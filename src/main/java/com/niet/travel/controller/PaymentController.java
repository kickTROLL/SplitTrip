package com.niet.travel.controller;

import com.niet.travel.dao.ParticipantDAO;
import com.niet.travel.dao.TripDAO;
import com.niet.travel.model.Trip;
import com.niet.travel.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class PaymentController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ParticipantDAO participantDAO;

    @Autowired
    private TripDAO tripDAO;

    @GetMapping("/trip/{tripId}/pay")
    public String showPaymentInterface(@PathVariable int tripId, @RequestParam BigDecimal amount, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        if (!participantDAO.hasStatus(tripId, user.getId(), "APPROVED")) return "redirect:/trip/" + tripId;
        if (amount == null || amount.compareTo(BigDecimal.ZERO) >= 0) return "redirect:/trip/" + tripId;

        model.addAttribute("tripId", tripId);
        model.addAttribute("amount", amount.abs()); // Send positive amount to pay
        return "mock_payment";
    }

    @PostMapping("/trip/{tripId}/pay/process")
    public String processPayment(@PathVariable int tripId, @RequestParam BigDecimal amount, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        if (!participantDAO.hasStatus(tripId, user.getId(), "APPROVED")) return "redirect:/trip/" + tripId;
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) return "redirect:/trip/" + tripId;

        Trip trip = tripDAO.findById(tripId);
        if (trip == null) return "redirect:/";

        String totalPaidSql =
                "SELECT COALESCE((SELECT SUM(amount) FROM expenses WHERE trip_id = ? AND paid_by = ?), 0) + " +
                "COALESCE((SELECT SUM(amount) FROM payments WHERE trip_id = ? AND user_id = ?), 0)";
        BigDecimal totalPaid = jdbcTemplate.queryForObject(totalPaidSql, BigDecimal.class, tripId, user.getId(), tripId, user.getId());

        BigDecimal totalExpense = jdbcTemplate.queryForObject(
                "SELECT COALESCE(SUM(amount), 0) FROM expenses WHERE trip_id = ?",
                BigDecimal.class,
                tripId
        );
        Integer approvedCount = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM trip_participants WHERE trip_id = ? AND status = 'APPROVED'",
                Integer.class,
                tripId
        );

        if (approvedCount == null || approvedCount == 0) return "redirect:/trip/" + tripId;

        BigDecimal perPerson = totalExpense.divide(BigDecimal.valueOf(approvedCount), 2, java.math.RoundingMode.HALF_UP);
        BigDecimal outstanding = perPerson.subtract(totalPaid);
        if (outstanding.compareTo(BigDecimal.ZERO) <= 0) return "redirect:/trip/" + tripId;

        BigDecimal paymentAmount = amount.min(outstanding);

        String sql = "INSERT INTO payments (trip_id, user_id, amount) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, tripId, user.getId(), paymentAmount);

        return "redirect:/trip/" + tripId;
    }
}

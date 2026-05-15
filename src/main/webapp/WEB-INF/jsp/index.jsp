<jsp:include page="header.jsp" />

<div class="wrap">
    <div class="hero fade-in">
        <h1 style="font-size: 48px; letter-spacing: -1.5px; margin-bottom: 20px;">Group travel for NIET students.<br><span>Split costs, not friendships.</span></h1>
        <p style="font-size: 18px; max-width: 500px; margin-bottom: 36px; color: var(--text-2);">Plan trips, share daily commutes, or coordinate college transport. Track every expense and split bills automatically.</p>
        <div class="hero-actions">
            <a href="${pageContext.request.contextPath}/register" class="btn btn-brand btn-lg">Get started</a>
            <a href="${pageContext.request.contextPath}/login" class="btn btn-outline btn-lg">Log in</a>
        </div>
    </div>

    <div class="grid-3">
        <div class="card feat fade-in delay-1" style="border-bottom: 4px solid #7c3aed;">
            <div class="feat-icon feat-icon-1">
                <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="12" cy="12" r="10"/><path d="M2 12h20"/><path d="M12 2a15.3 15.3 0 0 1 4 10 15.3 15.3 0 0 1-4 10 15.3 15.3 0 0 1-4-10 15.3 15.3 0 0 1 4-10z"/></svg>
            </div>
            <div class="fw-800 mb-8" style="font-size: 16px;">Any kind of trip</div>
            <div class="txt-sm txt-2" style="line-height: 1.6;">Leisure getaways, daily carpools, college transport, weekend trips &mdash; one platform for all.</div>
        </div>
        <div class="card feat fade-in delay-2" style="border-bottom: 4px solid #16a34a;">
            <div class="feat-icon feat-icon-2">
                <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><line x1="12" y1="1" x2="12" y2="23"/><path d="M17 5H9.5a3.5 3.5 0 0 0 0 7h5a3.5 3.5 0 0 1 0 7H6"/></svg>
            </div>
            <div class="fw-800 mb-8" style="font-size: 16px;">Track expenses</div>
            <div class="txt-sm txt-2" style="line-height: 1.6;">Everyone logs what they paid. Food, transport, stays &mdash; everything tracked in real-time.</div>
        </div>
        <div class="card feat fade-in delay-3" style="border-bottom: 4px solid #d97706;">
            <div class="feat-icon feat-icon-3">
                <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"/><circle cx="9" cy="7" r="4"/><path d="M23 21v-2a4 4 0 0 0-3-3.87"/><path d="M16 3.13a4 4 0 0 1 0 7.75"/></svg>
            </div>
            <div class="fw-800 mb-8" style="font-size: 16px;">Auto-split bills</div>
            <div class="txt-sm txt-2" style="line-height: 1.6;">See who owes what instantly. Settle up with one click through the payment gateway.</div>
        </div>
    </div>
</div>

</body></html>

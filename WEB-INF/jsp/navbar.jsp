<nav id="ib-navbar" class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <a href="#" class="navbar-brand">Brand</a>
        <div class="navbar-right">
            <s:if test="#session.loginUser==null">
                <a href="loginEnter" id="ib-navbar-link-login" class="navbar-link">
                    <s:text name="indexLoginBtn" />
                </a>
            </s:if>
            <s:else>
                <a href="logoutProcess" id="ib-navbar-link-login" class="navbar-link">
                    <s:text name="indexLogoutBtn" />
                </a>
            </s:else>
            <a href="regEnter" id="ib-navbar-link-reg" class="navbar-link">
                <s:text name="indexRegBtn" />
            </a>
        </div>
    </div>
</nav>

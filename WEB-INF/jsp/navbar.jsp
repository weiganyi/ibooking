<nav id="ib_navbar_nav" class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <a href="indexEnter" id="ib_navbar_link_index_a" class="navbar-brand"><s:property value="strTitle" /></a>
        <div class="navbar-right">
            <a href="#" class="navbar-brand">Brand</a>
            <s:if test="#session.loginUser==null">
                <a href="loginEnter" id="ib_navbar_login_a" class="navbar-link">
                    <s:text name="indexLoginBtn" />
                </a>
            </s:if>
            <s:else>
                <a href="logoutProcess" id="ib_navbar_login_a" class="navbar-link">
                    <s:text name="indexLogoutBtn" />
                </a>
            </s:else>
            <a href="regEnter" id="ib_navbar_reg_a" class="navbar-link">
                <s:text name="indexRegBtn" />
            </a>
        </div>
    </div>
</nav>

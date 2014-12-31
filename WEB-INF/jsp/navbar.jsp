<nav id="ib_navbar_nav" class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <a href="indexEnter" id="ib_navbar_link_index_a" class="navbar-brand"><s:property value="strTitle" /></a>
        <ul class="nav navbar-nav navbar-right">
            <s:if test="#session.loginUser!=null">
                <li class="dropdown">
                    <a href="#" id="ib_navbar_menu_a" class="dropdown-toggle" data-toggle="dropdown">
                        <s:text name="navOptions" /> <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#"><s:text name="navOptShop" /></a></li>
                        <li><a href="#"><s:text name="navOptOrder" /></a></li>
                        <li class="divider"></li>
                        <li><a href="#"><s:text name="navOptUser" /></a></li>
                    </ul>
                </li>
            </s:if>
            <s:if test="#session.loginUser==null">
                <li>
                    <a href="loginEnter" id="ib_navbar_login_a" class="navbar-link">
                        <s:text name="navLoginBtn" />
                    </a>
                </li>
            </s:if>
            <s:else>
                <li>
                    <a href="logoutProcess" id="ib_navbar_login_a" class="navbar-link">
                        <s:text name="navLogoutBtn" />
                    </a>
                </li>
            </s:else>
            <li>
                <a href="regEnter" id="ib_navbar_reg_a" class="navbar-link">
                    <s:text name="navRegBtn" />
                </a>
            </li>
        </ul>
    </div>
</nav>

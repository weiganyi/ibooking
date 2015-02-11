<nav id="ib_navbar_nav" class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <a href="indexPageEnter" id="ib_navbar_link_index_a" class="navbar-brand"><s:property value="strTitle" /></a>
        <ul class="nav navbar-nav navbar-right">
            <s:if test="#session.loginAuth=='admin'">
                <li class="dropdown">
                    <a href="#" id="ib_navbar_manager_menu_a" class="dropdown-toggle" data-toggle="dropdown">
                        <s:text name="navManager" /> <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="manOrderPageEnter"><s:text name="navManOrder" /></a></li>
                        <li><a href="manUserPageEnter"><s:text name="navManUser" /></a></li>
                        <li><a href="manMenuPageEnter"><s:text name="navManMenu" /></a></li>
                        <li><a href="manMenuTypePageEnter"><s:text name="navManMenuType" /></a></li>
                        <li><a href="manOptionPageEnter"><s:text name="navManOption" /></a></li>
                    </ul>
                </li>
            </s:if>
            <s:if test="#session.loginUser!=null">
                <li class="dropdown">
                    <a href="#" id="ib_navbar_option_menu_a" class="dropdown-toggle" data-toggle="dropdown">
                        <s:text name="navOptions" /> <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="shoppingPageEnter"><s:text name="navOptShop" /></a></li>
                        <li><a href="orderListPageEnter"><s:text name="navOptOrder" /></a></li>
                        <li class="divider"></li>
                        <li><a href="perInfoPageEnter"><s:text name="navOptUser" /></a></li>
                    </ul>
                </li>
            </s:if>
            <s:if test="#session.loginUser==null">
                <li>
                    <a href="loginPageEnter" id="ib_navbar_login_a" class="navbar-link">
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
                <a href="regPageEnter" id="ib_navbar_reg_a" class="navbar-link">
                    <s:text name="navRegBtn" />
                </a>
            </li>
        </ul>
    </div>
</nav>

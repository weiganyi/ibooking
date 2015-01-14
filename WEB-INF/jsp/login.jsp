<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <%-- the header --%>
        <%@ include file="/WEB-INF/jsp/header.jsp" %>

        <title><s:text name="loginTitle" /></title>
    </head>
    <body>
        <%-- the navbar --%>
        <%@ include file="/WEB-INF/jsp/navbar.jsp" %>

        <%-- the content --%>
        <div class="container">
            <form method="post" id="ib_login_form" action="loginProcess" role="form">
                <div class="form-group">
                    <label id="ib_login_label" for="ib_login_user_input">
                        <s:text name="loginInput" />
                    </label>
                    <input type="text" id="ib_login_user_input" name="userName" class="form-control input-lg" placeholder="<s:text name="loginUserName" />" />
                    <div class="ib_login_error_div">
                        <s:fielderror fieldName="userName"/>
                    </div>
                    <input type="password" id="ib_login_passwd_input" name="userPasswd" class="form-control input-lg" placeholder="<s:text name="loginUserPasswd" />" />
                    <div class="ib_login_error_div">
                        <s:fielderror fieldName="userPasswd"/>
                        <s:property value="failReason" escape="false"/>
                    </div>
                </div>
                <button id="ib_login_submit_btn" type="submit" class="btn btn-primary btn-lg">
                    <s:text name="loginSubmit" />
                </button>
                <a id="ib_login_reg_btn" href="regPageEnter" class="btn btn-primary btn-lg" role="button">
                    <s:text name="loginReg" />
                </a>
            </form>
        </div>

        <%-- the javascript --%>
        <script type="text/javascript">
            $("#ib_login_submit_btn").click(
                function() {
                    if ($("#ib_login_user_input").val() == "" || $("#ib_login_passwd_input").val() == "") {
                        alert("<s:text name="loginInputIsNull" />");
                        return false;
                    }
                }
            )
        </script>

    </body>
</html>
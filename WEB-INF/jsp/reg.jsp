<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <%-- the header --%>
        <%@ include file="/WEB-INF/jsp/header.jsp" %>

        <title><s:text name="regTitle" /></title>
    </head>
    <body>
        <%-- the navbar --%>
        <%@ include file="/WEB-INF/jsp/navbar.jsp" %>

        <%-- the content --%>
        <div class="container">
            <form method="post" id="ib_reg_form" action="regProcess" role="form">
                <div class="form-group">
                    <label for="ib_reg_user_input" id="ib_reg_label">
                        <s:text name="regInput" />
                    </label>
                    <input type="text" id="ib_reg_user_input" name="userName" class="form-control input-lg" placeholder="<s:text name="regUserName" />" />
                    <div class="ib_reg_error_div">
                        <s:fielderror fieldName="userName"/>
                    </div>
                    <input type="password" id="ib_reg_passwd_input" name="userPasswd" class="form-control input-lg" placeholder="<s:text name="regUserPasswd" />" />
                    <div class="ib_reg_error_div">
                        <s:fielderror fieldName="userPasswd"/>
                    </div>
                    <input type="text" id="ib_reg_tel_input" name="userTel" class="form-control input-lg" placeholder="<s:text name="regUserTel" />" />
                    <div class="ib_reg_error_div">
                        <s:fielderror fieldName="userTel"/>
                    </div>
                    <input type="text" id="ib_reg_addr_input" name="userAddr" class="form-control input-lg" placeholder="<s:text name="regUserAddr" />" />
                    <div class="ib_reg_error_div">
                        <s:fielderror fieldName="userAddr"/>
                        <s:property value="failReason" escape="false"/>
                    </div>
                </div>
                <button id="ib_reg_submit_btn" type="submit" class="btn btn-primary btn-lg btn-block">
                    <s:text name="regSubmit" />
                </button>
            </form>
        </div>

        <%-- the javascript --%>
        <script type="text/javascript">
            $("#ib_reg_submit_btn").click(
                function() {
                    if ($("#ib_reg_user_input").val() == "" || $("#ib_reg_passwd_input").val() == ""
                        || $("#ib_reg_tel_input").val() == "" || $("#ib_reg_addr_input").val() == "") {
                        alert("<s:text name="regInputIsNull" />");
                        return false;
                    }
                }
            )
        </script>

    </body>
</html>
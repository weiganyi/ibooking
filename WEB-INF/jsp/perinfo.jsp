<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <%-- the header --%>
        <%@ include file="/WEB-INF/jsp/header.jsp" %>

        <title><s:text name="perinfoTitle" /></title>
    </head>
    <body>
        <%-- the navbar --%>
        <%@ include file="/WEB-INF/jsp/navbar.jsp" %>

        <%-- the content --%>
        <div class="container">
            <form method="post" id="ib_perinfo_form" action="perInfoProcess" role="form">
                <div class="form-group">
                    <label for="ib_perinfo_old_passwd_input" id="ib_perinfo_label">
                        <s:text name="perinfoInput" />
                    </label>
                    <input type="password" id="ib_perinfo_old_passwd_input" name="userOldPasswd" class="form-control input-lg" placeholder="<s:text name="perinfoUserOldPasswd" />" />
                    <div class="ib_perinfo_error_div">
                        <s:fielderror fieldName="userOldPasswd"/>
                    </div>
                    <input type="text" id="ib_perinfo_new_user_input" name="userNewName" class="form-control input-lg" placeholder="<s:text name="perinfoUserNewName" />" />
                    <div class="ib_perinfo_error_div">
                        <s:fielderror fieldName="userNewName"/>
                    </div>
                    <input type="password" id="ib_perinfo_new_passwd_input" name="userNewPasswd" class="form-control input-lg" placeholder="<s:text name="perinfoUserNewPasswd" />" />
                    <div class="ib_perinfo_error_div">
                        <s:fielderror fieldName="userNewPasswd"/>
                    </div>
                    <input type="text" id="ib_perinfo_new_tel_input" name="userNewTel" class="form-control input-lg" placeholder="<s:text name="perinfoUserNewTel" />" />
                    <div class="ib_perinfo_error_div">
                        <s:fielderror fieldName="userNewTel"/>
                    </div>
                    <input type="text" id="ib_perinfo_new_addr_input" name="userNewAddr" class="form-control input-lg" placeholder="<s:text name="perinfoUserNewAddr" />" />
                    <div class="ib_perinfo_error_div">
                        <s:fielderror fieldName="userNewAddr"/>
                        <s:property value="failReason" escape="false"/>
                    </div>
                </div>
                <button id="ib_perinfo_submit_btn" type="submit" class="btn btn-primary btn-lg btn-block">
                    <s:text name="perinfoSubmit" />
                </button>
            </form>
        </div>

        <%-- the javascript --%>
        <script type="text/javascript">
            $("#ib_perinfo_submit_btn").click(
                function() {
                    if ($("#ib_perinfo_old_passwd_input").val() == "" 
                        || $("#ib_perinfo_new_user_input").val() == ""
                        || $("#ib_perinfo_new_passwd_input").val() == "" 
                        || $("#ib_perinfo_new_tel_input").val() == "" 
                        || $("#ib_perinfo_new_addr_input").val() == "") {
                        alert("<s:text name="perinfoInputIsNull" />");
                        return false;
                    }
                }
            )
        </script>

    </body>
</html>
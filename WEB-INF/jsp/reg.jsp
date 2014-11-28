<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <%@taglib prefix="s" uri="/struts-tags" %>

        <title><s:text name="regTitle" /></title>

        <link type="text/css" href="../res/bootstrap.min.css" rel="stylesheet">
        <link type="text/css" href="../res/ibooking.css" rel="stylesheet">

        <script type="text/javascript" src="../res/jquery-1.10.1.js"></script>
        <script type="text/javascript" src="../res/bootstrap.min.js"></script>
        <script type="text/javascript" src="../res/ibooking.js"></script>
    </head>
    <body>
        <%-- the navbar --%>
        <%@ include file="/WEB-INF/jsp/navbar.jsp" %>

        <%-- the content --%>
        <div class="container">
            <form method="post" id="ib-reg-form" action="regProcess" role="form">
                <div class="form-group">
                    <label for="ib-reg-input-user" id="ib-reg-label">
                        <s:text name="regInput" />
                    </label>
                    <input type="text" id="ib-reg-input-user" name="userName" class="form-control input-lg" placeholder="<s:text name="regUserName" />" />
                    <div class="ib-reg-error">
                        <s:fielderror fieldName="userName"/>
                    </div>
                    <input type="password" id="ib-reg-input-passwd" name="userPasswd" class="form-control input-lg" placeholder="<s:text name="regUserPasswd" />" />
                    <div class="ib-reg-error">
                        <s:fielderror fieldName="userPasswd"/>
                    </div>
                    <input type="text" id="ib-reg-input-tel" name="userTel" class="form-control input-lg" placeholder="<s:text name="regUserTel" />" />
                    <div class="ib-reg-error">
                        <s:fielderror fieldName="userTel"/>
                    </div>
                    <input type="text" id="ib-reg-input-addr" name="userAddr" class="form-control input-lg" placeholder="<s:text name="regUserAddr" />" />
                    <div class="ib-reg-error">
                        <s:fielderror fieldName="userAddr"/>
                        <s:property value="failReason" escape="false"/>
                    </div>
                </div>
                <button id="ib-reg-btn-submit" type="submit" class="btn btn-primary btn-lg btn-block">
                    <s:text name="regSubmit" />
                </button>
            </form>
        </div>

        <%-- the javascript --%>
        <%--script type="text/javascript">
            $("#ib-reg-btn-submit").click(
                function() {
                    if ($("#ib-reg-input-user").val() == "" || $("#ib-reg-input-passwd").val() == ""
                        || $("#ib-reg-input-tel").val() == "" || $("#ib-reg-input-addr").val() == "") {
                        alert("<s:text name="regInputIsNull" />");
                        return false;
                    }
                }
            )
        </script--%>

    </body>
</html>
<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <%@taglib prefix="s" uri="/struts-tags" %>

        <title><s:text name="loginTitle" /></title>

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
            <form method="post" id="ib-login-form" action="loginProcess" role="form">
                <div class="form-group">
                    <label id="ib-login-label" for="ib-login-input-user">
                        <s:text name="loginInput" />
                    </label>
                    <input type="text" id="ib-login-input-user" name="userName" class="form-control input-lg" placeholder="<s:text name="loginUserName" />" />
                    <div class="ib-login-error">
                        <s:fielderror fieldName="userName"/>
                    </div>
                    <input type="password" id="ib-login-input-passwd" name="userPasswd" class="form-control input-lg" placeholder="<s:text name="loginUserPasswd" />" />
                    <div class="ib-login-error">
                        <s:fielderror fieldName="userPasswd"/>
                        <s:property value="failReason" escape="false"/>
                    </div>
                </div>
                <button id="ib-login-btn-submit" type="submit" class="btn btn-primary btn-lg">
                    <s:text name="loginSubmit" />
                </button>
                <a id="ib-login-btn-reg" href="regEnter" class="btn btn-primary btn-lg" role="button">
                    <s:text name="loginReg" />
                </a>
            </form>
        </div>

        <%-- the javascript --%>
        <%--script type="text/javascript">
            $("#ib-login-btn-submit").click(
                function() {
                    if ($("#ib-login-input-user").val() == "" || $("#ib-login-input-passwd").val() == "") {
                        alert("<s:text name="loginInputIsNull" />");
                        return false;
                    }
                }
            )
        </script--%>

    </body>
</html>
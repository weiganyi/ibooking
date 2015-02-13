<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <%-- the header --%>
        <%@ include file="/WEB-INF/jsp/header.jsp" %>

        <title><s:text name="manPicTitle" /></title>
    </head>
    <body>
        <%-- the navbar --%>
        <%@ include file="/WEB-INF/jsp/navbar.jsp" %>
 
        <div id="ib_man_pic_content_div">
            <%@ include file="/WEB-INF/jsp/manager/man_pic_content.jsp" %>
        </div>
 
        <%-- the javascript --%>
        <script type="text/javascript">
            function fnChangeFinish(data, code, request) {
                if (code == "success") {
                    $("#ib_man_pic_content_div").html(data);
                }
            }

            function onBtnDelClick(data) {
                var ajaxUrl = "manPicChange";

                jQuery.ajax({
                    type: "POST",
                    url: ajaxUrl,
                    data: data,
                    dataType: "html",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: fnChangeFinish
                });
            }

        </script>

    </body>
</html>
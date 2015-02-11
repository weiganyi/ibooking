<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <%-- the header --%>
        <%@ include file="/WEB-INF/jsp/header.jsp" %>

        <title><s:text name="shopTitle" /></title>
    </head>
    <body>
        <%-- the navbar --%>
        <%@ include file="/WEB-INF/jsp/navbar.jsp" %>

        <div id="ib_shop_content_div">
            <%@ include file="/WEB-INF/jsp/shopping_content.jsp" %>
        </div>

        <%-- the javascript --%>
        <script type="text/javascript">
            function fnChangeFinish(data, code, request) {
                if (code == "success") {
                    $("#ib_shop_content_div").html(data);
                }
            }

            function onBtnChangeClick(data) {
                var ajaxUrl = "shoppingChange";
  
                jQuery.ajax({
                    type: "POST",
                    url: ajaxUrl,
                    data: data,
                    dataType: "html",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: fnChangeFinish
                });
            }

            function onInputRemarkChange(data) {
                var ajaxUrl = "shoppingChange";
 
                var menuName = data.match(/menuName=(.+)&opt=remarkChange/);
                if (menuName != null && menuName[1] != null) {
                    $(".ib_shop_remark_input").each(function() {
                        if ($(this).attr("menuName") == menuName[1] && 
                            $(this).attr("remark") != $(this).val()) {
                            data = data + "&remark=" + $(this).val();
     
                            jQuery.ajax({
                                type: "POST",
                                url: ajaxUrl,
                                data: data,
                                dataType: "html",
                                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                                success: fnChangeFinish
                            });
                        }
                    });
                }
            }

            function onInputAddrChange(data) {
                var ajaxUrl = "shoppingChange";

                if ($("#ib_shop_addr_input").attr("address") != $("#ib_shop_addr_input").val()) {
                    data = data + "&address=" + $("#ib_shop_addr_input").val();
     
                    jQuery.ajax({
                        type: "POST",
                        url: ajaxUrl,
                        data: data,
                        dataType: "html",
                        contentType: "application/x-www-form-urlencoded; charset=utf-8",
                        success: fnChangeFinish
                    });
                }
            }
        </script>

    </body>
</html>
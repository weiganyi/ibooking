<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <%-- the header --%>
        <%@ include file="/WEB-INF/jsp/header.jsp" %>

        <title><s:text name="indexTitle" /></title>
    </head>
    <body>
        <%-- the navbar --%>
        <%@ include file="/WEB-INF/jsp/navbar.jsp" %>

        <s:iterator value="lstMenuTypeBean" id="bean" status="st">
        <div class="row">
            <h2 class="ib_idx_caption_h"><s:property value="#bean.name" /></h2>
            <s:iterator value="#bean.lst" id="bean2">
            <div class="col-md-3">
                <div class="thumbnail">
                    <img src="<s:property value="#bean2.addr" />">
                    <h4 class="ib_idx_caption_h"><s:property value="#bean2.name" /></h4>
                    <h5 class="ib_idx_caption_h">
                        <s:text name="indexPrice" />: <s:property value="#bean2.price" />
                    </h5>
                    <p class="ib_idx_caption_h">
                        <button class="btn btn-primary" 
                            data_name='<s:property value="#bean2.name" />' 
                            data_price='<s:property value="#bean2.price" />'>
                            <s:text name="indexSubmitBtn" />
                        </button>
                    </p>
                </div>
            </div>
            </s:iterator>
        </div>
        </s:iterator>

        <%-- the pagination --%>
        <%@ include file="/WEB-INF/jsp/pagination.jsp" %>

        <%-- the javascript --%>
        <script type="text/javascript">
            function fnSubmitBtn(data, code, request) {
                var content = $("button");
            }

            $("button").click(
                function() {
                    var ajaxUrl = "menuSubmit";
                    var ajaxData = "name=" + $(this).attr("data_name") + "&" + "price=" + $(this).attr("data_price");
 
                    jQuery.ajax({
                        type: "POST",
                        url: ajaxUrl,
                        data: ajaxData,
                        dataType: "html",
                        contentType: "application/x-www-form-urlencoded; charset=utf-8",
                        success: fnSubmitBtn
                    });
                }
            )
        </script>

    </body>
</html>
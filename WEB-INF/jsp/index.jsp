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

        <div id="ib_idx_content_div">
            <s:iterator value="lstMenuTypeBean" id="bean">
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
                                menuName='<s:property value="#bean2.name" />' 
                                menuPrice='<s:property value="#bean2.price" />'>
                                <s:text name="indexSubmitBtn" /> <span class="badge"><s:property value="#bean2.amount" /></span>
                            </button>
                        </p>
                    </div>
                </div>
                </s:iterator>
            </div>
            </s:iterator>
    
            <%-- the pagination --%>
            <%@ include file="/WEB-INF/jsp/pagination.jsp" %>
        </div>

        <%-- the javascript --%>
        <script type="text/javascript">
            function fnSubmitBtn(data, code, request) {
                //exclude the error page
                var menuAmount =  data.match(/<!DOCTYPE html>/);
                if (menuAmount == null) {
                    var menuName = this.data.match(/menuName=(.+)&/);
                    if (menuName != null && menuName[1] != null) {
                        $("button").each(function() {
                            if ($(this).attr("menuName") == menuName[1]) {
                                $(this).children("span").text(data);
                            }
                        });
                    }
                }
            }

            $("button").click(
                function() {
                    var ajaxUrl = "menuSubmit";
                    var ajaxData = "menuName=" + $(this).attr("menuName") + "&" + "menuPrice=" + $(this).attr("menuPrice");
 
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
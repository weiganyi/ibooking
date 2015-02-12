<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <%-- the header --%>
        <%@ include file="/WEB-INF/jsp/header.jsp" %>

        <title><s:text name="manOrderDetailTitle" /></title>
    </head>
    <body>
        <%-- the navbar --%>
        <%@ include file="/WEB-INF/jsp/navbar.jsp" %>
 
        <div id="ib_man_orderdetail_content_div">
            <%@ include file="/WEB-INF/jsp/manager/man_orderdetail_content.jsp" %>
        </div>
 
        <%-- the javascript --%>
        <script type="text/javascript">
            function onBtnNewClick() {
                var status = $("#ib_man_orderdetail_new_btn").attr("status");
                if (status == "new") {
                    $("#ib_man_orderdetail_new_menu_input").removeAttr("readOnly");
                    $("#ib_man_orderdetail_new_price_input").removeAttr("readOnly");
                    $("#ib_man_orderdetail_new_amount_input").removeAttr("readOnly");
                    $("#ib_man_orderdetail_new_remark_input").removeAttr("readOnly");

                    $("#ib_man_orderdetail_add_btn").removeAttr("disabled");

                    $("#ib_man_orderdetail_new_btn").attr("status", "cancel");
                    $("#ib_man_orderdetail_new_btn").text("<s:text name="manOrderDetailCancelBtn" />");
                }else if (status == "cancel") {
                    $("#ib_man_orderdetail_new_menu_input").attr("readOnly", "true");
                    $("#ib_man_orderdetail_new_price_input").attr("readOnly", "true");
                    $("#ib_man_orderdetail_new_amount_input").attr("readOnly", "true");
                    $("#ib_man_orderdetail_new_remark_input").attr("readOnly", "true");

                    $("#ib_man_orderdetail_add_btn").attr("disabled", "disabled");

                    $("#ib_man_orderdetail_new_btn").attr("status", "new");
                    $("#ib_man_orderdetail_new_btn").text("<s:text name="manOrderDetailNewBtn" />");
                }
            }

            function fnChangeFinish(data, code, request) {
                if (code == "success") {
                    $("#ib_man_orderdetail_content_div").html(data);
                }
            }

            function onBtnAddClick(data) {
                var ajaxUrl = "manOrderDetailChange";
  
                data = data + 
                    "&menu=" + $("#ib_man_orderdetail_new_menu_input").val() + 
                    "&price=" + $("#ib_man_orderdetail_new_price_input").val() + 
                    "&amount=" + $("#ib_man_orderdetail_new_amount_input").val() + 
                    "&remark=" + $("#ib_man_orderdetail_new_remark_input").val();
  
                jQuery.ajax({
                    type: "POST",
                    url: ajaxUrl,
                    data: data,
                    dataType: "html",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: fnChangeFinish
                });
            }

            function onBtnEditClick(data) {
                $(".ib_man_orderdetail_id_label").each(function() {
                     if ($(this).text() == data) {
                        var menu_input = $(this).next();
                        var price_input = $(this).parent().next().children();
                        var amount_input = price_input.parent().next().children();
                        var remark_input = amount_input.parent().next().children();

                        var edit_btn = remark_input.parent().next().children("#ib_man_orderdetail_edit_btn");
                        var mod_btn = edit_btn.next();

                        var status = edit_btn.attr("status");
                        if (status == "edit") {
                            menu_input.removeAttr("readOnly");
                            price_input.removeAttr("readOnly");
                            amount_input.removeAttr("readOnly");
                            remark_input.removeAttr("readOnly");

                            mod_btn.removeAttr("disabled");

                            edit_btn.attr("status", "cancel");
                            edit_btn.text("<s:text name="manOrderDetailCancelBtn" />");
                        }else if (status == "cancel") {
                            menu_input.attr("readOnly", "true");
                            price_input.attr("readOnly", "true");
                            amount_input.attr("readOnly", "true");
                            remark_input.attr("readOnly", "true");

                            mod_btn.attr("disabled", "disabled");

                            edit_btn.attr("status", "edit");
                            edit_btn.text("<s:text name="manOrderDetailEditBtn" />");
                        }
                     }
                });
            }

            function onBtnModClick(data) {
                var ajaxUrl = "manOrderDetailChange";

                var id = data.match(/id=(.+)&orderId/);
                if (id != null && id[1] != null) {
                    $(".ib_man_orderdetail_id_label").each(function() {
                         if ($(this).text() == id[1]) {
                            var menu_input = $(this).next();
                            var price_input = $(this).parent().next().children();
                            var amount_input = price_input.parent().next().children();
                            var remark_input = amount_input.parent().next().children();

                            data = data + 
                                "&menu=" + menu_input.val() + 
                                "&price=" + price_input.val() + 
                                "&amount=" + amount_input.val() + 
                                "&remark=" + remark_input.val();

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

            function onBtnDelClick(data) {
                var ajaxUrl = "manOrderDetailChange";

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
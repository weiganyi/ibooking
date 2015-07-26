<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <%-- the header --%>
        <%@ include file="/WEB-INF/jsp/header.jsp" %>

        <title><s:text name="manMenuTitle" /></title>
    </head>
    <body>
        <%-- the navbar --%>
        <%@ include file="/WEB-INF/jsp/navbar.jsp" %>
 
        <div id="ib_man_menu_content_div">
            <%@ include file="/WEB-INF/jsp/manager/man_menu_content.jsp" %>
        </div>
 
        <%-- the javascript --%>
        <script type="text/javascript">
            function onBtnNewClick() {
                var status = $("#ib_man_menu_new_btn").attr("status");
                if (status == "new") {
                    $("#ib_man_menu_new_name_input").removeAttr("readOnly");
                    $("#ib_man_menu_new_price_input").removeAttr("readOnly");
                    $("#ib_man_menu_new_pic_select").removeAttr("disabled");
                    $("#ib_man_menu_new_type_select").removeAttr("disabled");

                    $("#ib_man_menu_add_btn").removeAttr("disabled");

                    $("#ib_man_menu_new_btn").attr("status", "cancel");
                    $("#ib_man_menu_new_btn").text("<s:text name="manMenuCancelBtn" />");
                }else if (status == "cancel") {
                    $("#ib_man_menu_new_name_input").attr("readOnly", "true");
                    $("#ib_man_menu_new_price_input").attr("readOnly", "true");
                    $("#ib_man_menu_new_pic_select").attr("disabled", "disabled");
                    $("#ib_man_menu_new_type_select").attr("disabled", "disabled");

                    $("#ib_man_menu_add_btn").attr("disabled", "disabled");

                    $("#ib_man_menu_new_btn").attr("status", "new");
                    $("#ib_man_menu_new_btn").text("<s:text name="manMenuNewBtn" />");
                }
            }

            function fnChangeFinish(data, code, request) {
                if (code == "success") {
                    $("#ib_man_menu_content_div").html(data);
                }
            }

            function onBtnAddClick(data) {
                var ajaxUrl = "manMenuChange";
  
                data = data + 
                    "&name=" + $("#ib_man_menu_new_name_input").val() + 
                    "&price=" + $("#ib_man_menu_new_price_input").val() + 
                    "&pic=" + $("#ib_man_menu_new_pic_select").val() + 
                    "&type=" + $("#ib_man_menu_new_type_select").val();
  
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
                $(".ib_man_menu_id_label").each(function() {
                     if ($(this).text() == data) {
                        var name_input = $(this).next();
                        var price_input = $(this).parent().next().children();
                        var pic_select = price_input.parent().next().children();
                        var type_select = pic_select.parent().next().children()

                        var edit_btn = type_select.parent().next().children("#ib_man_menu_edit_btn");
                        var mod_btn = edit_btn.next();

                        var status = edit_btn.attr("status");
                        if (status == "edit") {
                            name_input.removeAttr("readOnly");
                            price_input.removeAttr("readOnly");
                            pic_select.removeAttr("disabled");
                            type_select.removeAttr("disabled");

                            mod_btn.removeAttr("disabled");

                            edit_btn.attr("status", "cancel");
                            edit_btn.text("<s:text name="manMenuCancelBtn" />");
                        }else if (status == "cancel") {
                            name_input.attr("readOnly", "true");
                            price_input.attr("readOnly", "true");
                            pic_select.attr("disabled", "disabled");
                            type_select.attr("disabled", "disabled");

                            mod_btn.attr("disabled", "disabled");

                            edit_btn.attr("status", "edit");
                            edit_btn.text("<s:text name="manMenuEditBtn" />");
                        }
                     }
                });
            }

            function onBtnModClick(data) {
                var ajaxUrl = "manMenuChange";

                var id = data.match(/id=(.+)&opt=menuMod/);
                if (id != null && id[1] != null) {
                    $(".ib_man_menu_id_label").each(function() {
                         if ($(this).text() == id[1]) {
                            var name_input = $(this).next();
                            var price_input = $(this).parent().next().children();
                        	var pic_select = price_input.parent().next().children();
                        	var type_select = pic_select.parent().next().children()
              
                            data = data + 
                                "&name=" + name_input.val() + 
                                "&price=" + price_input.val() + 
                                "&pic=" + pic_select.val() + 
                                "&type=" + type_select.val();
              
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
                var ajaxUrl = "manMenuChange";

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
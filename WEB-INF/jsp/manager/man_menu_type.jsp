<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <%-- the header --%>
        <%@ include file="/WEB-INF/jsp/header.jsp" %>

        <title><s:text name="manMenuTypeTitle" /></title>
    </head>
    <body>
        <%-- the navbar --%>
        <%@ include file="/WEB-INF/jsp/navbar.jsp" %>
 
        <div id="ib_man_menu_type_content_div">
            <%@ include file="/WEB-INF/jsp/manager/man_menu_type_content.jsp" %>
        </div>
 
        <%-- the javascript --%>
        <script type="text/javascript">
            function onBtnNewClick() {
                var status = $("#ib_man_menu_type_new_btn").attr("status");
                if (status == "new") {
                    $("#ib_man_menu_type_new_name_input").removeAttr("readOnly");

                    $("#ib_man_menu_type_add_btn").removeAttr("disabled");

                    $("#ib_man_menu_type_new_btn").attr("status", "cancel");
                    $("#ib_man_menu_type_new_btn").text("<s:text name="manMenuTypeCancelBtn" />");
                }else if (status == "cancel") {
                    $("#ib_man_menu_type_new_name_input").attr("readOnly", "true");

                    $("#ib_man_menu_type_add_btn").attr("disabled", "disabled");

                    $("#ib_man_menu_type_new_btn").attr("status", "new");
                    $("#ib_man_menu_type_new_btn").text("<s:text name="manMenuTypeNewBtn" />");
                }
            }

            function fnChangeFinish(data, code, request) {
                if (code == "success") {
                    $("#ib_man_menu_type_content_div").html(data);
                }
            }

            function onBtnAddClick(data) {
                var ajaxUrl = "manMenuTypeChange";
  
                data = data + 
                    "&name=" + $("#ib_man_menu_type_new_name_input").val();
  
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
                $(".ib_man_menu_type_id_label").each(function() {
                     if ($(this).text() == data) {
                        var name_input = $(this).next();

                        var edit_btn = name_input.parent().next().children("#ib_man_menu_type_edit_btn");
                        var mod_btn = edit_btn.next();

                        var status = edit_btn.attr("status");
                        if (status == "edit") {
                            name_input.removeAttr("readOnly");

                            mod_btn.removeAttr("disabled");

                            edit_btn.attr("status", "cancel");
                            edit_btn.text("<s:text name="manMenuTypeCancelBtn" />");
                        }else if (status == "cancel") {
                            name_input.attr("readOnly", "true");

                            mod_btn.attr("disabled", "disabled");

                            edit_btn.attr("status", "edit");
                            edit_btn.text("<s:text name="manMenuTypeEditBtn" />");
                        }
                     }
                });
            }

            function onBtnModClick(data) {
                var ajaxUrl = "manMenuTypeChange";

                var id = data.match(/id=(.+)&opt=menuTypeMod/);
                if (id != null && id[1] != null) {
                    $(".ib_man_menu_type_id_label").each(function() {
                         if ($(this).text() == id[1]) {
                            var name_input = $(this).next();
              
                            data = data + 
                                "&name=" + name_input.val();
              
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
                var ajaxUrl = "manMenuTypeChange";

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
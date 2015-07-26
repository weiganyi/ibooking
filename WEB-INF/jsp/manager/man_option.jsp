<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <%-- the header --%>
        <%@ include file="/WEB-INF/jsp/header.jsp" %>

        <title><s:text name="manOptionTitle" /></title>
    </head>
    <body>
        <%-- the navbar --%>
        <%@ include file="/WEB-INF/jsp/navbar.jsp" %>
 
        <div id="ib_man_option_content_div">
            <%@ include file="/WEB-INF/jsp/manager/man_option_content.jsp" %>
        </div>
 
        <%-- the javascript --%>
        <script type="text/javascript">
            function onBtnNewClick() {
                var status = $("#ib_man_option_new_btn").attr("status");
                if (status == "new") {
                    $("#ib_man_option_new_name_input").removeAttr("readOnly");
                    $("#ib_man_option_new_value_input").removeAttr("readOnly");

                    $("#ib_man_option_add_btn").removeAttr("disabled");

                    $("#ib_man_option_new_btn").attr("status", "cancel");
                    $("#ib_man_option_new_btn").text("<s:text name="manOptionCancelBtn" />");
                }else if (status == "cancel") {
                    $("#ib_man_option_new_name_input").attr("readOnly", "true");
                    $("#ib_man_option_new_value_input").attr("readOnly", "true");

                    $("#ib_man_option_add_btn").attr("disabled", "disabled");

                    $("#ib_man_option_new_btn").attr("status", "new");
                    $("#ib_man_option_new_btn").text("<s:text name="manOptionNewBtn" />");
                }
            }

            function fnChangeFinish(data, code, request) {
                if (code == "success") {
                    $("#ib_man_option_content_div").html(data);
                }
            }

            function onBtnAddClick(data) {
                var ajaxUrl = "manOptionChange";
  
                data = data + 
                    "&name=" + $("#ib_man_option_new_name_input").val() + 
                    "&value=" + $("#ib_man_option_new_value_input").val();
  
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
                $(".ib_man_option_id_label").each(function() {
                     if ($(this).text() == data) {
                        var name_input = $(this).next();
                        var value_input = $(this).parent().next().children();

                        var edit_btn = value_input.parent().next().children("#ib_man_option_edit_btn");
                        var mod_btn = edit_btn.next();

                        var status = edit_btn.attr("status");
                        if (status == "edit") {
                            name_input.removeAttr("readOnly");
                            value_input.removeAttr("readOnly");

                            mod_btn.removeAttr("disabled");

                            edit_btn.attr("status", "cancel");
                            edit_btn.text("<s:text name="manOptionCancelBtn" />");
                        }else if (status == "cancel") {
                            name_input.attr("readOnly", "true");
                            value_input.attr("readOnly", "true");

                            mod_btn.attr("disabled", "disabled");

                            edit_btn.attr("status", "edit");
                            edit_btn.text("<s:text name="manOptionEditBtn" />");
                        }
                     }
                });
            }

            function onBtnModClick(data) {
                var ajaxUrl = "manOptionChange";

                var id = data.match(/id=(.+)&opt=optionMod/);
                if (id != null && id[1] != null) {
                    $(".ib_man_option_id_label").each(function() {
                         if ($(this).text() == id[1]) {
                            var name_input = $(this).next();
                            var value_input = $(this).parent().next().children();
              
                            data = data + 
                                "&name=" + name_input.val() + 
                                "&value=" + value_input.val();
              
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
                var ajaxUrl = "manOptionChange";

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
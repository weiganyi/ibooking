<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <%-- the header --%>
        <%@ include file="/WEB-INF/jsp/header.jsp" %>

        <title><s:text name="manuserTitle" /></title>
    </head>
    <body>
        <%-- the navbar --%>
        <%@ include file="/WEB-INF/jsp/navbar.jsp" %>
 
        <div id="ib_manuser_content_div">
            <%@ include file="/WEB-INF/jsp/manuser_content.jsp" %>
        </div>
 
        <%-- the javascript --%>
        <script type="text/javascript">
            function onBtnNewClick() {
                var status = $("#ib_manuser_new_btn").attr("status");
                if (status == "new") {
                    $("#ib_manuser_new_user_input").removeAttr("readOnly");
                    $("#ib_manuser_new_passwd_input").removeAttr("readOnly");
                    $("#ib_manuser_new_auth_select").removeAttr("disabled");
                    $("#ib_manuser_new_tel_input").removeAttr("readOnly");
                    $("#ib_manuser_new_addr_input").removeAttr("readOnly");

                    $("#ib_manuser_add_btn").removeAttr("disabled");

                    $("#ib_manuser_new_btn").attr("status", "cancel");
                    $("#ib_manuser_new_btn").text("<s:text name="manuserCancelBtn" />");
                }else if (status == "cancel") {
                    $("#ib_manuser_new_user_input").attr("readOnly", "true");
                    $("#ib_manuser_new_passwd_input").attr("readOnly", "true");
                    $("#ib_manuser_new_auth_select").attr("disabled", "disabled");
                    $("#ib_manuser_new_tel_input").attr("readOnly", "true");
                    $("#ib_manuser_new_addr_input").attr("readOnly", "true");

                    $("#ib_manuser_add_btn").attr("disabled", "disabled");

                    $("#ib_manuser_new_btn").attr("status", "new");
                    $("#ib_manuser_new_btn").text("<s:text name="manuserNewBtn" />");
                }
            }

            function fnChangeFinish(data, code, request) {
                if (code == "success") {
                    $("#ib_manuser_content_div").html(data);
                }
            }

            function onBtnAddClick(data) {
                var ajaxUrl = "manUserChange";
  
                data = data + 
                    "&user=" + $("#ib_manuser_new_user_input").val() + 
                    "&passwd=" + $("#ib_manuser_new_passwd_input").val() + 
                    "&auth=" + $("#ib_manuser_new_auth_select").val() + 
                    "&tel=" + $("#ib_manuser_new_tel_input").val() + 
                    "&addr=" + $("#ib_manuser_new_addr_input").val();
  
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
                $(".ib_manuser_id_label").each(function() {
                     if ($(this).text() == data) {
                        var user_input = $(this).next();
                        var passwd_input = $(this).parent().next().children();
                        var auth_select = passwd_input.parent().next().children();
                        var tel_input = auth_select.parent().next().children();
                        var addr_input = tel_input.parent().next().children();

                        var edit_btn = addr_input.parent().next().children("#ib_manuser_edit_btn");
                        var mod_btn = edit_btn.next();

                        var status = edit_btn.attr("status");
                        if (status == "edit") {
                            user_input.removeAttr("readOnly");
                            passwd_input.removeAttr("readOnly");
                            auth_select.removeAttr("disabled");
                            tel_input.removeAttr("readOnly");
                            addr_input.removeAttr("readOnly");

                            mod_btn.removeAttr("disabled");

                            edit_btn.attr("status", "cancel");
                            edit_btn.text("<s:text name="manuserCancelBtn" />");
                        }else if (status == "cancel") {
                            user_input.attr("readOnly", "true");
                            passwd_input.attr("readOnly", "true");
                            auth_select.attr("disabled", "disabled");
                            tel_input.attr("readOnly", "true");
                            addr_input.attr("readOnly", "true");

                            mod_btn.attr("disabled", "disabled");

                            edit_btn.attr("status", "edit");
                            edit_btn.text("<s:text name="manuserEditBtn" />");
                        }
                     }
                });
            }

            function onBtnModClick(data) {
                var ajaxUrl = "manUserChange";

                var id = data.match(/id=(.+)&opt=userMod/);
                if (id != null && id[1] != null) {
                    $(".ib_manuser_id_label").each(function() {
                         if ($(this).text() == id[1]) {
                            var user_input = $(this).next();
                            var passwd_input = $(this).parent().next().children();
                            var auth_select = passwd_input.parent().next().children();
                            var tel_input = auth_select.parent().next().children();
                            var addr_input = tel_input.parent().next().children();
              
                            data = data + 
                                "&user=" + user_input.val() + 
                                "&passwd=" + passwd_input.val() + 
                                "&auth=" + auth_select.val() + 
                                "&tel=" + tel_input.val() + 
                                "&addr=" + addr_input.val();
              
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
                var ajaxUrl = "manUserChange";

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
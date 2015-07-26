<%-- include the struct tag --%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%-- include the jstl --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table">
    <tr>
        <th><s:text name="manMenuName" /></th>
        <th><s:text name="manMenuPrice" /></th>
        <th><s:text name="manMenuPic" /></th>
        <th><s:text name="manMenuType" /></th>
        <th><s:text name="manMenuOpt" /></th>
    </tr>
    <tr>
        <td>
            <input type="text" 
                id="ib_man_menu_new_name_input"
                class="form-control" 
                value=""
                readOnly="true" />
        </td>
        <td>
            <input type="text" 
                id="ib_man_menu_new_price_input"
                class="form-control" 
                value=""
                readOnly="true" />
        </td>
        <td>
            <select id="ib_man_menu_new_pic_select" class="form-control" disabled="disabled">
            	<s:iterator value="lstPicBean" id="bean3">
                	<option value='<s:property value="#bean3.addr" />'><s:property value="#bean3.name" /></option>
                </s:iterator>
            </select>
        </td>
        <td>
            <select id="ib_man_menu_new_type_select" class="form-control" disabled="disabled">
            	<s:iterator value="lstMenuType" id="bean2">
                	<option value='<s:property value="#bean2.id" />'><s:property value="#bean2.name" /></option>
                </s:iterator>
            </select>
        </td>
        <td>
            <button class="btn btn-primary btn-xs" 
                id="ib_man_menu_new_btn"
                status="new"
                onclick='onBtnNewClick();'>
                <s:text name="manMenuNewBtn" />
            </button>
            <button class="btn btn-primary btn-xs" disabled="disabled"
                id="ib_man_menu_add_btn"
                onclick='onBtnAddClick("opt=menuAdd&page=${currPage}");'>
                <s:text name="manMenuAddBtn" />
            </button>
        </td>
    </tr>
    <s:iterator value="lstMenu" id="bean">
    <tr>
        <td>
            <label class="ib_man_menu_id_label" style="display:none"><s:property value="#bean.id" /></label>
            <input type="text" 
                class="form-control ib_man_menu_name_input" 
                value='<s:property value="#bean.name" />'
                readOnly="true" />
        </td>
        <td>
            <input type="text" 
                class="form-control ib_man_menu_price_input" 
                value='<s:property value="#bean.price" />'
                readOnly="true" />
        </td>
        <td>
            <select class="form-control ib_man_menu_pic_select" disabled="disabled">
            	<s:iterator value="lstPicBean" id="bean3">
	           		<s:if test='#bean.picture==#bean3.addr'>
	               		<option value='<s:property value="#bean.picture" />' selected="selected"><s:property value="#bean3.name" /></option>
	               	</s:if>
	               	<s:else>
	               		<option value='<s:property value="#bean.picture" />'><s:property value="#bean3.name" /></option>
	              	</s:else>
              	</s:iterator>
            </select>
        </td>
        <td>
            <select class="form-control ib_man_menu_type_select" disabled="disabled">
             	<s:iterator value="lstMenuType" id="bean2">
             		<s:if test='#bean.type.id==#bean2.id'>
                		<option value='<s:property value="#bean2.id" />' selected="selected"><s:property value="#bean2.name" /></option>
                	</s:if>
                	<s:else>
                		<option value='<s:property value="#bean2.id" />'><s:property value="#bean2.name" /></option>
                	</s:else>
                </s:iterator>
            </select>
        </td>
        <td>
            <button class="btn btn-primary btn-xs" 
                id="ib_man_menu_edit_btn"
                status="edit"
                onclick='onBtnEditClick("<s:property value="#bean.id" />");'>
                <s:text name="manMenuEditBtn" />
            </button>
            <button class="btn btn-primary btn-xs" disabled="disabled"
                id="ib_man_menu_mod_btn"
                onclick='onBtnModClick("id=<s:property value="#bean.id" />&opt=menuMod&page=${currPage}");'>
                <s:text name="manMenuModBtn" />
            </button>
            <button class="btn btn-primary btn-xs"
                id="ib_man_menu_del_btn"
                onclick='onBtnDelClick("id=<s:property value="#bean.id" />&opt=menuDel&page=${currPage}");'>
                <s:text name="manMenuDelBtn" />
            </button>
        </td>
    </tr>
    </s:iterator>
</table>

<%-- the pagination --%>
<%@ include file="/WEB-INF/jsp/pagination.jsp" %>

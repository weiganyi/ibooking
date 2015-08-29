<%-- include the struct tag --%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%-- include the jstl --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table">
    <tr>
        <th><s:text name="manMenuTypeName" /></th>
        <th><s:text name="manMenuOpt" /></th>
    </tr>
    <tr>
        <td>
            <input type="text" 
                id="ib_man_menu_type_new_name_input"
                class="form-control" 
                value=""
                readOnly="true" />
        </td>
        <td>
            <button class="btn btn-primary btn-xs" 
                id="ib_man_menu_type_new_btn"
                status="new"
                onclick='onBtnNewClick();'>
                <s:text name="manMenuTypeNewBtn" />
            </button>
            <button class="btn btn-primary btn-xs" disabled="disabled"
                id="ib_man_menu_type_add_btn"
                onclick='onBtnAddClick("opt=menuTypeAdd&page=${currPage}");'>
                <s:text name="manMenuTypeAddBtn" />
            </button>
        </td>
    </tr>
    <s:iterator value="lstMenuType" id="bean">
    <tr>
        <td>
            <label class="ib_man_menu_type_id_label" style="display:none"><s:property value="#bean.id" /></label>
            <input type="text" 
                class="form-control ib_man_menu_type_name_input" 
                value='<s:property value="#bean.name" />'
                readOnly="true" />
        </td>
        <td>
            <button class="btn btn-primary btn-xs" 
                id="ib_man_menu_type_edit_btn"
                status="edit"
                onclick='onBtnEditClick("<s:property value="#bean.id" />");'>
                <s:text name="manMenuTypeEditBtn" />
            </button>
            <button class="btn btn-primary btn-xs" disabled="disabled"
                id="ib_man_menu_type_mod_btn"
                onclick='onBtnModClick("id=<s:property value="#bean.id" />&opt=menuTypeMod&page=${currPage}");'>
                <s:text name="manMenuTypeModBtn" />
            </button>
            <button class="btn btn-primary btn-xs"
                id="ib_man_menu_type_del_btn"
                onclick='onBtnDelClick("id=<s:property value="#bean.id" />&opt=menuTypeDel&page=${currPage}");'>
                <s:text name="manMenuTypeDelBtn" />
            </button>
        </td>
    </tr>
    </s:iterator>
</table>

<%-- the pagination --%>
<%@ include file="/WEB-INF/jsp/pagination.jsp" %>

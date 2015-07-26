<%-- include the struct tag --%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%-- include the jstl --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table">
    <tr>
        <th><s:text name="manOptionName" /></th>
        <th><s:text name="manOptionValue" /></th>
        <th><s:text name="manMenuOpt" /></th>
    </tr>
    <tr>
        <td>
            <input type="text" 
                id="ib_man_option_new_name_input"
                class="form-control" 
                value=""
                readOnly="true" />
        </td>
        <td>
            <input type="text" 
                id="ib_man_option_new_value_input"
                class="form-control" 
                value=""
                readOnly="true" />
        </td>
        <td>
            <button class="btn btn-primary btn-xs" 
                id="ib_man_option_new_btn"
                status="new"
                onclick='onBtnNewClick();'>
                <s:text name="manOptionNewBtn" />
            </button>
            <button class="btn btn-primary btn-xs" disabled="disabled"
                id="ib_man_option_add_btn"
                onclick='onBtnAddClick("opt=optionAdd&page=${currPage}");'>
                <s:text name="manOptionAddBtn" />
            </button>
        </td>
    </tr>
    <s:iterator value="lstOption" id="bean">
    <tr>
        <td>
            <label class="ib_man_option_id_label" style="display:none"><s:property value="#bean.id" /></label>
            <input type="text" 
                class="form-control ib_man_option_name_input" 
                value='<s:property value="#bean.name" />'
                readOnly="true" />
        </td>
        <td>
            <input type="text" 
                class="form-control ib_man_option_value_input" 
                value='<s:property value="#bean.value" />'
                readOnly="true" />
        </td>
        <td>
            <button class="btn btn-primary btn-xs" 
                id="ib_man_option_edit_btn"
                status="edit"
                onclick='onBtnEditClick("<s:property value="#bean.id" />");'>
                <s:text name="manOptionEditBtn" />
            </button>
            <button class="btn btn-primary btn-xs" disabled="disabled"
                id="ib_man_option_mod_btn"
                onclick='onBtnModClick("id=<s:property value="#bean.id" />&opt=optionMod&page=${currPage}");'>
                <s:text name="manOptionModBtn" />
            </button>
            <button class="btn btn-primary btn-xs"
                id="ib_man_option_del_btn"
                onclick='onBtnDelClick("id=<s:property value="#bean.id" />&opt=optionDel&page=${currPage}");'>
                <s:text name="manOptionDelBtn" />
            </button>
        </td>
    </tr>
    </s:iterator>
</table>

<%-- the pagination --%>
<%@ include file="/WEB-INF/jsp/pagination.jsp" %>

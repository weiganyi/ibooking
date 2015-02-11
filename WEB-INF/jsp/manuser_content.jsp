<%-- include the struct tag --%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%-- include the jstl --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table">
    <tr>
        <th><s:text name="manuserUser" /></th>
        <th><s:text name="manuserPasswd" /></th>
        <th><s:text name="manuserAuth" /></th>
        <th><s:text name="manuserTel" /></th>
        <th><s:text name="manuserAddr" /></th>
        <th><s:text name="manuserOpt" /></th>
    </tr>
    <tr>
        <td>
            <input type="text" 
                id="ib_manuser_new_user_input"
                class="form-control" 
                value=""
                readOnly="true" />
        </td>
        <td>
            <input type="text" 
                id="ib_manuser_new_passwd_input"
                class="form-control" 
                value=""
                readOnly="true" />
        </td>
        <td>
            <select id="ib_manuser_new_auth_select" class="form-control" disabled="disabled">
                <option value="customer" selected="selected"><s:text name="manuserCustomer" /></option>
                <option value="admin"><s:text name="manuserAdmin" /></option>
            </select>
        </td>
        <td>
            <input type="text" 
                id="ib_manuser_new_tel_input"
                class="form-control" 
                value=""
                readOnly="true" />
        </td>
        <td>
            <input type="text" 
                id="ib_manuser_new_addr_input"
                class="form-control" 
                value=""
                readOnly="true" />
        </td>
        <td>
            <button class="btn btn-primary btn-xs" 
                id="ib_manuser_new_btn"
                status="new"
                onclick='onBtnNewClick();'>
                <s:text name="manuserNewBtn" />
            </button>
            <button class="btn btn-primary btn-xs" disabled="disabled"
                id="ib_manuser_add_btn"
                onclick='onBtnAddClick("opt=userAdd&page=${currPage}");'>
                <s:text name="manuserAddBtn" />
            </button>
        </td>
    </tr>
    <s:iterator value="lstUserBean" id="bean">
    <tr>
        <td>
            <label class="ib_manuser_id_label" style="display:none"><s:property value="#bean.id" /></label>
            <input type="text" 
                class="form-control ib_manuser_user_input" 
                value='<s:property value="#bean.name" />'
                readOnly="true" />
        </td>
        <td>
            <input type="text" 
                class="form-control ib_manuser_passwd_input" 
                value='<s:property value="#bean.passwd" />'
                readOnly="true" />
        </td>
        <td>
            <select class="form-control ib_manuser_auth_select" disabled="disabled">
                <s:if test='<s:property value="#bean.auth" /> = "customer"'>
                    <option value="customer" selected="selected"><s:text name="manuserCustomer" /></option>
                    <option value="admin"><s:text name="manuserAdmin" /></option>
                </s:if>
                <s:else>
                    <option value="customer"><s:text name="manuserCustomer" /></option>
                    <option value="admin" selected="selected"><s:text name="manuserAdmin" /></option>
                </s:else>
            </select>
        </td>
        <td>
            <input type="text" 
                class="form-control ib_manuser_tel_input" 
                value='<s:property value="#bean.tel" />'
                readOnly="true" />
        </td>
        <td>
            <input type="text" 
                class="form-control ib_manuser_addr_input" 
                value='<s:property value="#bean.addr" />'
                readOnly="true" />
        </td>
        <td>
            <button class="btn btn-primary btn-xs" 
                id="ib_manuser_edit_btn"
                status="edit"
                onclick='onBtnEditClick("<s:property value="#bean.id" />");'>
                <s:text name="manuserEditBtn" />
            </button>
            <button class="btn btn-primary btn-xs" disabled="disabled"
                id="ib_manuser_mod_btn"
                onclick='onBtnModClick("id=<s:property value="#bean.id" />&opt=userMod&page=${currPage}");'>
                <s:text name="manuserModBtn" />
            </button>
            <button class="btn btn-primary btn-xs"
                id="ib_manuser_del_btn"
                onclick='onBtnDelClick("id=<s:property value="#bean.id" />&opt=userDel&page=${currPage}");'>
                <s:text name="manuserDelBtn" />
            </button>
        </td>
    </tr>
    </s:iterator>
</table>

<%-- the pagination --%>
<%@ include file="/WEB-INF/jsp/pagination.jsp" %>

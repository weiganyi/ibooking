<%-- include the struct tag --%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%-- include the jstl --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table">
    <tr>
        <th><s:text name="manUserUser" /></th>
        <th><s:text name="manUserPasswd" /></th>
        <th><s:text name="manUserAuth" /></th>
        <th><s:text name="manUserTel" /></th>
        <th><s:text name="manUserAddr" /></th>
        <th><s:text name="manUserOpt" /></th>
    </tr>
    <tr>
        <td>
            <input type="text" 
                id="ib_man_user_new_user_input"
                class="form-control" 
                value=""
                readOnly="true" />
        </td>
        <td>
            <input type="text" 
                id="ib_man_user_new_passwd_input"
                class="form-control" 
                value=""
                readOnly="true" />
        </td>
        <td>
            <select id="ib_man_user_new_auth_select" class="form-control" disabled="disabled">
                <option value="customer" selected="selected"><s:text name="manUserCustomer" /></option>
                <option value="admin"><s:text name="manUserAdmin" /></option>
            </select>
        </td>
        <td>
            <input type="text" 
                id="ib_man_user_new_tel_input"
                class="form-control" 
                value=""
                readOnly="true" />
        </td>
        <td>
            <input type="text" 
                id="ib_man_user_new_addr_input"
                class="form-control" 
                value=""
                readOnly="true" />
        </td>
        <td>
            <button class="btn btn-primary btn-xs" 
                id="ib_man_user_new_btn"
                status="new"
                onclick='onBtnNewClick();'>
                <s:text name="manUserNewBtn" />
            </button>
            <button class="btn btn-primary btn-xs" disabled="disabled"
                id="ib_man_user_add_btn"
                onclick='onBtnAddClick("opt=userAdd&page=${currPage}");'>
                <s:text name="manUserAddBtn" />
            </button>
        </td>
    </tr>
    <s:iterator value="lstUserBean" id="bean">
    <tr>
        <td>
            <label class="ib_man_user_id_label" style="display:none"><s:property value="#bean.id" /></label>
            <input type="text" 
                class="form-control ib_man_user_user_input" 
                value='<s:property value="#bean.name" />'
                readOnly="true" />
        </td>
        <td>
            <input type="text" 
                class="form-control ib_man_user_passwd_input" 
                value='<s:property value="#bean.passwd" />'
                readOnly="true" />
        </td>
        <td>
            <select class="form-control ib_man_user_auth_select" disabled="disabled">
                <s:if test='<s:property value="#bean.auth" /> = "customer"'>
                    <option value="customer" selected="selected"><s:text name="manUserCustomer" /></option>
                    <option value="admin"><s:text name="manUserAdmin" /></option>
                </s:if>
                <s:else>
                    <option value="customer"><s:text name="manUserCustomer" /></option>
                    <option value="admin" selected="selected"><s:text name="manUserAdmin" /></option>
                </s:else>
            </select>
        </td>
        <td>
            <input type="text" 
                class="form-control ib_man_user_tel_input" 
                value='<s:property value="#bean.tel" />'
                readOnly="true" />
        </td>
        <td>
            <input type="text" 
                class="form-control ib_man_user_addr_input" 
                value='<s:property value="#bean.addr" />'
                readOnly="true" />
        </td>
        <td>
            <button class="btn btn-primary btn-xs" 
                id="ib_man_user_edit_btn"
                status="edit"
                onclick='onBtnEditClick("<s:property value="#bean.id" />");'>
                <s:text name="manUserEditBtn" />
            </button>
            <button class="btn btn-primary btn-xs" disabled="disabled"
                id="ib_man_user_mod_btn"
                onclick='onBtnModClick("id=<s:property value="#bean.id" />&opt=userMod&page=${currPage}");'>
                <s:text name="manUserModBtn" />
            </button>
            <button class="btn btn-primary btn-xs"
                id="ib_man_user_del_btn"
                onclick='onBtnDelClick("id=<s:property value="#bean.id" />&opt=userDel&page=${currPage}");'>
                <s:text name="manUserDelBtn" />
            </button>
        </td>
    </tr>
    </s:iterator>
</table>

<%-- the pagination --%>
<%@ include file="/WEB-INF/jsp/pagination.jsp" %>

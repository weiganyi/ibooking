<%-- include the struct tag --%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%-- include the jstl --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table">
    <tr>
        <th><s:text name="manOrderDetailMenu" /></th>
        <th><s:text name="manOrderDetailPrice" /></th>
        <th><s:text name="manOrderDetailAmount" /></th>
        <th><s:text name="manOrderDetailRemark" /></th>
        <th><s:text name="manOrderDetailOpt" /></th>
    </tr>
    <tr>
        <td>
            <input type="text" 
                id="ib_man_orderdetail_new_menu_input"
                class="form-control" 
                value=""
                readOnly="true" />
        </td>
        <td>
            <input type="text" 
                id="ib_man_orderdetail_new_price_input"
                class="form-control" 
                value=""
                readOnly="true" />
        </td>
        <td>
            <input type="text" 
                id="ib_man_orderdetail_new_amount_input"
                class="form-control" 
                value=""
                readOnly="true" />
        </td>
        <td>
            <input type="text" 
                id="ib_man_orderdetail_new_remark_input"
                class="form-control" 
                value=""
                readOnly="true" />
        </td>
        <td>
            <button class="btn btn-primary btn-xs" 
                id="ib_man_orderdetail_new_btn"
                status="new"
                onclick='onBtnNewClick();'>
                <s:text name="manOrderDetailNewBtn" />
            </button>
            <button class="btn btn-primary btn-xs" disabled="disabled"
                id="ib_man_orderdetail_add_btn"
                onclick='onBtnAddClick("orderId=<s:property value="lstOrderDetail[0].orderId" />&opt=orderDetailAdd");'>
                <s:text name="manOrderDetailAddBtn" />
            </button>
        </td>
    </tr>
    <s:iterator value="lstOrderDetail" id="bean">
    <tr>
        <td>
            <label class="ib_man_orderdetail_id_label" style="display:none"><s:property value="#bean.id" /></label>
            <input type="text" 
                class="form-control ib_man_orderdetail_menu_input" 
                value='<s:property value="#bean.menuName" />'
                readOnly="true" />
        </td>
        <td>
            <input type="text" 
                class="form-control ib_man_orderdetail_price_input" 
                value='<s:property value="#bean.menuPrice" />'
                readOnly="true" />
        </td>
        <td>
            <input type="text" 
                class="form-control ib_man_orderdetail_amount_input" 
                value='<s:property value="#bean.amount" />'
                readOnly="true" />
        </td>
        <td>
            <input type="text" 
                class="form-control ib_man_orderdetail_remark_input" 
                value='<s:property value="#bean.remark" />'
                readOnly="true" />
        </td>
        <td>
            <button class="btn btn-primary btn-xs" 
                id="ib_man_orderdetail_edit_btn"
                status="edit"
                onclick='onBtnEditClick("<s:property value="#bean.id" />");'>
                <s:text name="manOrderDetailEditBtn" />
            </button>
            <button class="btn btn-primary btn-xs" disabled="disabled"
                id="ib_man_orderdetail_mod_btn"
                onclick='onBtnModClick("id=<s:property value="#bean.id" />&orderId=<s:property value="#bean.orderId" />&opt=orderDetailMod");'>
                <s:text name="manOrderDetailModBtn" />
            </button>
            <button class="btn btn-primary btn-xs"
                id="ib_man_orderdetail_del_btn"
                onclick='onBtnDelClick("id=<s:property value="#bean.id" />&orderId=<s:property value="#bean.orderId" />&opt=orderDetailDel");'>
                <s:text name="manOrderDetailDelBtn" />
            </button>
        </td>
    </tr>
    </s:iterator>
</table>

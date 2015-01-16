<%-- include the struct tag --%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%-- include the jstl --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table">
    <tr>
        <th><s:text name="shopMenu" /></th>
        <th><s:text name="shopPrice" /></th>
        <th><s:text name="shopAmount" /></th>
        <th><s:text name="shopOpt" /></th>
        <th><s:text name="shopRemark" /></th>
    </tr>
    <s:iterator value="lstShoppingBean" id="bean">
    <tr>
        <td><s:property value="#bean.menuName" /></td>
        <td><s:property value="#bean.menuPrice" /></td>
        <td><s:property value="#bean.amount" /></td>
        <td>
            <button class="btn btn-primary btn-xs" 
                 onclick='onBtnIncClick("menuName=<s:property value="#bean.menuName" />&opt=amountInc&page=${currPage}");'>
                <s:text name="shopIncBtn" />
            </button>
            <button class="btn btn-primary btn-xs" 
                onclick='onBtnDecClick("menuName=<s:property value="#bean.menuName" />&opt=amountDec&page=${currPage}");'>
                <s:text name="shopDecBtn" />
            </button>
            <button class="btn btn-primary btn-xs" 
                onclick='onBtnDelClick("menuName=<s:property value="#bean.menuName" />&opt=amountDel&page=${currPage}");'>
                <s:text name="shopDelBtn" />
            </button>
        </td>
        <td>
            <input type="text" 
                menuName='<s:property value="#bean.menuName" />' 
                remark='<s:property value="#bean.remark" />' 
                onblur='onInputRemarkChange("menuName=<s:property value="#bean.menuName" />&opt=remarkChange&page=${currPage}");'
                class="form-control ib_shop_remark_input" value="<s:property value="#bean.remark" />" />
        </td>
    </tr>
    </s:iterator>
</table>
</br>
<div class="input-group">
    <span id="ib_shop_price_span" class="input-group-addon"><s:text name="shopTotalPrice" /></span>
    <input type="text" 
        address='<s:property value="totalPrice" />' 
        id="ib_shop_price_input"
        class="form-control" 
        value="<s:property value="totalPrice" />"
        readOnly="true" />
</div>
</br>
<div class="input-group">
    <span id="ib_shop_addr_span" class="input-group-addon"><s:text name="shopAddress" /></span>
    <input type="text" 
        address='<s:property value="strAddress" />' 
        id="ib_shop_addr_input"
        class="form-control" 
        onblur='onInputAddrChange("opt=addrChange&page=${currPage}");'
        value="<s:property value="strAddress" />" />
</div>
</br>
<div id="ib_shop_submit">
    <a href="shoppingSubmit" class="btn btn-primary" role="button">
        <s:text name="shopSubmitBtn" />
    </a>
</div>

<%-- the pagination --%>
<%@ include file="/WEB-INF/jsp/pagination.jsp" %>

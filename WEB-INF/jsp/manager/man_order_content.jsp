<%-- include the struct tag --%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%-- include the jstl --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table">
    <tr>
        <th><s:text name="manOrderId" /></th>
        <th><s:text name="manOrderUser" /></th>
        <th><s:text name="manOrderTime" /></th>
        <th><s:text name="manOrderStatus" /></th>
        <th><s:text name="manOrderTel" /></th>
        <th><s:text name="manOrderAddr" /></th>
        <th><s:text name="manOrderOpt" /></th>
    </tr>
    <s:iterator value="lstOrderBean" id="bean" status="st">
    <tr>
        <td>
            <a href='manOrderDetailPageEnter?orderId=<s:property value="#bean.id" />'>
                <s:property value="#bean.id" />
            </a>
        </td>
        <td><s:property value="#bean.userName" /></td>
        <td><s:property value="#bean.time" /></td>
        <s:if test="#bean.accept == 1">
            <td><s:text name="manOrderAccept" /></td>
        </s:if>
        <s:else>
            <td><s:text name="manOrderUnAccept" /></td>
        </s:else>
        <td><s:property value="#bean.tel" /></td>
        <td><s:property value="#bean.addr" /></td>
        <td>
        <s:if test="#bean.accept == 1">
            <button class="btn btn-primary btn-xs" 
                onclick='onBtnChangeClick("id=<s:property value="#bean.id" />&opt=orderUnAccept&page=${currPage}");'>
                <s:text name="manOrderUnAcceptBtn" />
            </button>
        </s:if>
        <s:else>
            <button class="btn btn-primary btn-xs" 
                onclick='onBtnChangeClick("id=<s:property value="#bean.id" />&opt=orderAccept&page=${currPage}");'>
                <s:text name="manOrderAcceptBtn" />
            </button>
        </s:else>
            <button class="btn btn-primary btn-xs" 
                onclick='onBtnChangeClick("id=<s:property value="#bean.id" />&opt=orderDel&page=${currPage}");'>
                <s:text name="manOrderDelBtn" />
            </button>
        </td>
    </tr>
    </s:iterator>
</table>
</br>

<%-- the pagination --%>
<%@ include file="/WEB-INF/jsp/pagination.jsp" %>

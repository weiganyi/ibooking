<%-- include the struct tag --%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%-- include the jstl --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table">
    <tr>
        <th><s:text name="orderlistSeq" /></th>
        <th><s:text name="orderlistTime" /></th>
        <th><s:text name="orderlistStatus" /></th>
        <th><s:text name="orderlistOpt" /></th>
    </tr>
    <s:iterator value="lstOrderListBean" id="bean" status="st">
    <tr>
        <td>
            <a href='orderDetailPageEnter?orderId=<s:property value="#bean.id" />'>
                <s:property value="#bean.seq" />
            </a>
        </td>
        <td><s:property value="#bean.time" /></td>
        <s:if test="#bean.accept == 1">
            <td><s:text name="orderlistAccept" /></td>
        </s:if>
        <s:else>
            <td><s:text name="orderlistUnAccept" /></td>
        </s:else>
        <s:if test="#bean.accept == 0">
            <td>
                <button class="btn btn-primary btn-xs" 
                    onclick='onBtnDelClick("orderId=<s:property value="#bean.id" />&opt=orderDel&page=${currPage}");'>
                    <s:text name="orderlistDelBtn" />
                </button>
            </td>
        </s:if>
    </tr>
    </s:iterator>
</table>
</br>

<%-- the pagination --%>
<%@ include file="/WEB-INF/jsp/pagination.jsp" %>

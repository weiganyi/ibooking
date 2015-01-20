<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <%-- the header --%>
        <%@ include file="/WEB-INF/jsp/header.jsp" %>

        <title><s:text name="orderdetailTitle" /></title>
    </head>
    <body>
        <%-- the navbar --%>
        <%@ include file="/WEB-INF/jsp/navbar.jsp" %>

        <%-- include the struct tag --%>
        <%@taglib prefix="s" uri="/struts-tags" %>
        
        <%-- include the jstl --%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        
        <table class="table">
            <tr>
                <th><s:text name="orderdetailMenu" /></th>
                <th><s:text name="orderdetailPrice" /></th>
                <th><s:text name="orderdetailAmount" /></th>
                <th><s:text name="orderdetailRemark" /></th>
            </tr>
            <s:iterator value="lstOrderDetail" id="bean" status="st">
            <tr>
                <td><s:property value="#bean.menuName" /></td>
                <td><s:property value="#bean.menuPrice" /></td>
                <td><s:property value="#bean.amount" /></td>
                <td><s:property value="#bean.remark" /></td>
            </tr>
            </s:iterator>
        </table>

    </body>
</html>
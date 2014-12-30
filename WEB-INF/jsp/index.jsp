<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <%-- include the struct tag --%>
        <%@taglib prefix="s" uri="/struts-tags" %>
        
        <%-- include the jstl --%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <title><s:text name="indexTitle" /></title>

        <link type="text/css" href="../res/bootstrap.min.css" rel="stylesheet">
        <link type="text/css" href="../res/ibooking.css" rel="stylesheet">

        <script type="text/javascript" src="../res/jquery-1.10.1.js"></script>
        <script type="text/javascript" src="../res/bootstrap.min.js"></script>
        <script type="text/javascript" src="../res/ibooking.js"></script>
    </head>
    <body>
        <%-- the navbar --%>
        <%@ include file="/WEB-INF/jsp/navbar.jsp" %>

        <s:iterator value="lstMenuTypeBean" id="bean" status="st">
        <div class="row">
            <h2 class="ib_idx_caption_h"><s:property value="#bean.name" /></h2>
            <s:iterator value="#bean.lst" id="bean2">
            <div class="col-md-3">
                <div class="thumbnail">
                    <img src="<s:property value="#bean2.addr" />">
                    <h4 class="ib_idx_caption_h"><s:property value="#bean2.name" /></h4>
                    <h5 class="ib_idx_caption_h">
                        <s:text name="indexPrice" />: <s:property value="#bean2.price" />
                    </h5>
                    <p class="ib_idx_caption_h">
                        <button id="ib_idx_select_btn" class="btn btn-primary">
                            <s:text name="indexSelectBtn" />
                        </button>
                    </p>
                </div>
            </div>
            </s:iterator>
        </div>
        </s:iterator>

        <%-- the pagination --%>
        <%@ include file="/WEB-INF/jsp/pagination.jsp" %>

    </body>
</html>
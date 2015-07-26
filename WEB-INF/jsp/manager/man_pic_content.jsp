<%-- include the struct tag --%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%-- include the jstl --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<s:form action="manPicUpload" enctype="multipart/form-data">
		<s:textfield name="title" key="manPicFileTitle" />
		<s:file name="upload" key="manPicSelectFile" />
		<s:submit value="UpLoad" />
	</s:form>
</div>

</br></br>

<div class="row">
<s:iterator value="lstPicBean" id="bean">
    <div class="col-md-3">
        <div class="thumbnail">
            <img src="<s:property value="#bean.addr" />">
            <h4 class="ib_man_pic_caption_h"><s:property value="#bean.name" /></h4>
            <p class="ib_man_pic_caption_h">
                <button class="btn btn-primary" 
                    id="ib_man_pic_del_btn"
                    onclick='onBtnDelClick("name=<s:property value="#bean.name" />&addr=<s:property value="#bean.addr" />&opt=picDel&page=${currPage}");'>
                    <s:text name="manPicDelBtn" /></span>
                </button>
            </p>
        </div>
    </div>
</s:iterator>
</div>

<%-- the pagination --%>
<%@ include file="/WEB-INF/jsp/pagination.jsp" %>

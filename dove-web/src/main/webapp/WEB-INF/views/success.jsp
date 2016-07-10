<%@ page contentType="text/html;charset=UTF-8"%>

<%String cxtPath=request.getContextPath();%>
<%response.getWriter().write((String)request.getAttribute("htmls")); %>

<%--
<title>success</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
 --%>
<!-- jslib -->
<%-- <script type="text/javascript" src="<%=cxtPath%>/resources/jslib/jquery/jquery-1.7.2.min.js" charset="utf-8"></script> --%>
<script type="text/javascript" src="<%=cxtPath%>/resources/jslib/base64.min.js" charset="utf-8"></script>

<!-- js -->
<script type="text/javascript" src="<%=cxtPath%>/resources/js/web-base.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=cxtPath%>/resources/js/func-binder.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=cxtPath%>/resources/js/data-validator.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=cxtPath%>/resources/js/ec-datagrid.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=cxtPath%>/resources/js/dove-base.js" charset="utf-8"></script>
<%-- APP auto JS begin --%>
<% Object _jsUri=request.getAttribute("js_uri");
if(_jsUri!=null){ %>
<script type="text/javascript" src="<%=cxtPath+(String)_jsUri%>" charset="utf-8"></script>
<% } %>
<%-- APP auto JS end --%>

<script type="text/javascript" charset="utf-8">
	//set context path
	WebBase.cxtPath='<%=cxtPath%>';
	// set datas
	WebBase.datas='<%=(String)request.getAttribute("datas")%>';
	//
	window.onload=function(){
	  	// resize route e
	    $(".route").width($('#dataTable').width());
	}
	
</script>

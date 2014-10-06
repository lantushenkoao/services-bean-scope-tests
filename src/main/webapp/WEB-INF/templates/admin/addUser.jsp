<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<link rel="stylesheet" href='<c:url value="/css/base.css" />'/>
	<link rel="stylesheet" href="<c:url value="/js/libs/jquery/jquery-ui.min.css"/>"/>
	
	<script charset="utf-8" type="text/javascript" src="<c:url value="/js/libs/jquery/jquery.js"/>"></script>
</head>
<body>
<jsp:include page="../includes/frame_header.jsp"/>
<h1>Add User</h1>
<form method="post">
<jsp:include page="manageUserFragment.jsp">
	<jsp:param name="isLoginEditable" value="true"/>
</jsp:include>
<table>
	<tr>
		<td colspan="2">
			<a href="javascript:void(0);" onclick="$(this).closest('form').submit();" class="button">Add User</a>
			<a href="javascript:void(0);" onclick="window.history.back();" class="button">Cancel</a>
		</td>
	</tr>
</table>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<jsp:include page="../includes/frame_footer.jsp"/>
</body>

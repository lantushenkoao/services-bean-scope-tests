<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<link rel="stylesheet" href='<c:url value="/css/base.css" />'/>
	<link rel="stylesheet" href="<c:url value="/js/libs/jquery/jquery-ui.min.css"/>"/>
	
	<script charset="utf-8" type="text/javascript" src="<c:url value="/js/libs/jquery/jquery.js"/>"></script>
</head>
<body>
<jsp:include page="../includes/frame_header.jsp"/>
<h1>Edit User</h1>
<form method="post" action="${requestScope['javax.servlet.forward.request_uri']}">
<input type="hidden" name="userId" value="${user.id}"/>
<jsp:include page="manageUserFragment.jsp">
	<jsp:param name="isLoginEditable" value="false"/>
</jsp:include>
<table>
	<tr>
		<td colspan="2">
			<a href="javascript:void(0);" onclick="$(this).closest('form').submit();" class="button">Save</a>
			<a href="javascript:void(0);" onclick="window.history.back();" class="button">Cancel</a>
		</td>
	</tr>
</table>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<jsp:include page="../includes/frame_footer.jsp"/>
</body>

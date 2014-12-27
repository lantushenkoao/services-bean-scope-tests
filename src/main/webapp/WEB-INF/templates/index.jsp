<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
	<jsp:include page="includes/head.jsp"/>
</head>
<body>
	<jsp:include page="includes/frame_header.jsp"/>
	<h1><spring:message code="home"/></h1>
	
	<jsp:include page="includes/frame_footer.jsp"/>
</body>
</html>
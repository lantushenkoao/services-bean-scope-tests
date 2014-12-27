<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<link rel="stylesheet" href='<c:url value="/css/base.css" />'/>
</head>
<body>
 
	<h1>System Error</h1>
 
	<c:if test="${not empty exception.message}">
		<h4>${exception.message}</h4>
	</c:if>
	<p>
	<c:forEach var="stackTraceElem" items="${exception.stackTrace}">
		<c:out value="${stackTraceElem}"/><br/>
	</c:forEach>
	</p>
</body>
</html>
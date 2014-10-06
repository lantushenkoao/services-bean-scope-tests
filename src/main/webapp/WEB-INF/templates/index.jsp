<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<link rel="stylesheet" href='<c:url value="/css/base.css" />'/>
</head>
<body>
	<jsp:include page="includes/frame_header.jsp"/>
	<h1>Hello</h1>
	<a href="<c:url value='/login'/>">Login</a>
	<jsp:include page="includes/frame_footer.jsp"/>
</body>
</html>
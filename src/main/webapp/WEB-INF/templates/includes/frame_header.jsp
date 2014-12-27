<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="topBar">
<sec:authentication property="principal" var="p"/>
<ul>
	<li><a href="<c:url value="/" />"><spring:message code="home"/></a></li>
	<li><a href="<c:url value="/static/alphabet.html" />"><spring:message code="alphabet"/></a></li>
<c:choose>
	<c:when test="${p != 'anonymousUser'}">
		<li><a href="<c:url value="/myaccount" />"><spring:message code="myaccount" /></a></li>
		<sec:authorize ifAllGranted="ROLE_MANAGE_USERS" >
		<li>
		<a href="<c:url value='/admin/listUsers' />"><spring:message code="manageUsers" /></a>
		</li>
		</sec:authorize>
		<li>
		<form action='<c:url value="/sign-out"/>' method="post" name="logoutFrm" class="logout">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<a href="javascript:void(0);" onclick="javascript:document.logoutFrm.submit();" id="logOut"><spring:message code="logout" /></a>
		</form>
		</li>
	</c:when >
	<c:otherwise>
		<li>
		<a href="<c:url value='/login'/>"><spring:message code="login"/></a>
		</li>
	</c:otherwise>
</c:choose>
</ul>
</div>
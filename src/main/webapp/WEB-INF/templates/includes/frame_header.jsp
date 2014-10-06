<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="topBar">
<span class="header">${appName}</span>
<sec:authentication property="principal" var="p"/>
<ul>
	<li><a href="<c:url value="/" />">Home</a></li>
<c:if test="${p != 'anonymousUser'}">
	<li><a href="<c:url value="/myaccount" />">My Account</a></li>
	<sec:authorize ifAllGranted="ROLE_MANAGE_USERS" >
	<li>
	<a href="<c:url value='/admin/listUsers' />">Manage users</a>
	</li>
	</sec:authorize>
	<li>
	<form action='<c:url value="/sign-out"/>' method="post" name="logoutFrm" class="logout">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	<a href="javascript:void(0);" onclick="javascript:document.logoutFrm.submit();" id="logOut">Log out</a>
	</form>
	</li>
</c:if>
</ul>
</div>
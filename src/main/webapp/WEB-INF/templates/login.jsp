<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<meta charset="utf-8">
<title><spring:message code="loginPage.title"/></title>
<link rel="stylesheet" href="css/base.css">
<style>
#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
</head>
<body onload='document.loginForm.username.focus();'>
 
	<div id="login-box">
 
		<h3><spring:message code="login" /></h3>
 
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
 
		<form name='loginForm'
		    action="<c:url value='j_spring_security_check' />" method='POST'>
 
		    <table>
			<tr>
				<td><spring:message code="user"/>:</td>
				<td><input type='text' name='uname' value=''></td>
			</tr>
			<tr>
				<td><spring:message code="password"/>:</td>
				<td><input type='password' name='upwd' /></td>
			</tr>
			<tr>
				<td colspan='2'>
					<input name="submit" type="submit" value='<spring:message code="login" />' />
				</td>
			</tr>
		   </table>
 
		   <input type="hidden" 
                     name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</div>
 
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table>
<tr>
	<td>Login</td>
	<td><input type="text" name="login" <c:if test="${not isLoginEditable}">readonly="readonly"</c:if> value="${user.login}"/></td>
</tr>
<tr>
	<td>Full Name</td>
	<td><input type="text" name="fullName" value="${user.fullName}"/></td>
</tr>
<tr>
	<td>Password</td>
	<td><input type="password" name="password"></td>
</tr>
</table>
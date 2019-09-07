<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"  isELIgnored="false"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" href="issuer/resources/css/index.css" rel="stylesheet"> 

</head>
<body>
	<form id="logout" action="logout" method="post">
		<input value="Logout" type="submit">
	</form>
	<sec:csrfInput />
</body>
</html>
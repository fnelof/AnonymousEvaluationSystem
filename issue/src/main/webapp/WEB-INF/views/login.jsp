<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ticket issuer</title>
<script type="text/javascript" src="/issue/webjars/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="/issue/webjars/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<link  type="text/css" href="/issue/webjars/bootstrap/4.1.0/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="/issue/resources/css/login.css" rel="stylesheet">
</head>
<body>
   <div class="modal-dialog">
   	<div class="modal-content">
   		<div class="modal-heading">
   			<h2>Ticket Issuer Login</h2>
   		</div>
   		<div class="modal-body">
   			<form action="loginAction" method="post">
   				<div class="form-group">
		   			<span class="glyphicon glyphicon-user"></span>
		   			<input type="text" class="form-control" placeholder="Username" name="username">
   				</div>
   				<div class="form-group">
		   			<span class="glyphicon glyphicon-user"></span>
		   			<input type="password" class="form-control" placeholder="Password" name="password">
   				</div>
   				<button type="submit" class="btn btn-success btn-block">Log in</button>
   			</form>
   		</div>
   	</div>
   </div>
</body>
</html>
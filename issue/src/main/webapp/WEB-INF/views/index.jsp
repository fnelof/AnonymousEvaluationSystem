<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"  isELIgnored="false"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ticket issuer</title>

<link  type="text/css" href="/issue/webjars/bootstrap/4.1.0/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="/issue/resources/css/index.css" rel="stylesheet">
<script type="text/javascript" src="/issue/webjars/popper.js/1.14.1/umd/popper.min.js"></script>
<script type="text/javascript" src="/issue/webjars/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="/issue/webjars/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/issue/webjars/murmurhash-js/1.0.0/murmurhash3_gc.js"></script>
<script type="text/javascript" src="/issue/resources/js/index.js"></script>
<script type="text/javascript" src="/issue/webjars/jsbn/0.1.0/index.js"></script>
</head>
<body>
	<form id="logout" action="logout" method="post">
		<input class="btn btn-danger" value="Logout" type="submit">
		<sec:csrfInput />
	</form>
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-heading">
				<h3>Issue a ticket</h3>
			</div>
			<div class="modal-body">
				<input class="form-control" type="text" placeholder="Message (m)" name="message" id="message" readOnly>
				<button class="btn btn-info btn-block" id="generateMessageButton">Generate message (m)</button><br>
				<div class="dropdown">
  					<button class="btn btn-secondary btn-block dropdown-toggle" type="button" id="courseDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    					Course
  					</button><br>
  					<ul class="dropdown-menu" id="courseDropdownMenu" aria-labelledby="dropdownMenuButton">
 					</ul>
				</div>
				<div class="dropdown">
  					<button class="btn btn-secondary btn-block dropdown-toggle" type="button" id="instructorDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    					Instructor
  					</button>
  					<ul class="dropdown-menu" id="instructorDropdownMenu" aria-labelledby="dropdownMenuButton">
 					</ul>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-success btn-block" id="requestButton" type="button" disabled>Request a ticket</button>
			</div>
		</div>
	</div>
	
	<div class="result alert alert-success" id="result"></div>
	<div class="alert alert-danger" role="alert" id="error"></div>
	<div id="alertBox" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					Signed ticket copied to clipboard!
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary btn-block" data-dismiss="modal">OK</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
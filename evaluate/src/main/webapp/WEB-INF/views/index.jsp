<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"  isELIgnored="false"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voter</title>
<script type="text/javascript" src="/evaluate/webjars/jquery/2.1.4/jquery.min.js"></script>

<script type="text/javascript" src="/evaluate/resources/js/index.js"></script>
<link  type="text/css" href="/evaluate/webjars/bootstrap/4.1.0/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/evaluate/webjars/popper.js/1.14.1/umd/popper.min.js"></script>
<script type="text/javascript" src="/evaluate/webjars/bootstrap/4.1.0/js/bootstrap.min.js"></script>

<link type="text/css" href="/evaluate/resources/css/index.css" rel="stylesheet">

<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
	
	<h1>Evaluation Form</h1>
	<div id="questionnaire"></div><hr>
	<h3>Additional comments about the instructor and his course</h3>
	<textarea id="comment" class="form-control" rows="5" cols="100"></textarea><br>
	<div class="modal" id="voteModal">
		<div class="modal-content">			
			<div class="modal-heading">
			    <button type="button" class="close" aria-label="Close"><span aria-hidden="true">&times;</span></button><br>
				<h3>Anonymous Evaluation</h3>
			</div>
			
			<div class="modal-body">
				<input class="form-control" type="text" placeholder="Type your message (m)" name="message" id="message">
				<input class="form-control" type="text" placeholder="Enter your signed ticket" name="signedTicket" id="signedTicket">	
				<div class="dropdown">
  					<button class="btn btn-secondary btn-block dropdown-toggle" type="button" id="departmentDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    					Department
  					</button>
  					<ul class="dropdown-menu" id="departmentDropdownMenu" aria-labelledby="dropdownMenuButton">
 					</ul>
				</div>
				<div class="dropdown" id="syllabusDropdown">
  					<button class="btn btn-secondary btn-block dropdown-toggle" type="button" id="syllabusDropdownButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    					Syllabus
  					</button>
  					<ul class="dropdown-menu" id="syllabusDropdownMenu" aria-labelledby="syllabusDropdownMenuButton">
 					</ul>
				</div>
				<div class="dropdown" id="courseDropdown">
  					<button class="btn btn-secondary btn-block dropdown-toggle" type="button" id="courseDropdownButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" value="">
    					Course
  					</button>
  					<ul class="dropdown-menu" id="courseDropdownMenu" aria-labelledby="courseDropdownMenuButton">
 					</ul>
				</div>
				<div class="dropdown" id="instructorDropdown">
  					<button class="btn btn-secondary btn-block dropdown-toggle" type="button" id="instructorDropdownButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    					Instructor
  					</button>
  					<ul class="dropdown-menu" id="instructorDropdownMenu" aria-labelledby="instructorDropdownMenuButton">
 					</ul>
				</div>							
			</div>
			<div class="modal-footer">
				<button class="btn btn-success btn-block" id="voteButton" type="button">Vote</button>
			</div>
		</div>
	</div>
	<button type="button" class="btn btn-primary" id="showModalButton">Choose instructor and vote</button>
	
	<div class="alert alert-success" role="alert" id="success"></div>		
	<div class="alert alert-danger" role="alert" id="error"></div>
	<sec:csrfInput />
</body>
</html>
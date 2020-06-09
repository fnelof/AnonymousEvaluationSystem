
$(document).ready(function() {

	var data = {
		"courseName": "",
		"instructorName": "",
		"p1": 0,
		"p2": 5
	};
	var tableData = [];

	$.ajax({
		url: "getCourseInstructorPagination",
		type: "get",
		datatype: "json",
		data: data,
		success: function(result){
			tableData = result;
			tableData.forEach(function(item,index) {
				appendToTable(item,index);
			});
		}
	});

	function appendToTable(item,index){
		index++;
		$("#tableBody").append("<tr>" +
			"<td>"+ index +"</td>" +
			"<td>" + item["department"] + "</td>" +
			"<td>" + item["syllabus"] + "</td>" +
			"<td>" + item["courseTitle"] + "</td>" +
			"<td>" + item["instructorFirstName"] + " " + item["instructorLastName"] + ", " + item["instructorTitle"] + "</td>" +
			"<td><button type='button' value='"+ index +"' class='btn btn-light goToAttendanceButton'>Create Attendance Chain</button></td>" +
			"<td>" +
			"	<div class='hidden' id='course" + index + "'>" + item["courseId"] + "</div>" +
			"	<div class='hidden' id='instructor" + index + "'>" + item["instructorId"] + "</div>" +
			"</td>" +
			"</tr>");
	}

	$(document).on("click",".goToAttendanceButton",function(){
		var index = $(this).val();
		var courseId = $("#course" + index).text();
		var instructorId = $("#instructor" + index).text();
		if(courseId && instructorId){
			window.location = '/createAttendanceForm?instructor=' + instructorId + '&course=' + courseId;
		}
	});

});

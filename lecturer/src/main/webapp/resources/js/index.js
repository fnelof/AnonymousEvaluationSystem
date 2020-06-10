
$(document).ready(function() {
	$("#attendanceChain").hide();
	var initialPaginationData = {
		"courseName": "",
		"instructorName": "",
		"p1": 0,
		"p2": 5
	};
	var tableData = [];
	var totalLectures = 0;
	var courseId;
	var instructorId;

	$.ajax({
		url: "getCourseInstructorPagination",
		type: "get",
		datatype: "json",
		data: initialPaginationData,
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
		courseId = $("#course" + index).text();
		instructorId = $("#instructor" + index).text();
		$("#lecturerGrid").hide();

		if(courseId && instructorId){
			var courseTotalLecturesRequestBody = {
				"courseId": courseId
			};

			$.ajax({
				url: "getCourseTotalLectures",
				type: "get",
				datatype: "json",
				data: courseTotalLecturesRequestBody,
				success: function(result){
					totalLectures = result;
				}
			});
		}
	});

});

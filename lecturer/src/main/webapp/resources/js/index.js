
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
			debugger;
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
			"</tr>");
	}

});

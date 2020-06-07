
$(document).ready(function() {
	console.log("ready");
	var data = {
		"courseName": "",
		"instructorName": "",
		"p1": 0,
		"p2": 5
	};
	$.ajax({
		url: "getCourseInstructorPagination",
		type: "get",
		datatype: "json",
		data: data,
		success: function(result){
			console.log(result);
			debugger;
		}
	});
	
});

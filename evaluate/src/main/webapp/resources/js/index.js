
$(document).ready(function() {

	//include csrf token on request headers
	$.ajaxSetup({
		beforeSend: function(xhr) {
			xhr.setRequestHeader('X-Csrf-Token', $('input[name="_csrf"]').attr('value'));
		}
	});

	var syllabusList = [];
	var courseList = [];
	var instructorList = [];
	var course_id,instructor_id;
	
	
	
	$("#error").hide();
	$("#success").hide();
	$("#voteModal").hide();
	// get departments list
	$.ajax({
		  url: "getDepartments",
		  type: "get",
		  datatype: "json",
		  success: function(result){
			  var data = JSON.parse(result);
			  var departments = data.departmentList;
			  $("#departmentDropdownMenu").empty();
			  $.each(departments,function(index,value){
					$("#departmentDropdownMenu").append("<li value='"+ value["name"]+"'><a class='dropdown-item department' href='#'>" + value["name"]+"</a></li>");
			  })
		  }
	});
	
	// get questionnaire for evaluation
	
	$.ajax({
		url: "getQuestionnaire",
		type:"get",
		datatype:"json",
		success:function(result){
			var data = JSON.parse(result);
			var questionnaire = data["questionnaire"];
			$.each(questionnaire,function(index,value){
				$("#questionnaire").append("<h2>"+value["question"]+"</h2><br>" +
						"<div class='row checkGroup'>" +
						"<div class='col-md-12 col-lg-2 checkDiv'><input type='checkbox' class='checkInput' name='"+ value["id"]+"' value=1>Poor</div>" +
						"<div class='col-md-12 col-lg-2 checkDiv'><input type='checkbox' class='checkInput' name='"+ value["id"]+"' value=2>Fair</div>" +
						"<div class='col-md-12 col-lg-2 checkDiv'><input type='checkbox' class='checkInput' name='"+ value["id"]+"' value=3>Satisfactory</div>" +
						"<div class='col-md-12 col-lg-2 checkDiv'><input type='checkbox' class='checkInput' name='"+ value["id"]+"' value=4>Good</div>" +
						"<div class='col-md-12 col-lg-2 checkDiv'><input type='checkbox' class='checkInput' name='"+ value["id"]+"' value=5>Excellent</div></div>")
			});
		}
	});
	
	$("#syllabusDropdown").hide();
	$("#courseDropdown").hide();
	$("#instructorDropdown").hide();
	// add dropdown-menu li a event to change button value on selecting
	$(document).on('click','.dropdown-menu li a',function(){
		$(this).parents(".dropdown").find('.btn').html($(this).text() + ' <span class="caret"></span>');
		//$(this).parents(".dropdown").find('.btn').val($(this).data('value'));
	});
	
	
	//get selected department's syllabus list

	$(document).on('click','.department',function(){
		var dept = $(this).text();
		$.get("getSyllabus",{department: dept},
			function(result){
				var data = JSON.parse(result);
				syllabusList = data.syllabusList;

				$("#syllabusDropdownMenu").empty();
				$.each(syllabusList,function(index,value){
					$("#syllabusDropdownMenu").append("<li id='"+value["name"].replace(/\s/g, '')+"' value='"+value["id"]+"'><a class='dropdown-item syllabus' href='#'>"+
							value["name"]+"</a></li>");
				});
				
				// Reset syllabus,course,instructor button and values
				
				$("#syllabusDropdownButton").html("Syllabus");
				
				$("#courseDropdownButton").html("Course");
				$("#courseDropdown").hide();
				
				$("#instructorDropdownButton").html("Instructor");
				$("#instructorDropdown").hide();
				
				// Show syllabus dropdown
				$("#syllabusDropdown").show();

		});
	});
	
	// get syllabus courses & instructors
	
	$(document).on('click','.syllabus',function(){
		var syllabus = $(this).text();
		var id=$("#"+syllabus.replace(/\s/g, '')).val();
		$.get("getCourses",{syllabusId:id},
			function(result){
				var data = JSON.parse(result);
				courseList = data["courseList"];
				$("#courseDropdownMenu").empty();
				$.each(courseList,function(index,value){
					$("#courseDropdownMenu").append("<li id='"+value["title"].replace(/\s/g,'')+"' value='"+value["id"]+"'><a class='dropdown-item course' href='#'>"+
							value["title"]+"</a></li>");
				});
		});
		
		// Reset courses and instructors
		
		$("#courseDropdownButton").html("Course");
		
		$("#instructorDropdownButton").html("Instructor");
		$("#instructorDropdown").hide();
		
		$("#courseDropdown").show();
	});
	
	// get instructors of the course
	
	$(document).on('click','.course',function(){
		var title=$(this).text();
		var id = $("#"+title.replace(/\s/g,'')).val();
		$.each(courseList,function(index,value){
			if(value["id"]===id){
				instructorList = value["instructorList"];
			}
		});
		$("#instructorDropdownMenu").empty();
		$.each(instructorList,function(index,value){
			$("#instructorDropdownMenu").append("<li id='"+value["name"].replace(/\s/g,'')+"' value='"+value["id"]+"'><a class='dropdown-item instructor' href='#'>"+
					value["name"] + " - " + value["title"]+"</a></li>");
		});
		course_id = id;
		
		// Reset instructor's button
		
		$("#instructorDropdownButton").html("Instructor");
		
		$("#instructorDropdown").show();

	});
	
	// set id of instructor
	$(document).on('click','.instructor',function(){
		instructor_id = $(this).parents("li").val();
	});
	
	
	// submit questionnaire
	
	$(document).on('click','#voteButton',function() {
		var m = JSON.parse($("#message").val());
		var ticketChain = getTicketChain(m);
		//m["EAT"] = hexToBn('0' + m["EAT"].toString()).toString();
		// check if signed ticket is empty
		if($("#signedTicket").val()===""){
			$("#error").html("<i class='material-icons'>warning</i><br>Signed ticket cannot be empty");
		    $("#error").show();
		    $('#error').delay(3000).fadeOut('slow');
		    return;
		}

		// check if message is empty
		if($("#message").val()===""){
			$("#error").html("<i class='material-icons'>warning</i><br>Message cannot be empty");
		    $("#error").show();
		    $('#error').delay(3000).fadeOut('slow');
		    return;
		}

		// create evaluation object
		var evaluation = {};
		var evalList=[];
		$("input:checkbox").each(function(){

			if($(this).is(":checked")){
				var name = parseInt($(this).attr("name"));
				var v = parseInt($(this).attr("value"));
				var evaluationObject = {};
				evaluationObject.id = name;
				evaluationObject.vote = v;
				evalList.push(evaluationObject);
			}
		});
		evaluation.voteList = evalList;
		var comment = $("#comment").val();
		var data = {
			"courseId": course_id,
			"instructorId": instructor_id,
			"ticketChain": ticketChain,
			"initialTicket": m["initialTicket"],
			"EAT": m["EAT"],
			"eval": JSON.stringify(evaluation),
			"comment": comment
		};

		$.ajax({
			type: "post",
			url: "vote",
			datatype: "json",
			data: data,
			success: function (result) {
				result = JSON.parse(result);
				if (result.hasOwnProperty("success")) {
					$("#success").html("<i class='material-icons'>check box</i><br>" + result.success);
					$("#success").show();
					$("#success").delay(3000).fadeOut('slow');
				}
				if (result.hasOwnProperty("error")) {
					$("#error").html("<i class='material-icons'>warning</i><br>" + result.error);
					$("#error").show();
					$("#error").delay(3000).fadeOut('slow');
				}
			}
		});
	});
	$("#showModalButton").click(function(){
		// check if questionnaire is filled
		
		if ($('.checkGroup:not(:has(:checkbox:checked))').length) {
		    $("#error").html("<i class='material-icons'>warning</i><br>At least one question is not answered");
		    $("#error").show();
		    $('#error').delay(3000).fadeOut('slow');
		    
		    return;
		}
		$("#voteModal").show();
		$("#voteModal").focus();
	
	});
	// allow only one checkbox to be selected
	$(document).on('click',"input:checkbox",function(){
		var $box = $(this);
		  if ($box.is(":checked")) {
		    var group = "input:checkbox[name='" + $box.attr("name") + "']";

		    $(group).prop("checked", false);
		    $box.prop("checked", true);
		  } else {
		    $box.prop("checked", false);
		  }
	});
	$(".close").click(function(){
		$("#voteModal").hide();
	})

	function getTicketChain(obj){
		var result = [];
		//Last two values of object are the EAT and initialTicket.
		for(var i = 0; i < Object.values(obj).length-2;i++){
			result.push(hexToBn('0'+Object.values(obj)[i]).toString());
		}
		return result;
	}

	function hexToBn(hex) {
		if (hex.length % 2) {
			hex = '0' + hex;
		}

		var highbyte = parseInt(hex.slice(0, 2), 16)
		var bn = BigInt('0x' + hex);

		if (0x80 & highbyte) {

			bn = BigInt('0b' + bn.toString(2).split('').map(function (i) {
				return '0' === i ? 1 : 0
			}).join('')) + BigInt(1);
			bn = -bn;
		}
		return bn;
	}
});

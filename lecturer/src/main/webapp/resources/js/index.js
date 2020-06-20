
$(document).ready(function() {
	$("#attendanceChain").hide();
	$("#signatureButton").hide();
	$("#result").hide();
	$("#form").hide();
	var initialPaginationData = {
		"courseName": "",
		"instructorName": "",
		"p1": 0,
		"p2": 5
	};
	var tableData = [];
    var selectedLectures = [];
    var ticketChain = [];
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
					if(totalLectures){
						$("#attendanceChain").show();
						$("#signatureButton").show()
						$("#form").hide()
						$("#lecturerGrid").hide();
                        $("#attendance").empty();
                        for(var i=0;i<totalLectures;i++){
                            $("#attendanceHashChain").append("<div class=\"row lectureRow\">" +
                                "<label class=\"form-check-label hashLabel col-sm-2\">\n" +
                                "     <input type=\"checkbox\" class=\"form-check-input\" id = \"lecture" + i + "\" name=\"lecture\" value=\"" + i + "\">Lecture " + (i + 1) + "\n" +
                                "</label>\n" +
                                "<input type=\"text\" class=\"form-control col-sm-10\" id=\"lectureHash"+i+"\">\n" +
                                "</div>\n"
                            )

                        }
					}


				}
			});
		}
	});

	$(document).on('click', "#populateHashChain" ,function(){

		ticketChain = JSON.parse($("#chainJson").val());
		for(var i = 0; i <  totalLectures; i++){
			var selector = "#lectureHash" + i;
			$(selector).val(ticketChain[i.toString()]);
		}
	});

	$(document).on('click',"#signatureButton", function() {
		selectedLectures = [];
		$.each($("input[name='lecture']:checked"), function () {
			selectedLectures.push($(this).val());
		});
		var ticketChainNumbers = [];
		for (var key in ticketChain) {
			ticketChainNumbers.push(hexToBn('0' + ticketChain[key]).toString());
		}
		var itemsProcessed = 0;
		$.each(selectedLectures, function (i, item) {
			var getLecturerSignaturesData = {
				courseId: courseId,
				instructorId: instructorId,
				ticket: ticketChainNumbers[item]
			};

			$.ajax({
				url: "signLectures",
				type: "get",
				datatype: "json",
				data: getLecturerSignaturesData,
				success: function (result) {
					ticketChain[item] = dec2hex(result);
					itemsProcessed++;
					if(itemsProcessed === selectedLectures.length){
						populateSuccessMarkup(ticketChain);
					}
				}
			});

		});

	});

	$("body").on("click","#ticketChain",function(){
		$("#ticketChain").prop("readonly", true);

		var copyText =  $("#ticketChain");

		// Select the text field */
		copyText.select();

		// Copy the text inside the text field */
		document.execCommand("copy");

		// Alert the copied text
		$("#alertBox").modal({
			"backdrop":"static",
			"keyboard":true,
			"show":true
		});
	});

	function populateSuccessMarkup(tChain){
		$("#result").html("<strong>Success!</strong><hr>" +
			"<strong>Use the following hash chain to proceed with your evaluation</strong><br>" +
			"<textarea class='form-control' id='ticketChain' rows='10'>" + JSON.stringify(tChain) + "</textarea>").show();
		$("#attendanceChain").hide();
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

    function dec2hex(str){
        var dec = str.toString().split(''), sum = [], hex = [], i, s
        while(dec.length){
            s = 1 * dec.shift()
            for(i = 0; s || i < sum.length; i++){
                s += (sum[i] || 0) * 10
                sum[i] = s % 16
                s = (s - sum[i]) / 16
            }
        }
        while(sum.length){
            hex.push(sum.pop().toString(16))
        }
        return hex.join('')
    }
});

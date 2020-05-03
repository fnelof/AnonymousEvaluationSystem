
$(document).ready(function() {

	//include csrf token on request headers
	$.ajaxSetup({
		beforeSend: function(xhr) {
			xhr.setRequestHeader('X-Csrf-Token', $('input[name="_csrf"]').attr('value'));
		}
	});

	var crypto = window.crypto || window.msCrypto;
	var m,t,n,e,randomNumber,blindSignature;
	var numberOfLectures;
	var ticketChain = [];

    $("#error").hide();
    $("#publicKey").hide();
    $("#privateKey").hide();

    window.crypto.subtle.generateKey({
			name: "RSASSA-PKCS1-v1_5",
			modulusLength: 2048, //can be 1024, 2048, or 4096
			publicExponent: new Uint8Array([0x01, 0x00, 0x01]),
			hash: {name: "SHA-256"}, //can be "SHA-1", "SHA-256", "SHA-384", or "SHA-512"
		},
		true, //whether the key is extractable (i.e. can be used in exportKey)
		["sign", "verify"] //can be any combination of "sign" and "verify"
	)
		.then(function(key) {

			// For Demo Purpos Only Exported in JWK format
			window.crypto.subtle.exportKey("jwk", key.publicKey).then(
				function(keydata) {
					var publicKeyhold = keydata;
					var publicKeyJson = JSON.stringify(publicKeyhold);
					$("#publicKey").append(publicKeyJson);
					//document.getElementById("rsapublic").value = publicKeyJson;
				}
			);

			window.crypto.subtle.exportKey("jwk", key.privateKey).then(
				function(keydata) {
					var privateKeyhold = keydata;
					var privateKeyJson = JSON.stringify(privateKeyhold);
					$("#privateKey").append(privateKeyJson);
					//document.getElementById("rsaprivate").value = privateKeyJson;
				}
			);
		})
		.catch(function(err) {
			console.error(err);
		});
	var dictionary = [];
	var courseList = [];
	var instructorList  = [];
	var course_id;
	//get dictionary for m
	$.get( "getDictionary", function( data ) {
		dictionary=JSON.parse(data).dictionary;
	});
	
	if(!crypto.subtle){
	    $("#error").append("Cryptography API not Supported.Please use a difference browser.");
	}
	
	// get the courses the user can issue a ticket
	$.get("getCourses",function(data){
		courseList = JSON.parse(data)["courseList"];
		$.each(courseList,function(index,value){
			$("#courseDropdownMenu").append("<li id='"+value["title"].replace(/\s/g,'')+"' value='"+value["id"]+"'><a class='dropdown-item course' href='#'>"+
					value["title"]+"</a></li>");
		});
	});
	
	// get server's public key
	$.ajax({
		  url: "sendPublicKey",
		  datatype: "json",
		  success: function(result){
			  n = new BigInteger(JSON.parse(result).modulus);
			  e = new BigInteger(JSON.parse(result).exponent);
		  }
	});
	
	// Create ticket, blind the ticket, send it to the server, and unblind the signed blinded ticket
	$("#requestButton").click(function(){
		var message = $("#message").val();
		
		// t = h(M)
		sha256Chain(message,numberOfLectures).then(function(digest) {
			t = digest;
			$("#hash").text("(SHA256(m)): " + digest);
		
			// generate random r

			var instructor_id = $("#instructorDropdown").val();
            var  randomValue = getRandomInt(0,1000000);
            randomNumber = new BigInteger(randomValue.toString());
		
			// blind t with randomNumber
			var tNum = convertStringToInteger(t);
			var blinded_ticket = blind(tNum,randomNumber);
			var d = {
					"blindedTicket":blinded_ticket.toString(),
					"courseId":course_id,
					"instructorId":instructor_id,
			};
			$.ajax({
				  type:"post",
				  url: "issueTicket",
				  dataType:"json",
				  data: d,
				  success: function(result){
					  $("#result").hide();
					  blindSignature = new BigInteger(result["blindSignature"]);
					  var signedTicket = unblind(blindSignature,randomNumber);
					  if(verify(signedTicket)) {
						  $("#result").html("<strong>Success!</strong><hr>" +
							  "<strong>Message</strong><br>" + m + "<br>" +
							  "<strong>Signed ticket</strong><br>" +
							  "<input id='signedTicket' rows='1' width='100%' value='" + signedTicket + "'/>");
						  $("#result").toggle();
					  }else {
					  	$("#error").html("<strong>SignedTicket was not successfully verified as a valid one, contact the administrators for more details</strong>");
					  	$("#error").show();
					  	$('#error').delay(3000).fadeOut('slow');
					  }
				  },
				  error: function(xhr, status, error){
					  $("#error").html("<strong>An error occurred when trying to send the request to the server. Please contact the server administrator for more details.</strong>");
				  }
			});
		});
	});
	
	// Copy the signed ticket on clipboard 
	$("body").on("click","#signedTicket",function(){
		$("#signedTicket").prop("readonly", true);

		var copyText =  $("#signedTicket");
		
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
	
	// generates a random message to be used for the ticket creation
	$("#generateMessageButton").on('click',function(){
		m = generateM();
		$("#message").val(m);
		if($("#courseDropdown").text().trim()!="Course" && $("#instructorDropdown").text().trim()!="Instructor"){
			$('#requestButton').prop('disabled', false);
		}
		
	});
	
	// 
	$(document).on('click','.course',function(){
		course_id=$("#"+$(this).text().replace(/\s/g, '')).val();
		$.each(courseList,function(index,value){
			if(value["id"]===course_id){
				instructorList = value["instructorList"];
				numberOfLectures = value["numberOfLectures"];
			}
		});
		$("#instructorDropdownMenu").empty();
		$.each(instructorList,function(index,value){
			$("#instructorDropdownMenu").append("<li id='"+value["name"].replace(/\s/g,'')+"' value='"+value["id"]+"'><a class='dropdown-item instructor' href='#'>"+
					value["name"] + " - " + value["title"]+"</a></li>");
		});
		
		$("#instructorDropdown").html("Instructor");
		$('#requestButton').prop('disabled', true);

	});
	
	
	$(document).on('click','.instructor',function(){
		if($("#message").val().trim()){
			$('#requestButton').prop('disabled', false);			
		}
	});
	
	
	function generateM(){
		var message = $("#publicKey").text();
		sha256(message).then(function(digest){
			t = digest;
		});
		return message;
	}
	function sha256(str) {
		  // We transform the string into an arraybuffer.
		  var buffer = new TextEncoder("utf-8").encode(str);
		  return crypto.subtle.digest("SHA-256", buffer).then(function (hash) {
		    return hex(hash);
		  });
	}
	function sha256Chain(str,times){
		var promise = new Promise(function (resolve,reject){
			if(times == 0){
				resolve(str);
			}else {
				sha256(str).then(function (digest) {
					ticketChain.push(digest);
					resolve(sha256Chain(digest, times - 1));
				});
			}
		});
		return promise;
	}

	function hex(buffer) {
		  var hexCodes = [];
		  var view = new DataView(buffer);
		  for (var i = 0; i < view.byteLength; i += 4) {
		    // Using getUint32 reduces the number of iterations needed (we process 4 bytes each time)
		    var value = view.getUint32(i)
		    // toString(16) will give the hex representation of the number without padding
		    var stringValue = value.toString(16)
		    // We use concatenation and slice for padding
		    var padding = '00000000'
		    var paddedValue = (padding + stringValue).slice(-padding.length)
		    hexCodes.push(paddedValue);
		  }

		  // Join all the hex strings into one
		  return hexCodes.join("");
	}
	function convertStringToInteger(str){
		var byteArray = str.split('').map(s);
		return new BigInteger(byteArray);
	}

	function blind(msg,r){
		return msg.multiply(r.modPow(e,n)).mod(n);
	}
	function verify(msg){
		return msg.modPow(e,n).equals(convertStringToInteger(t));
	}
	function unblind(msg,r){
		return msg.multiply(r.modInverse(n)).mod(n);
	}
	function s(x) {return x.charCodeAt(0);}

    function getRandomInt(min, max) {
        var randomBuffer = new Uint32Array(1);

        window.crypto.getRandomValues(randomBuffer);

        var r = randomBuffer[0] / (0xffffffff + 1);

        min = Math.ceil(min);
        max = Math.floor(max);
        return Math.floor(r * (max - min + 1)) + min;
    }
	$(document).on('click','.dropdown-menu li a',function(){
		$(this).parents(".dropdown").find('.btn').html($(this).text() + ' <span class="caret"></span>');
	});
	
});

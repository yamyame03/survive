function make_AuthCode(){
	let authCode = Math.random().toString(36).substring(2,9);
	return authCode;
}

function authCode_check(authCode, callback){
	$('.btnAuthCodeCheck').click(function(e){
		e.preventDefault();
		let auth_check = $('#auth-check').val();
		let isCheck = "";
		if(auth_check == authCode){
			isCheck = "true";
		}else{
			isCheck = "false";
		}
		callback(isCheck);
	});
}
	
$(document).ready(function(){
	$('.btnEmailAuth').click(function(e){
		e.preventDefault();
		let userEmail = $('#email').val();
		let authCode = make_AuthCode();
		
		if(userEmail == ''){
			alert("이메일을 입력해주세요.");
		}else{
			alert("인증코드 전송..!");
			$('div[name=auth]').css("display","block");
			let jsonData = {'userEmail' : userEmail,
							'authCode' : authCode}
			console.log(authCode);				
			$.ajax({
				url : '/Survivor.io/emailAuth',
				method : 'GET',
				data : jsonData,
				dataType : 'JSON',
				success : function(data){
					if(data == true){
						authCode_check(authCode, function(isCheck){
							if(isCheck == "true"){
								alert("인증번호가 일치합니다.");
							} else {
								alert("인증번호가 일치하지 않습니다.");
							}
						});
					}
				}
			});
		}
	});
});
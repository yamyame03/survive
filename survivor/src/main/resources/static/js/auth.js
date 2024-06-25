let isEmailOk = false;

function make_AuthCode(){
	let authCode = Math.random().toString(36).substring(2,9);
	return authCode;
}

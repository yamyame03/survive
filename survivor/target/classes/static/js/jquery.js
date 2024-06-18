$(document).ready(function(){
	$(".header-img > img").click(function(){
		location.href = "./code"
	});
	
	let success = $(location).attr('search');
	
	if(success != ''){
		let num = success.slice(-3, success.length);
		
		if(num == 101) {
			alert('등록 실패!\n다시 등록해주세요.')
		}else if(num == 102){
			alert('수정 실패!\n다시 등록해주세요.')
		}else if(num == 103){
			alert('삭제 실패!\n다시 등록해주세요.')
		}
	}
	
	$(".btnDelete").click(function(e){
	    let answer = confirm("정말 삭제?");
	    	
	    if(!answer){
			return false;
		}
	});
	
	let currentDate = new Date().toISOString().substring(0, 10);
	
	$("#date").val(currentDate);
	
	$(".btnPayments").click(function(e){
		let buyerName = $("input[class=buyerName]").val();
		let buyerEmail = $("input[class=buyerEmail]").val();
		let zip = $("#zip").val();
		let addr1 = $('#addr1').val();
        if (buyerName && buyerEmail && zip && addr1) {
            requestPay();
        } else {
            alert('입력해주세요.');
        }
	});
		
});



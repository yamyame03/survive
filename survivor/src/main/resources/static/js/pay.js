$(document).ready(function(){
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
	
	$('.btnPaymentsCancel').click(function(){
		if(confirm("결제를 취소하시겠습니까?")){
			let merchant_uid = $('input[class=merchant_uid]').val();
			let price = $('input[class=price]').val();
			let reason = "테스트 결제 환불";
			let no = $('input[class=no]').val();
			
			console.log(no);
			let jsonData = {"merchant_uid" : merchant_uid,
							"price" : price,
							"reason" : reason,
							"no" : no};
			
			$.ajax({
				url : "/Survivor.io/order/paymentsCancel",
				method : 'POST',
				data : jsonData,
				dataType : 'JSON',
				success : function(data){
					if(data['data'] == '0') {
						console.log(data);
						alert('결제 취소에 성공하였습니다.');
						location.href="./code";
					} else{
						alert('결제 취소에 실패하였습니다.');
						location.href="./code";
					}
				},
			    error: function(error) {
			        console.log(error);
				}
				
			});
		}
	});	
});



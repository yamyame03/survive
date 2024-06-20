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
	
    $('.btnCalculator').click(function(){
        let checkbox = $('input[type="checkbox"]:checked');
        let array = []; // 값을 담을 배열을 초기화합니다.

        checkbox.each(function(){
            array.push($(this).val());
        });
        
        let pet = parseInt($('td[class=pet] > input[type=text]').val());
        let collection = parseInt($('td[class=collection] > input[type=text]').val());
        
        let total = 0;
        
        for(let i = 0; i < array.length; i++){
        	total += parseInt(array[i]);
        }
        
        let totalSum = total + pet + collection;
        
        console.log(totalSum);
        
        $('.result > input').val(totalSum);
    });
    
    $('td[class=equipment] > ul li.calculator-li > input[type=checkbox]').change(function(){
    	let length = $('td[class=equipment] > ul li.calculator-li > input[type=checkbox]:checked').length;
    	
        if(length >= 2){
        	alert("장비는 1개만 클릭해주세요.");
        	$('td[class=equipment] > ul li.calculator-li > input[type=checkbox]').prop("checked", false);
        }
    });		
});



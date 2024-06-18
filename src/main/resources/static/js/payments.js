 var IMP = window.IMP;
 IMP.init("imp84685320");

function make_merchant_uid(){
	let unixtime = Math.floor(new Date().getTime() / 1000);
	let random = Math.random().toString(16).substr(2, 3);
	let merchant_uid = random + unixtime;
	return merchant_uid;
}

function info(){
	let productName 	= document.getElementsByClassName("productName")[0].textContent;
	let productPrice 	= parseInt(document.getElementsByClassName("productPrice")[0].textContent);
	return [productName, productPrice];
}

function requestPay() {
	 let merchant_uid 	= make_merchant_uid();
	 let information  	= info();
	 let productName 	= information[0];
	 let productPrice 	= information[1];
	 
     IMP.request_pay(
         {
        	//KG이니시스 pg파라미터 값
            pg: "html5_inicis",		
          	//결제방법
            pay_method: "card",		
          	//주문번호
            merchant_uid: merchant_uid,
          	//상품명
            name: productName,
    		//금액
           	amount: productPrice,	
			buyer_email: "gildong@gmail.com",
			buyer_name: "홍길동",
			buyer_tel: "010-4242-4242",
			buyer_addr: "서울특별시 강남구 신사동",
			buyer_postcode: "01182"

         },
         function (rsp) {
			//rsp.imp_uid 값으로 결제 단건조회 API를 호출하여 결제결과를 판단합니다.
             if (rsp.success) {
				
				let jsonData = {"imp_uid" : rsp.imp_uid,
								"merchant_uid" : rsp.merchant_uid,
								"amount" : rsp.paid_amount};
								
                 $.ajax({
					url : "/Survivor.io/order/payments",
					method : "post",
					data: jsonData,
					dataType : "json",
					success : function(data){
						console.log(data);
						alert("data" + data);
					},
				    error: function(error) {
				        console.log("에러 발생: " + error);
				    }
				 });
             } else {
                 alert("결제에 실패하였습니다. " + rsp.error_msg);
             }
         }
     );
 }

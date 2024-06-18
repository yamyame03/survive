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
	let buyerName 		= document.querySelector("input[class='buyerName']").value;
	let buyerEmail 		= document.querySelector("input[class='buyerEmail']").value;
	let zip 			= document.getElementById("zip").value;
	let addr1 			= document.getElementById("addr1").value;
	let addr2 			= document.getElementById("addr2").value;
	return [productName, productPrice, buyerName, buyerEmail, zip, addr1, addr2];
}

function requestPay() {
	 let merchant_uid 	= make_merchant_uid();
	 let information  	= info();
	 let productName 	= information[0];
	 let productPrice 	= information[1];
	 let buyerName 		= information[2];
	 let buyerEmail 	= information[3];
	 let zip 			= information[4];
	 let addr1 			= information[5];
	 let addr2 			= information[6];
	 console.log(productPrice);
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
			buyer_email: buyerEmail,
			buyer_name: buyerName,
			buyer_tel: "010-4390-0810",
			buyer_addr: addr1 + addr2,
			buyer_postcode: zip
         },
         function (rsp) {
			//rsp.imp_uid 값으로 결제 단건조회 API를 호출하여 결제결과를 판단합니다.
             if (rsp.success) {
				
				let jsonData = {"imp_uid" : rsp.imp_uid,
								"merchant_uid" : rsp.merchant_uid,
								"amount" : rsp.paid_amount,
								// 이름
								"name" : buyerName,
								// 이메일
								"email" : buyerEmail,
								// 상품명
								"product_name" : productName,
								// 가격
								"price" : productPrice,
								// 우편번호
								"zip" : zip,
								// 주소
								"addr" : addr1 + addr2
								};
								
                 $.ajax({
					url : "/Survivor.io/order/payments",
					method : "post",
					data: jsonData,
					dataType : "json",
					success : function(data){
						let no = data['data'];
						location.href="./orderComplete?no="+no;
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

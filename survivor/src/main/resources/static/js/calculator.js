$(document).ready(function(){
    $('.btnCritical').click(function(){
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
    
    $('button[class=btnEssence]').click(function() {
        let length = $('.essence-container > input:checked').length;
        let type = $('.essence-container > input:checked').val();
        let startLevel = $('input[class=startLevel]').val();
        let endLevel = $('input[class=endLevel]').val();

        let jsonData = {
            'type': type,
            'startLevel': startLevel,
            'endLevel': endLevel
        };

        if (length < 1) {
            alert('특공대를 선택해주세요.');
        } else if (length > 1) {
            alert('특공대 타입은 1개만 선택해주세요.');
            $('.essence-container > input').prop("checked", false);
        } else {
            if (startLevel === '' || endLevel === '') {
                alert('캐릭터 레벨을 입력해주세요.');
                $('input[class=startLevel]').focus();
            } else if (isNaN(startLevel) || isNaN(endLevel)) {
                alert("숫자를 입력해주세요.");
                $('input[class=startLevel]').val(0);
                $('input[class=endLevel]').val(0);
                $('input[class=startLevel]').focus();
            } else {
                $.ajax({
                    url: '/Survivor.io/essenceCalculator',
                    method: 'GET',
                    data: jsonData,
                    dataType: 'JSON',
                    success: function(data) {
                    	console.log(data);
                    	if(data['msg'] == 'true'){
                            let gold = data['gold'];
                            let essence = data['essence'];
                            alert("필요 골드는 " + gold + "\n필요 에센스는 " + essence + " 입니다.");
                    	}else if(data['msg'] == 'false'){
                    		alert('정확한 값을 입력해주세요.');
                    	}
                    },
                    error: function(error) {
                        console.log("에러 발생: " + error);
                    }
                });
            }
        }
    });    
});



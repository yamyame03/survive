package kr.co.survivor.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.co.survivor.service.ProductService;
import kr.co.survivor.vo.ApiKeyVO;
import kr.co.survivor.vo.PaymentVO;
import kr.co.survivor.vo.ProductVO;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("/product")
	public String product(Model model) {
		
		List<ProductVO> lists = service.selectProducts();
		
		model.addAttribute("lists", lists);
		
		return "views/product/product";
	}
	
	@GetMapping("/cart")
	public String cart(Model model) {
		return "views/product/cart";
	}
	
	@GetMapping("/order")
	public String order(Model model, int pno) {
		
		ProductVO product = service.selectProduct(pno);
		
		model.addAttribute("product", product);
		
		return "views/product/order";
	}
	
	@GetMapping("/orderComplete")
	public String orderComplete(Model model, int no) {
		
		PaymentVO order = service.selectOrder(no);
		
		int type = order.getType();
		
		String process = "";
		
		if(type == 1) {
			process = "신용카드";
		}else {
			process = "무통장입금";
		}
		
		model.addAttribute("order", order);
		model.addAttribute("process", process);
		
		return "views/product/orderComplete";
	}
	
	@ResponseBody
	@PostMapping("/order/payments")
	public Map<String, Integer> orderPayments(@RequestParam String imp_uid, 
												@RequestParam String merchant_uid, 
												@RequestParam String amount,
												@RequestParam String name,
												@RequestParam String email,
												@RequestParam String product_name,
												@RequestParam int price,
												@RequestParam int zip,
												@RequestParam String addr,
												@RequestParam int proNo,
												PaymentVO pvo) {
		
		service.insertPayment(pvo);
		
		int data = pvo.getId();
		
	    Map<String, Integer> map = new HashMap<>();
	    
	    map.put("data", data);
	    
	    return map;
	}
	
	@ResponseBody
	@PostMapping("/order/paymentsCancel")
	public Map<String, String> orderPaymentsCancel(@RequestParam String merchant_uid, @RequestParam int price, @RequestParam String reason, @RequestParam int no, PaymentVO pvo) throws IOException  {
		System.out.println("error...1");
		URL url = new URL("https://api.iamport.kr/payments/cancel");
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		String access_token = service.getAccessToken();
		
		// 요청 방식을 POST로 설정
		conn.setRequestMethod("POST");
		
        // 요청의 Content-Type, Accept, Authorization 헤더 설정
        conn.setRequestProperty("Content-type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Authorization", access_token);
        
        // 해당 연결을 출력 스트림(요청)으로 사용
        conn.setDoOutput(true);
        
        // JSON 객체에 해당 API가 필요로하는 데이터 추가.
        JsonObject json = new JsonObject();
        json.addProperty("merchant_uid", merchant_uid);
        json.addProperty("reason", reason);
 
        // 출력 스트림으로 해당 conn에 요청
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        bw.write(json.toString());
        bw.flush();
        bw.close();
 
        // 입력 스트림으로 conn 요청에 대한 응답 반환
        String responseJson = new BufferedReader(new InputStreamReader(conn.getInputStream()))
                .lines()
                .collect(Collectors.joining("\n"));
        
        System.out.println("응답 본문: " + responseJson);
        
    	JsonObject jsonResponse = JsonParser.parseString(responseJson).getAsJsonObject();
        String resultCode 		= jsonResponse.get("code").getAsString();
        JsonElement resultMsg 	= jsonResponse.get("message");
        String resultMessage 	= (resultMsg != null && !resultMsg.isJsonNull()) ? resultMsg.getAsString() : null;
        
        // 결과 출력
        if (resultMessage == null) {
        	resultMessage = "취소 성공";
        	service.updatePayments(pvo);
        } else {
            System.out.println("결과 메시지 = " + resultMessage);
        }
        
        Map<String, String> map = new HashMap<>();
        
        map.put("data", resultCode);
        
       return map; 
	}
}

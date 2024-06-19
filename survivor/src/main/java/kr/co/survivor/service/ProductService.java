package kr.co.survivor.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.co.survivor.dao.ProductDAO;
import kr.co.survivor.vo.ApiKeyVO;
import kr.co.survivor.vo.PaymentVO;
import kr.co.survivor.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {

	@Autowired
	private ProductDAO dao;
	
	public int insertPayment(PaymentVO pvo) {
		return dao.insertPayment(pvo);
	}
	
	public List<ProductVO> selectProducts(){
		return dao.selectProducts();
	}
	
	public ProductVO selectProduct(int pno) {
		return dao.selectProduct(pno);
	};
	
	public PaymentVO selectOrder(int pno) {
		return dao.selectOrder(pno);
	};
	
	public ApiKeyVO selectApiKey() {
		return dao.selectApiKey();
	}
	
	public void updatePayments(PaymentVO pvo) {
		dao.updatePayments(pvo);
	}
	
	public String getAccessToken() throws IOException{
		
		ApiKeyVO keys = selectApiKey();
		
		String apiKey = keys.getApiKey();
		String secretKey = keys.getSecretKey();
		
        URL url = new URL("https://api.iamport.kr/users/getToken");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		
        // 요청 방식을 POST로 설정
        conn.setRequestMethod("POST");
 
        // 요청의 Content-Type과 Accept 헤더 설정
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
		
        conn.setDoOutput(true);
        
        JsonObject json = new JsonObject();
        json.addProperty("imp_key", apiKey);
        json.addProperty("imp_secret", secretKey);
        
        // 출력 스트림으로 해당 conn에 요청
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        bw.write(json.toString()); // json 객체를 문자열 형태로 HTTP 요청 본문에 추가
        bw.flush(); // BufferedWriter 비우기
        bw.close(); // BufferedWriter 종료
 
        // 입력 스트림으로 conn 요청에 대한 응답 반환
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        Gson gson = new Gson(); // 응답 데이터를 자바 객체로 변환
        String response = gson.fromJson(br.readLine(), Map.class).get("response").toString();
        String accessToken = gson.fromJson(response, Map.class).get("access_token").toString();
        br.close(); // BufferedReader 종료
 
        conn.disconnect(); // 연결 종료
        
		return accessToken;
	}
	
}

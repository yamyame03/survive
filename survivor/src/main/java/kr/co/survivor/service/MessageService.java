package kr.co.survivor.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import kr.co.survivor.dao.ProductDAO;
import kr.co.survivor.vo.ApiKeyVO;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Service
public class MessageService {
	
	@Autowired
	private ProductService productService;
	
	private DefaultMessageService messageService;
	
	@PostConstruct
	private void init() {
		ApiKeyVO keys = productService.selectApiKey();
		String apiKey = keys.getCoolSmsApiKey();
		String apiSecretKey = keys.getCoolSmsSecretKey();
		this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecretKey, "https://api.coolsms.co.kr");
	}
	
	public SingleMessageSentResponse sendOn(String to, String randomNum) {
		ApiKeyVO keys = productService.selectApiKey();
		String phoneNumber = keys.getFromPhoneNumber();
		String authCode = randomNum;
		String content = keys.getSendMsgContent() + "\n" + authCode;
		
		Message message = new Message();
		message.setFrom(phoneNumber);
		message.setTo(to);
		message.setText(content);
		
		try {
			SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
			return response;
			
		} catch (Exception e) {
			System.out.println("에러 내용 ..  " + e);
			return null;
		}
	}
}

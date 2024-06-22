package kr.co.survivor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public boolean sendSimpleEmail(String usermail, String authCode) {
		SimpleMailMessage msg = new SimpleMailMessage();
		
		boolean authCheck = false;
		
		if(usermail == null || usermail.isEmpty()) {
			return authCheck;
		}else {
			try {
				authCheck = true;
				msg.setSubject("테스트 제목");
				msg.setFrom("leemh4390@gmail.com");
				msg.setTo(usermail);
				msg.setText(authCode + "인증코드");
				
				javaMailSender.send(msg);
				
			} catch (Exception e) {
				System.out.println("enter....2");
				e.printStackTrace(); // 예시로 콘솔에 출력
                authCheck = false; // 이메일 전송 실패 시 처리 로직 추가 가능
			}
			return authCheck;
		}
	}
}

package kr.co.survivor.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.survivor.service.MailService;
import kr.co.survivor.service.MemberService;
import kr.co.survivor.service.MessageService;
import kr.co.survivor.vo.MemberVO;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private MessageService messageService;
	
	@GetMapping("/memberSignIn")
	public String memberSignIn() {
		return "views/member/memberSignIn";
	}
	
	@GetMapping("/memberSignUp")
	public String memberSignUp() {
		return "views/member/memberSignUp";
	}
	
	@PostMapping("/memberSignUp")
	public String memberSignUp(HttpServletRequest http, MemberVO mvo) {
		String regip = http.getRemoteAddr();
		mvo.setRegip(regip);
		memberService.insertMember(mvo);
		return "redirect:/code";
	}
	
	@ResponseBody
	@GetMapping("/emailAuth")
	public boolean emailAuth(@RequestParam String userEmail, @RequestParam String authCode) {
		boolean check = mailService.sendSimpleEmail(userEmail, authCode);
		if(check == true) {
			return check;
		}else {
			return check;
		}
	}
	
	@ResponseBody
	@GetMapping("/HpMesaageAuth")
	public Map<String, String> hpMessageAuth(@RequestParam String hp) {
		
		HashMap<String, String> map = new HashMap<>();
        Random rand = new Random();
        
        String randomNum = "";
        for (int i = 0; i < 4; i++) {
            String random = Integer.toString(rand.nextInt(10));
            randomNum += random;
        }
		
        SingleMessageSentResponse data = messageService.sendOn(hp, randomNum);
        
        String statusCode = data.getStatusCode();
        
        System.out.println(data);
        
        if("2000".equals(statusCode)) {
        	map.put("code", randomNum);
        	map.put("statusCode", statusCode);
        	return map;
        }else {
        	System.out.println("false");
        	map.put("statusCode", statusCode);
        	return map;
        }
	}
}

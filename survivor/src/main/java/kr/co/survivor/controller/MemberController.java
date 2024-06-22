package kr.co.survivor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.survivor.service.MailService;
import kr.co.survivor.service.MemberService;
import kr.co.survivor.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MailService mailService;
	
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
}

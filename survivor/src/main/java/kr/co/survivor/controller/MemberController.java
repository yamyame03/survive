package kr.co.survivor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.survivor.service.MemberService;
import kr.co.survivor.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
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

}

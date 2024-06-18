package kr.co.survive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import kr.co.survive.service.MemberService;
import kr.co.survive.service.UserService;
import kr.co.survive.vo.MemberVO;
import kr.co.survive.vo.UserVO;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user")
	public String user(Model model) {
		
		List<UserVO> lists = userService.selectUser();
		
		model.addAttribute("lists", lists);
		
		return "views/user/user";
	}
	
	@GetMapping("/userRegister")
	public String userRegister() {
		return "views/user/userRegister";
	}
	
	@PostMapping("/userRegister")
	public String userRegister(UserVO uvo, HttpServletRequest req) {
		
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        String username;
        
        if(auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
        	username = auth.getName();
        	System.out.println(username);
        }else {
        	username = "anonymousUser";
        }
		
		String regip = req.getRemoteAddr();
		
		uvo.setRegip(regip);
		uvo.setUsername(username);
		
		int value = userService.insertUser(uvo);
		
		if (value != 1) {
	        System.err.println("Code registration failed. Value: " + value);
	        return "redirect:/user&success=200"; // 실패 시 리다이렉트할 페이지로 변경
		}
		
		return "redirect:/user";
	}

}

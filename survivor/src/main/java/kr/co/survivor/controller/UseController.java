package kr.co.user.controller;

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
import kr.co.user.service.MemberService;
import kr.co.user.service.UserService;
import kr.co.user.vo.MemberVO;
import kr.co.user.vo.UserVO;

@Controller
public class UseController {
	
	@GetMapping("/use")
	public String user(Model model) {
		
		return "views/use/use";
	}

}

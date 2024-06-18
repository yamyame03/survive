package kr.co.survivor.controller;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.survivor.service.CodeService;
import kr.co.survivor.vo.CodeVO;

@Controller
public class CodeController {
	
	@Autowired
	private CodeService service;

	@GetMapping({"", "/","/code"})
	public String index(Model model, Integer page) {
		
		// 페이지 쿼리파라미터가 없으면 1페이지라고 생각함
		if(page == null) {
			page = 1;
		}
		
		// 현재 페이지
		int currentPage = service.getCurrentPage(page);
		// 5개씩 출력
		int pg = service.getPageList(page);
		// 마지막 페이지
		int lastPageNum = service.getLastPageNum();
		// 합계
		int total = service.selectTotal();
		// 이전 다음 페이징 처리
		int[] groups = service.getPageGroup(currentPage, lastPageNum);
		
		// 시간 비교
		long currentUnixTime = System.currentTimeMillis() / 1000;
		
		List<CodeVO> lists = service.selectCodes(pg);
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("currentUnixTime", currentUnixTime);	
		model.addAttribute("totalCount", lastPageNum);	
		model.addAttribute("lists", lists);
		model.addAttribute("total", total);
		model.addAttribute("groups", groups);
		
		return "views/code/code";
	}

	
	@GetMapping("/codeRegister")
	public String codeRegist() {
    		
		return "views/code/codeRegister";
	}	
	
	
	@PostMapping("/codeRegister")
	public String codeRegist(String date, HttpServletRequest req, CodeVO cvo) {
		
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        String username;
        
        if(auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
        	username = auth.getName();
        }else {
        	username = "anonymousUser";
        }
        
		String regip = req.getRemoteAddr();
		
		LocalDate unixtime = LocalDate.parse(date);
		
		long unixTimestamp = unixtime.atStartOfDay().toInstant(ZoneOffset.UTC).getEpochSecond();
		long eightHoursInSeconds = 8 * 3600;
		long newUnixTime = unixTimestamp - eightHoursInSeconds;
		
		cvo.setUnixtime(newUnixTime);
		cvo.setRegip(regip);
		cvo.setUsername(username);
		
		int value = service.insertCode(cvo);
		
		if (value != 1) {
	        System.err.println("Code registration failed. Value: " + value);
	        return "redirect:/code?success=101"; // 실패 시 리다이렉트할 페이지로 변경
		}
		
		return "redirect:/code";
	}		
	
	@GetMapping("/codeModify")
	public String codeModify(int cno, Model model) {
		
		CodeVO code = service.selectCode(cno);
		
		model.addAttribute("code", code);
		
		return "views/code/codeModify";
	}
	
	@PostMapping("/codeModify")
	public String codeModify(String date, HttpServletRequest req, CodeVO cvo) {
		
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        String username;
        
        if(auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
        	username = auth.getName();
        	System.out.println(username);
        }else {
        	username = "anonymousUser";
        }
		
		LocalDate unixtime = LocalDate.parse(date);
		
		long unixTimestamp = unixtime.atStartOfDay().toInstant(ZoneOffset.UTC).getEpochSecond();
		long eightHoursInSeconds = 8 * 3600;
		long newUnixTime = unixTimestamp - eightHoursInSeconds;
		
		String modip = req.getRemoteAddr();
		
		cvo.setUnixtime(newUnixTime);
		cvo.setModip(modip);
		cvo.setUsername(username);
		
		int value = service.updateCode(cvo);
		
		if (value != 1) {
	        System.err.println("Code registration failed. Value: " + value);
	        return "redirect:/code?success=102"; // 실패 시 리다이렉트할 페이지로 변경
		}		
		
		return "redirect:/code";
	}
	
	@GetMapping("/codeDelete")
	public String deleteCode(int cno) {
		
		int value = service.deleteCode(cno);
		
		if (value != 1) {
	        System.err.println("Code registration failed. Value: " + value);
	        return "redirect:/code?success=103"; // 실패 시 리다이렉트할 페이지로 변경
		}		
		
		return "redirect:/code";
	}
}

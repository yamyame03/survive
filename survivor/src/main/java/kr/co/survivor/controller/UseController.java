package kr.co.survivor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import kr.co.survivor.service.UseService;
import kr.co.survivor.service.MemberService;
import kr.co.survivor.service.MessageService;
import kr.co.survivor.service.UserService;
import kr.co.survivor.vo.CharacterVO;
import kr.co.survivor.vo.EssenceVO;
import kr.co.survivor.vo.MemberVO;
import kr.co.survivor.vo.UserVO;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;

@Controller
public class UseController {
	
	@Autowired
	private UseService useService;
	
	@GetMapping("/use")
	public String use(Model model) {
		
//		String to = "01043900810";
//		messageService.sendOn(to);
		
		return "views/use/use";
	}
	
	@GetMapping("/essence")
	public String essence(Model model) {
		return "views/use/essence";
	}	
	
	@ResponseBody
	@GetMapping("/essenceCalculator")
	public Map<String, Object> essence(@RequestParam String type, @RequestParam int startLevel, @RequestParam int endLevel) {
		
		EssenceVO data = useService.selectEssence(type, startLevel, endLevel);
		Map<String, Object> map = new HashMap<>();
		
		if(data != null) {
			String gold = data.getGold() / 1000 + "K";
			String msg = "true";
			map.put("msg", msg);
			map.put("gold", gold);
			map.put("essence", data.getEssence());
			return map;
		}else {
			String msg = "false";
			map.put("msg", msg);
			return map;
		}
	}
	
	@GetMapping("/critical")
	public String critical(Model model) {
		
		List<CharacterVO> characters_1 = useService.selectCharacter(0);
		List<CharacterVO> characters_2 = useService.selectCharacter(5);
		List<CharacterVO> characters_Equip = useService.selectEquip();
		List<CharacterVO> characters_Gen = useService.selectGen();
		
		model.addAttribute("characters_1", characters_1);
		model.addAttribute("characters_2", characters_2);
		model.addAttribute("characters_Equip", characters_Equip);
		model.addAttribute("characters_Gen", characters_Gen);
		
		return "views/use/critical";
	}
}

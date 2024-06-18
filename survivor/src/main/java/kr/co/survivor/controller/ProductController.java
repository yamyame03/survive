package kr.co.survivor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.survivor.service.ProductService;
import kr.co.survivor.vo.PaymentVO;
import kr.co.survivor.vo.ProductVO;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("/product")
	public String product(Model model) {
		
		List<ProductVO> lists = service.selectProducts();
		
		model.addAttribute("lists", lists);
		
		return "views/product/product";
	}
	
	@GetMapping("/cart")
	public String cart(Model model) {
		return "views/product/cart";
	}
	
	@GetMapping("/order")
	public String order(Model model, int pno) {
		
		ProductVO product = service.selectProduct(pno);
		
		model.addAttribute("product", product);
		
		return "views/product/order";
	}
	
	@GetMapping("/orderComplete")
	public String orderComplete(Model model, int no) {
		
		System.out.println("no : " + no);
		
		PaymentVO order = service.selectOrder(no);
		
		int type = order.getType();
		
		String process = "";
		
		if(type == 1) {
			process = "신용카드";
		}else {
			process = "무통장입금";
		}
		
		model.addAttribute("order", order);
		model.addAttribute("process", process);
		
		return "views/product/orderComplete";
	}
	
	@ResponseBody
	@PostMapping("/order/payments")
	public Map<String, Integer> orderPayments(@RequestParam String imp_uid, 
												@RequestParam String merchant_uid, 
												@RequestParam String amount,
												@RequestParam String name,
												@RequestParam String email,
												@RequestParam String product_name,
												@RequestParam int price,
												@RequestParam int zip,
												@RequestParam String addr,
												PaymentVO pvo) {
		
		service.insertPayment(pvo);
		
		int data = pvo.getId();
		
	    Map<String, Integer> map = new HashMap<>();
	    
	    map.put("data", data);
	    
	    return map;
	}
}

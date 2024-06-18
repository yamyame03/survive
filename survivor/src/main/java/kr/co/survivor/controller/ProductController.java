package kr.co.user.controller;

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

import kr.co.user.service.ProductService;
import kr.co.user.vo.PaymentVO;
import kr.co.user.vo.ProductVO;

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
		System.out.println("pno : " + pno);
		ProductVO product = service.selectProduct(pno);
		
		model.addAttribute("product", product);
		
		return "views/product/order";
	}
	
	@ResponseBody
	@PostMapping("/order/payments")
	public Map<String, String> orderPayments(@RequestParam String imp_uid, @RequestParam String merchant_uid, @RequestParam String amount, PaymentVO pvo) {
		
		service.insertPayment(pvo);
		
	    Map<String, String> map = new HashMap<>();
	    
	    String data = "결제성공!";
	    
	    map.put("data", data);
	    
	    return map;
	}
}

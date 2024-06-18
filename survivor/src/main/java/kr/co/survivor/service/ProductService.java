package kr.co.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.user.dao.ProductDAO;
import kr.co.user.vo.PaymentVO;
import kr.co.user.vo.ProductVO;

@Service
public class ProductService {

	@Autowired
	private ProductDAO dao;
	
	public List<ProductVO> selectProducts(){
		return dao.selectProducts();
	}
	
	public ProductVO selectProduct(int pno) {
		return dao.selectProduct(pno);
	};
	
	public void insertPayment(PaymentVO pvo) {
		dao.insertPayment(pvo);
	}
	
}

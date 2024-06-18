package kr.co.survivor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.survivor.dao.ProductDAO;
import kr.co.survivor.vo.PaymentVO;
import kr.co.survivor.vo.ProductVO;

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

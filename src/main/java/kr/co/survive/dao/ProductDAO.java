package kr.co.survive.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.survive.vo.CodeVO;
import kr.co.survive.vo.PaymentVO;
import kr.co.survive.vo.ProductVO;

@Mapper
@Repository
public interface ProductDAO {
	public List<ProductVO> selectProducts();
	public ProductVO selectProduct(int pno);
	public void insertPayment(PaymentVO pvo);
}

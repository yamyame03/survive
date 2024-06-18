package kr.co.survivor.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.survivor.vo.PaymentVO;
import kr.co.survivor.vo.ProductVO;

@Mapper
@Repository
public interface ProductDAO {
	public List<ProductVO> selectProducts();
	public ProductVO selectProduct(int pno);
	public void insertPayment(PaymentVO pvo);
}

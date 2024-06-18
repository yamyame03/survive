package kr.co.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.user.vo.CodeVO;
import kr.co.user.vo.PaymentVO;
import kr.co.user.vo.ProductVO;

@Mapper
@Repository
public interface ProductDAO {
	public List<ProductVO> selectProducts();
	public ProductVO selectProduct(int pno);
	public void insertPayment(PaymentVO pvo);
}

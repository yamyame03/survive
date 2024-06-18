package kr.co.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.user.vo.MemberVO;

@Mapper
@Repository
public interface MemberDAO {
	public int insertMember(MemberVO mvo);
}

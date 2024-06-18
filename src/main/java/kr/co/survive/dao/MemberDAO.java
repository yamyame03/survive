package kr.co.survive.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.survive.vo.MemberVO;

@Mapper
@Repository
public interface MemberDAO {
	public int insertMember(MemberVO mvo);
}

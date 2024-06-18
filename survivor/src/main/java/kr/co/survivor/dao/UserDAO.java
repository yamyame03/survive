package kr.co.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.user.vo.UserVO;

@Mapper
@Repository
public interface UserDAO {
	public int insertUser(UserVO svo);
	public List<UserVO> selectUser();
}

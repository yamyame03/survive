package kr.co.survive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.survive.dao.UserDAO;
import kr.co.survive.vo.UserVO;

@Service
public class UserService {
	
	@Autowired
	private UserDAO dao;
	
	public List<UserVO> selectUser(){
		return dao.selectUser();
	}
	
	public int insertUser(UserVO svo) {
		return dao.insertUser(svo);
	}
}

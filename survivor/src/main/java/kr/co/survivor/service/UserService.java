package kr.co.survivor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.survivor.dao.UserDAO;
import kr.co.survivor.vo.UserVO;

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

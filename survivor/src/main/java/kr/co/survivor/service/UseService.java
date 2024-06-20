package kr.co.survivor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.survivor.dao.UseDAO;
import kr.co.survivor.dao.UserDAO;
import kr.co.survivor.vo.CharacterVO;
import kr.co.survivor.vo.UserVO;

@Service
public class UseService {
	
	@Autowired
	private UseDAO dao;
	
	public List<CharacterVO> selectCharacter(int num){
		return dao.selectCharacter(num);
	}
	
	public List<CharacterVO> selectEquip(){
		return dao.selectEquip();
	}
	
	public List<CharacterVO> selectGen(){
		return dao.selectGen();
	}

}

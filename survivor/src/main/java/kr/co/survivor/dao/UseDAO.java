package kr.co.survivor.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.survivor.vo.CharacterVO;
import kr.co.survivor.vo.UserVO;

@Mapper
@Repository
public interface UseDAO {
	public List<CharacterVO> selectCharacter(int num);
	public List<CharacterVO> selectEquip();
	public List<CharacterVO> selectGen();
}

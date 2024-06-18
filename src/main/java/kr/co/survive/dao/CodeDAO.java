package kr.co.survive.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.survive.vo.CodeVO;

@Mapper
@Repository
public interface CodeDAO {
	public int insertCode(CodeVO cvo);
	public List<CodeVO> selectCodes(int page);
	public CodeVO selectCode(int cno);
	public int updateCode(CodeVO cvo);
	public int deleteCode(int cno);
	public int selectTotal();
}

package kr.co.survivor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.survivor.dao.CodeDAO;
import kr.co.survivor.vo.CodeVO;

@Service
public class CodeService {

	@Autowired
	private CodeDAO dao;
	
	public int insertCode(CodeVO cvo) {
		return dao.insertCode(cvo);
	}
	
	public List<CodeVO> selectCodes(int page){
		return dao.selectCodes(page);
	}
	
	public CodeVO selectCode(int cno) {
		return dao.selectCode(cno);
	}
	
	public int updateCode(CodeVO cvo) {
		return dao.updateCode(cvo);
	};	
	
	public int deleteCode(int cno) {
		return dao.deleteCode(cno);
	};
	
	public int selectTotal() {
		return dao.selectTotal();
	};
	
	
	// 현재 페이지를 출력하는 메소드
	public int getCurrentPage(Integer page) {
		if(page == null) {
			page = 1;
		}
		return page;
	}
	
	// 한 페이지에 5개씩 출력해주는 메소드
	public int getPageList(int page) {
		int pg = (page - 1) * 5;
		return pg;
	}
	
	// 마지막 페이지
	public int getLastPageNum() {
		int total = dao.selectTotal();
		
		int lastPageNum;
		
		if(total % 5 == 0) {
			lastPageNum = total / 5;
		}else {
			lastPageNum = (total / 5) + 1;
		}
		
		return lastPageNum;
	}
	
	public int[] getPageGroup(int currentPage, int lastPageNum) {
		
		// 5페이지씩 출력
		int perPage = 5;
		int prevPageStart, nextPageStart, currentPageEnd;
		
		int groupCurrent = (int) Math.ceil(currentPage / perPage);
		int currentPageStart = (currentPage - 1) / perPage * perPage + 1;
		
		if(lastPageNum < 5) {
			prevPageStart = 1;
			nextPageStart = lastPageNum;
			currentPageEnd = lastPageNum;
			
			if(currentPageEnd == 0)
				nextPageStart = 1;
			
		}else {
			prevPageStart = currentPageStart - perPage;
			nextPageStart = currentPageStart + perPage;
			
			currentPageEnd  = nextPageStart -1;
			
			if(prevPageStart < 1) 
				prevPageStart = 1;
			
			if(nextPageStart > lastPageNum) 
				nextPageStart = lastPageNum;
				currentPageEnd = lastPageNum;
		}
		
		int[] groups = {prevPageStart, nextPageStart, currentPageStart, currentPageEnd};
		
		return groups;
	}
	
}

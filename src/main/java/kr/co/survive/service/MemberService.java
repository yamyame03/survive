package kr.co.survive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.survive.dao.MemberDAO;
import kr.co.survive.repository.MemberRepository;
import kr.co.survive.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	private final MemberRepository memberRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	// 회원가입
	public void insertMember(MemberVO mvo) {
		mvo.setPassword(passwordEncoder.encode(mvo.getPassword()));
		memberDAO.insertMember(mvo);
	}
}

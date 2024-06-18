package kr.co.survivor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.survivor.dao.MemberDAO;
import kr.co.survivor.entity.MemberEntity;
import kr.co.survivor.repo.MemberRepository;
import kr.co.survivor.vo.MemberVO;
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

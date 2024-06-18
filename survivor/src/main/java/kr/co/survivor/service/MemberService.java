package kr.co.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.user.dao.MemberDAO;
import kr.co.user.entity.MemberEntity;
import kr.co.user.repo.MemberRepository;
import kr.co.user.vo.MemberVO;
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

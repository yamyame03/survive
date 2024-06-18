package kr.co.survive.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.survive.entity.MemberEntity;
import kr.co.survive.repository.MemberRepository;

@Service
public class SecurityMemberService  implements UserDetailsService{
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<MemberEntity> memberOpt = memberRepository.findById(username);
		
		if(!memberOpt.isPresent()) {
			throw new UsernameNotFoundException(username);
		}else {
			MemberEntity member = memberOpt.get();
			UserDetails memberDts = MyUserDetails.builder().member(member).build();
			return memberDts;
		}
	}
}

package kr.co.user.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import kr.co.user.entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MyUserDetails implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	private MemberEntity member;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
	    List<GrantedAuthority> authorities = new ArrayList<>();
	    
	    if(member != null) {
	    	authorities.add(new SimpleGrantedAuthority("ROLE_" + member.getLevel())); 
	    }
	    
	    return authorities;
	}

	@Override
	public String getPassword() {
		
		if(member == null) {
			return "";
		}else {
			return member.getPassword();
		}
	}

	@Override
	public String getUsername() {
		
		if(member == null) {
			return "";
		}else {
			return member.getUsername();
		}
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}

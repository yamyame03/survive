package kr.co.survive.vo;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {

	@Id
	private String username;
	private String password;
	private int level;
	private String regip;
	
	private String email;
	private String provider;
	private String providerId;

}

package kr.co.survivor.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class MakeAuthCode {
	
	// 이메일 인증코드 생성
	public String EmailAuthCode() {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		List<String> list = new ArrayList<>();
		
		for(int i = 0; i < 3; i++) {
			list.add(String.valueOf(random.nextInt(10)));
		}
		
		for(int i = 0; i < 3; i++) {
			list.add(String.valueOf((char)(random.nextInt(26)+65)));
			list.add(String.valueOf((char)random.nextInt(26)+65));
		}
		
		Collections.shuffle(list);
		
		for(String item : list) {
			sb.append(item);
		}
		
		return sb.toString();
	}
	
	public String HpAuthCode() {
		return "";
	}

}

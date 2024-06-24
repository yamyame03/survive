package kr.co.survivor.vo;

import lombok.Data;

@Data
public class ApiKeyVO {
	private String portOneApiKey;
	private String portOneSecretKey;
	private String coolSmsApiKey;
	private String coolSmsSecretKey;
	private String fromPhoneNumber;
	private String sendMsgContent;
}

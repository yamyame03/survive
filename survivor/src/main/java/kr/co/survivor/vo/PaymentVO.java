package kr.co.user.vo;

import lombok.Data;

@Data
public class PaymentVO {
	private int no;
	private String name;
	private int price;
	private int type;
	private String merchant_uid;
	private int date;
	private int zip;
	private String addr1;
	private String addr2;
}

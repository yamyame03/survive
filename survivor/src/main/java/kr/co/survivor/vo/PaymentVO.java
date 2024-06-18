package kr.co.survivor.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentVO {
	private int id;
	private int no;
	private String name;
	private String email;
	private String hp;
	private String merchant_uid;
	private int type;
	private int zip;
	private String addr;
	private String product_name;
	private int price;
	private int result;
	private String date;
}

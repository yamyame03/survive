package kr.co.survivor.vo;

import lombok.Data;

@Data
public class ProductVO {
	private int pno;
	private String name;
	private int price;
	private String description;
	private String img;
	private int delivery;
}

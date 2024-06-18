package kr.co.user.vo;

import lombok.Data;

@Data
public class CodeVO {
	private int cno;
	private String code;
	private String date;
	private String regip;
	private String modip;
    private long unixtime;
    private String username;
    private int total;
    
    public long getUnixtime() {
        return unixtime;
    }

    public void setUnixtime(long unixtime) {
        this.unixtime = unixtime;
    }
}

package kr.hongik.history.model;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("rank")
public class Rank {

	private String userNo;
	private String seq;
	
	private String total;
	
	private String regDt;
	
	private String userName;
}

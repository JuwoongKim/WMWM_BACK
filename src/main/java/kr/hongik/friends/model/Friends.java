package kr.hongik.friends.model;

import org.apache.ibatis.type.Alias;

import kr.hongik.member.model.Member;
import lombok.Data;

@Data
@Alias("friends")
public class Friends {
	private int seq;		//
    
    private String userNo;	//지인1
    private String subNo;	//지인2
	private String regDt;	//등록 일자

}

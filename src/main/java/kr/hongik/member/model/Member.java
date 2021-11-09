package kr.hongik.member.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("member")
public class Member {
	private long userNo;		//회원번호
	
    private String loginId;		//계정ID
	private String pwd;			//비밀번호
	private String userName;	//회원이름
	private String telno1;		//전화번호1
	private String email;		//이메일
	private String regDt;		//작성자
	private String regId;		//작성일시
	private String modDt;		//수정자
	private String modId;		//수정일시

}

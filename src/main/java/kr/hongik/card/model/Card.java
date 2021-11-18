package kr.hongik.card.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
* 개인정보
* @author Jeeun Lee
* 
* 2021.11.02
*/
@Data
@Alias("card")
public class Card {
	
	private long userNo;		//회원번호
	
    private String userName;		//성별
    private String telno1;			//나이
	private String email;	//생일
    
    private String gender;		//성별
    private String age;			//나이
	private String birthdate;	//생일
	private String mbti;		//MBTI
	private String residence;	//거주지역
    private String workType;		//근무형태
    private String workDetail;		//근무형태상세
    private String elementary;		//초등학교
    private String middle;		//중학교
    private String high;		//고등학교
    private String univ;		//대학교
	private String bloodType;	//혈액형
	private String fileId;		//프로필사진 ID
	private String motto;		//좌우명
	
	
	private String regDt;		//작성자
	private String regId;		//작성일시
	private String modDt;		//수정자
	private String modId;		//수정일시
	
	private String total;		//
	private String seq;		//

}

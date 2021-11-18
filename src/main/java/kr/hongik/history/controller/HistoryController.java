package kr.hongik.history.controller;


import com.fasterxml.jackson.core.JsonProcessingException;

import com.google.gson.Gson;

import kr.hongik.friends.model.Friends;
import kr.hongik.friends.service.FriendsService;
import kr.hongik.history.model.History;
import kr.hongik.history.service.HistoryService;
import kr.hongik.member.model.Member;
import kr.hongik.member.service.MemberService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    MemberService memberService;

    @Autowired
    FriendsService friendsService;
    
    @Autowired
    HistoryService historyService;

    @PostMapping("/")
    public String insertHistory (@RequestBody String objJson) {
        Gson gson = new Gson();
        History history = gson.fromJson(objJson, History.class);

      //  System.out.println("야호 성공이다....");
      //  System.out.println(history.toString());

        List<Member> users = memberService.getUserNo(history.getLoginId());
        List<Member> subs = memberService.getUserNo( history.getSubLoginId());

        String userNo = Long.toString(users.get(0).getUserNo());
        String subNo =  Long.toString(subs.get(0).getUserNo());
        System.out.println("유저 "+userNo+"과 "+subNo+"의 지인 등록 요청 시작");

        Friends friends  =new Friends();
        friends.setUserNo(userNo);
        friends.setSubNo(subNo);

        if(Integer.parseInt(userNo) > Integer.parseInt(subNo)) {
            friends.setUserNo(subNo);
            friends.setSubNo(userNo);
        }

        int rtnVal = 0;
        //rtnVal이 1이면 등록 성공, 1이 아니면 등록 오류로 간주
        if(friendsService.whetherFirstShare(friends) == 0) {
            //최초 등록
            System.out.println("최초 등록 시작");

            rtnVal = friendsService.insertFriends(friends);

            if(rtnVal ==1 ) System.out.println("최초 등록 성공");
            else System.out.println("최초 등록 실패");
        }

        //최초 등록, 재회 시 HISTORY INSERT
        System.out.println("HISTORY 등록 시작");
        friends.setSeq(friendsService.findFriendsSeq(friends));

        history.setSeq(friends.getSeq());
        rtnVal = friendsService.insertHistory2(history);

        if(rtnVal ==1 ) System.out.println("HISTORY 등록 성공");
        else System.out.println("HISTORY 등록 실패");



        String result = "";
        result = "200";
        JSONObject jsonob= new JSONObject();
        jsonob.put("result", result);

        return jsonob.toString();

    }
    
	/* 회원 - 상세 조회 */
	@GetMapping("/detail") 
	public String getHistoryInfoList (@RequestParam String seq) throws JsonProcessingException, ParseException {		
		
		JSONObject jsonob= new JSONObject();
		
		
		List<History> historyInfoList = historyService.getHistoryInfoList(seq);
		
		if (historyInfoList != null && historyInfoList.size() > 0) {
			for (int i = 0; i < historyInfoList.size(); i++) {

				History history = historyInfoList.get(i);
				
				String regDt = history.getRegDt();
				history.setDay(getDayOfweek(regDt));
				history.setBeforeDay(getTermOfDays(regDt));
			}
		}
		
		jsonob.put("historyInfoList", historyInfoList);
		System.out.println("historyInfoList:"+historyInfoList);
		
		return jsonob.toString();		
		
	}




    @PostMapping("/total")
    public String getTotal (@RequestBody String objJson) {
        Gson gson = new Gson();
        Friends friends = gson.fromJson(objJson, Friends.class);

      //  System.out.println("야호 성공이다....");
      //  System.out.println(history.toString());

        String userNoString = friends.getUserNo();
        String subNoString = friends.getUserNo();


        friends.setUserNo(userNoString);
        friends.setSubNo(subNoString);

        if(Integer.parseInt(userNoString) > Integer.parseInt(subNoString)) {
            friends.setUserNo(subNoString);
            friends.setSubNo(userNoString);
        }

        int rtnVal = 0;
        String total = String.valueOf(historyService.getTotal(friends));

        JSONObject jsonob= new JSONObject();
        jsonob.put("total", total);

        return jsonob.toString();

    }

    
  	/** * 날짜로 요일 구하기 * 
  	 * @param date - 요일 구할 날짜 
  	 * @return  string
  	 * @throws ParseException 
  	 * */ 
  	public String getDayOfweek(String date) throws ParseException { 
  		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd"); 
  		String[] week = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"}; 
  		Calendar cal = Calendar.getInstance(); 
  		Date getDate; 

  		getDate = format.parse(date); 
  		cal.setTime(getDate); 
  		int w = cal.get(Calendar.DAY_OF_WEEK)-1; 
  		
  		//System.out.println(week[w]);
  		return  week[w];
  	}
  	
  	
  	public String getTermOfDays(String date) throws ParseException { 
  		Date today = new Date();
  		Date subDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date); 
        long diffSec = (today.getTime() - subDate.getTime()) / 1000; //초 차이
        long diffDays = diffSec / (24*60*60); //일자수 차이


  		return  String.valueOf(diffDays);
  	}





}
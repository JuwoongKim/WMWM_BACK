package kr.hongik.friends.controller;

import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import kr.hongik.card.model.Card;
import kr.hongik.friends.model.Friends;
import kr.hongik.friends.service.FriendsService;
import kr.hongik.member.model.Member;
import kr.hongik.member.service.MemberService;

@RestController
@RequestMapping("/friends")
public class FriendsController {
	
	@Autowired
	private FriendsService friendsService;
	
	/* 지인 정보 - 목록 조회 */
	@GetMapping("/all") 
	public String selectFriendsInfoList (@RequestParam String userNo) throws JsonProcessingException {	
		JSONObject jsonob= new JSONObject();
		List<Card> list = null;
		
		if (!"".equals(userNo) && null != userNo) {
			list = friendsService.selectFriendsInfoList(userNo);
			jsonob.put("friendsInfoList", list);
		}
		
		return jsonob.toString();
	}
	
	/* 지인 정보 - 지인 등록  */
	@PostMapping("/") 
	public String insertFriends (@RequestBody String objJson) {
		Gson gson = new Gson();
		Friends friends = gson.fromJson(objJson, Friends.class);
		
		String userNo = friends.getUserNo();
		String subNo = friends.getSubNo();
		System.out.println("유저 "+userNo+"과 "+subNo+"의 지인 등록 요청 시작");
		
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
		rtnVal = friendsService.insertHistory(friends);
		
		if(rtnVal ==1 ) System.out.println("HISTORY 등록 성공");
		else System.out.println("HISTORY 등록 실패");
		
		JSONObject jsonob= new JSONObject();
		jsonob.put("rtnVal", rtnVal);
		
		return jsonob.toString();
		
	}

}

package kr.hongik.history.controller;


import com.google.gson.Gson;
import kr.hongik.friends.model.Friends;
import kr.hongik.friends.service.FriendsService;
import kr.hongik.history.model.History;
import kr.hongik.member.model.Member;
import kr.hongik.member.service.MemberService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    MemberService memberService;

    @Autowired
    FriendsService friendsService;

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








}
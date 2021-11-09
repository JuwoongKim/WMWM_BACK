package kr.hongik.login.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import kr.hongik.member.model.Member;
import kr.hongik.member.service.MemberService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private MemberService memberService;
	
	/* 로그인 */
	@PostMapping("/") 
	public String login (@RequestBody String objJson) {		
		Gson gson = new Gson();
		Member member = gson.fromJson(objJson, Member.class);
		
		String result = "";
		
		List<Member> memberList = memberService.login(member);
		if (memberList != null && memberList.size() > 0) {
			result = "200";
			System.out.println("로그인 성공");
		}else {
			System.out.println("로그인 정보 존재하지 않음");
			result = "300";
		}
		
		JSONObject jsonob= new JSONObject();
		jsonob.put("result", result);
		
		return jsonob.toString();
		
	}

}

package kr.hongik.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hongik.member.model.Member;
import kr.hongik.member.repository.MemberMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	//
	public List<Member> login(Member member){
		return Optional.ofNullable(memberMapper.login(member)).orElseGet(ArrayList::new);
	}
	
	//
	public List<Member> getUserNo(String loginId) {
		return Optional.ofNullable(memberMapper.getUserNo(loginId)).orElseGet(ArrayList::new);
	}

	//
	public List<Member> findAll(){
		return Optional.ofNullable(memberMapper.findAll()).orElseGet(ArrayList::new);
	}
}

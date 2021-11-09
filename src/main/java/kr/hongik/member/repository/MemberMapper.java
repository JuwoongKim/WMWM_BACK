package kr.hongik.member.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import kr.hongik.member.model.Member;

@Mapper
public interface MemberMapper {
	
	//로그인
	public List<Member> login(Member member);
	
	//사용자 아이디 
	public List<Member> getUserNo(String loginId);
	
	//사용자 전체 리스트
	public List<Member> findAll();

}

package kr.hongik.history.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.hongik.friends.model.Friends;
import kr.hongik.history.model.History;
import kr.hongik.member.model.Member;

@Mapper
public interface HistoryMapper {
	public int getTotal(Friends friends);
	
	public List<History> getHistoryInfoList(String seq);

}

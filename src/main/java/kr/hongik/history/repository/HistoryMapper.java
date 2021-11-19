package kr.hongik.history.repository;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.hongik.friends.model.Friends;
import kr.hongik.history.model.Hcount;
import kr.hongik.history.model.History;
import kr.hongik.history.model.Rank;
import kr.hongik.history.model.Tcount;
import kr.hongik.history.model.Wcount;
import kr.hongik.member.model.Member;

@Mapper
public interface HistoryMapper {
    /* 지인 정보 - 목록 조회 */
    public List<History> selectHistoryList(@Param("userNo") String userNo);

    public List<Tcount> selectTypeCount(@Param("userNo") String userNo);

    public List<Hcount> selectHourCount(@Param("userNo") String userNo);

    public List<Wcount> selectWeekCount(@Param("userNo") String userNo);
	
	
	public int getTotal(Friends friends);
	
	public List<History> getHistoryInfoList(String seq);

    public List<Rank> getPeriodRankList(String userNo, String stDate);
}

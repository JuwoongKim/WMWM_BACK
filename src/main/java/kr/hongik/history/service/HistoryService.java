package kr.hongik.history.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hongik.friends.model.Friends;
import kr.hongik.history.model.Hcount;
import kr.hongik.history.model.History;
import kr.hongik.history.model.Rank;
import kr.hongik.history.model.Tcount;
import kr.hongik.history.model.Wcount;
import kr.hongik.history.repository.HistoryMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HistoryService {

	@Autowired
	private HistoryMapper historyMapper;
	
    /* 지인 정보 - 목록 조회 */
    public List<History> selectHistoryList(String userNo) {
        return Optional.ofNullable(historyMapper.selectHistoryList(userNo)).orElseGet(ArrayList::new);
    }

    public List<Tcount> selectTypeCount(String userNo){
        return Optional.ofNullable(historyMapper.selectTypeCount(userNo)).orElseGet(ArrayList::new);

    }

    public List<Hcount> selectHourCount(String userNo){
        return Optional.ofNullable(historyMapper.selectHourCount(userNo)).orElseGet(ArrayList::new);

    }

    public List<Wcount> selectWeekCount(String userNo){
        return Optional.ofNullable(historyMapper.selectWeekCount(userNo)).orElseGet(ArrayList::new);

    }

	public int getTotal(Friends friends) {
		return Optional.ofNullable(historyMapper.getTotal(friends)).orElse(1);
	}
	
	public List<History> getHistoryInfoList(String seq){
		return Optional.ofNullable(historyMapper.getHistoryInfoList(seq)).orElseGet(ArrayList::new);	
	}
	
	public List<Rank> getPeriodRankList(String userNo, String stDate){
		return Optional.ofNullable(historyMapper.getPeriodRankList(userNo, stDate)).orElseGet(ArrayList::new);	
	}
}

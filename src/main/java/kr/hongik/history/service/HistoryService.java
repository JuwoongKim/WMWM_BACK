package kr.hongik.history.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hongik.friends.model.Friends;
import kr.hongik.history.model.History;
import kr.hongik.history.repository.HistoryMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HistoryService {

	@Autowired
	private HistoryMapper historyMapper;

	public int getTotal(Friends friends) {
		return Optional.ofNullable(historyMapper.getTotal(friends)).orElse(1);
	}
	
	public List<History> getHistoryInfoList(String seq){
		return Optional.ofNullable(historyMapper.getHistoryInfoList(seq)).orElseGet(ArrayList::new);

		
	}
}

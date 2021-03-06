package kr.hongik.card.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hongik.card.model.Card;
import kr.hongik.card.repository.CardMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CardService {
	
	@Autowired
	private CardMapper cardMapper;
	
	/* 내 정보 - 상세 조회 */
	public List<Card> selectMyInfo(String userNo) {
		return Optional.ofNullable(cardMapper.selectMyInfo(userNo)).orElseGet(ArrayList::new);
	}
	
	/* 내 정보 - 업데이트 */
	public int updateMyInfo(Card card) {
		return Optional.ofNullable(cardMapper.updateMyInfo(card)).orElse(1);
	}

	//주웅이 추가한 것
	public List<Card> selectMbti(String userNo) {
		return Optional.ofNullable(cardMapper.selectMbti(userNo)).orElseGet(ArrayList::new);
	}

}

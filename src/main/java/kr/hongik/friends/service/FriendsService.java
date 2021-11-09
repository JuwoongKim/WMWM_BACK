package kr.hongik.friends.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hongik.card.model.Card;
import kr.hongik.friends.model.Friends;
import kr.hongik.friends.repository.FriendsMapper;
import kr.hongik.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FriendsService {
	
	@Autowired
	private FriendsMapper friendsMapper;
	
	/* 지인 정보 - 목록 조회 */
	public List<Card> selectFriendsInfoList(String userNo) {
		return Optional.ofNullable(friendsMapper.selectFriendsInfoList(userNo)).orElseGet(ArrayList::new);
	}
	
	/* 지인 정보 - 최초 등록 여부 조회 */
	public int whetherFirstShare(Friends friends) {
		return Optional.ofNullable(friendsMapper.whetherFirstShare(friends)).orElse(0);

	}
	
	/* 지인 정보 - 최초 등록 */
	public int insertFriends(Friends friends) {
		return Optional.ofNullable(friendsMapper.insertFriends(friends)).orElse(1);

	}
	
	/* 지인 정보 - 재회 등록 */
	public int insertHistory(Friends friends) {
		return Optional.ofNullable(friendsMapper.insertHistory(friends)).orElse(1);

	}
	
	/* 지인 정보 - 지인 고유 번호 조회 */
	public int findFriendsSeq(Friends friends) {
		return Optional.ofNullable(friendsMapper.findFriendsSeq(friends)).orElse(0);

	}

}

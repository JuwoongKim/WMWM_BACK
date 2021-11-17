package kr.hongik.friends.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.hongik.card.model.Card;
import kr.hongik.friends.model.Friends;
import kr.hongik.history.model.History;

@Mapper
public interface FriendsMapper {
	
	/* 지인 정보 - 목록 조회 */
	public List<Card> selectFriendsInfoList(@Param("userNo") String userNo);
	
	/* 지인 정보 - 최초 등록 여부 조회 */
	public int whetherFirstShare(Friends friends);
	
	/* 지인 정보 - 지인 고유 번호 조회 */
	public int findFriendsSeq(Friends friends);
	
	/* 지인 정보 - 최초 등록 */
	public int insertFriends(Friends friends);
	
	/* 지인 정보 - 재회 등록 */
	public int insertHistory(Friends friends);
	
	public int insertHistory2(History history);

}

package kr.hongik.card.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.hongik.card.model.Card;


@Mapper
public interface CardMapper {
	
	/* 내 정보 - 상세 조회 */
	public List<Card> selectMyInfo(@Param("userNo") String userNo);
	
	/* 내 정보 - 업데이트 */
	int updateMyInfo(Card card);

}

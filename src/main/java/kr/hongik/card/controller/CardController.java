package kr.hongik.card.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.hongik.card.model.Card;
import kr.hongik.card.service.CardService;

@RestController
@RequestMapping("/card")
public class CardController {
	@Autowired
	private CardService cardService;
	
	/* 내 정보 - 상세 조회 */
	@GetMapping("/my") 
	public String selectMyInfo (@RequestParam String userNo) throws JsonProcessingException {	
		JSONObject jsonob= new JSONObject();
		List<Card> cardList = null;
		
		if (!"".equals(userNo) && null != userNo) {
			cardList = cardService.selectMyInfo(userNo);
			jsonob.put("cardInfo", cardList);
		}
		
		return jsonob.toString();
	}

}

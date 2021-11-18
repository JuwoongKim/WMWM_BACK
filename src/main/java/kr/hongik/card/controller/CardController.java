package kr.hongik.card.controller;

import java.io.File;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import kr.hongik.card.model.Card;
import kr.hongik.card.service.CardService;
import kr.hongik.common.utils.FileUtils;
import kr.hongik.common.model.FileInfo;
import kr.hongik.common.service.FileService;

@RestController
@RequestMapping("/card")
public class CardController {
	@Autowired
	private CardService cardService;
	
	@Autowired
	private FileService fileService;
	
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
	
	/* 내 정보 - 업데이트 */
	@PostMapping("/my") 
	public String updateMyInfo (@RequestBody String objJson) throws JsonProcessingException {	
		Gson gson = new Gson();
		Card card = gson.fromJson(objJson, Card.class);
		System.out.println(card);
		
		int rtnVal = 0;
		JSONObject jsonob= new JSONObject();
		if (card.getUserNo()!=0) {
			rtnVal = cardService.updateMyInfo(card);
			jsonob.put("result", rtnVal);
		}
		
		return jsonob.toString();
	}
	
	@PostMapping("/fileUpload") 
	public HashMap<String, Object> fileUpload (FileInfo fileInfo, @RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile, @RequestParam(value = "userNo") String userNo) throws JsonProcessingException {	
        System.out.println("file upload 호출");
        
        
        if(uploadFile == null) {
            System.out.println("null");
        }else {
        }
       
        HashMap<String, Object> hmap = new HashMap<>();
        String result = "fail";
        String fileId = "";
        
		try {
			// 파일이 있을때.
			if(uploadFile!=null && !uploadFile.getOriginalFilename().equals("")) {
				
				
				String originalFileName = uploadFile.getOriginalFilename();
				
				fileId = String.format("%012d", fileService.selectMaxFileId() + 1);
				
				//파일명 충돌방지
				String formatName = originalFileName.equals("") ? "" : originalFileName.substring(originalFileName.equals("") ? 0 : originalFileName.lastIndexOf(".") + 1);
				String savedName = UUID.randomUUID() + "." + formatName;
				
				// 업로드 경로
				String filePath = FileUtils.uploadMultiFile("C:/dev_01/upload/", uploadFile, savedName); // 파일 업로드 하고 파일 경로 리턴

				
				System.out.println(fileId);
				System.out.println(filePath);
				System.out.println(savedName);
				System.out.println(originalFileName);
				
				fileInfo.setFileId(fileId);
				fileInfo.setFilePath(filePath);
				fileInfo.setFileName(savedName);
				fileInfo.setFileOriginName(originalFileName);
				fileInfo.setRegId(userNo);
				
				fileService.insertFileInfo(fileInfo);

				hmap.put("fileId", fileId);
				
				result = "success";
			}			
			// 파일 업로드 없이 글을 등록하는 경우
			else
				result = "success";

		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(fileId);
		System.out.println(result);
		hmap.put("result", result);
		
		return hmap;
        
        
        
	}
	
	@GetMapping("/fileDownload")
	public ResponseEntity<InputStreamResource> fileDownload(@RequestParam(value = "fileId") String fileId) throws Exception {
		System.out.println(fileId);
		HashMap<String, Object> hmap = new HashMap<>();
		
		FileInfo fileInfo = fileService.selectFileInfo(fileId);
		File file = new File("C:/dev_01/upload/" + fileInfo.getFilePath());
		String fileName = file.getName();
		
		// 파일 확장자 
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1); 
		HttpHeaders header = new HttpHeaders(); 
		Path fPath = Paths.get(file.getAbsolutePath()); 
		
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName); 
		header.add("Cache-Control", "no-cache, no-store, must-revalidate"); 
		header.add("Pragma", "no-cache"); 
		header.add("Expires", "0");
		
		InputStreamResource resource3 = new InputStreamResource(new FileInputStream(file));
		
		return ResponseEntity.ok() 
				.headers(header) 
				.contentLength(file.length()) 
				.contentType(MediaType.parseMediaType("application/octet-stream")) 
				.body(resource3);




	}
	
	// 주웅이 추가한 것
	//MBTI 똒깥은것 조회
	@GetMapping("/mbti")
	public String selectMbti (@RequestParam String userNo) throws JsonProcessingException {
		JSONObject jsonob= new JSONObject();
		List<Card> cardList = null;

		if (!"".equals(userNo) && null != userNo) {
			cardList = cardService.selectMbti(userNo);
			jsonob.put("cardInfo", cardList);
		}

		System.out.println("============");
		System.out.println(cardList.size());
		System.out.println("============");

		return jsonob.toString();
	}

	

}

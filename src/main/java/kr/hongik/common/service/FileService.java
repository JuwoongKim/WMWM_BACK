package kr.hongik.common.service;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.hongik.common.model.FileInfo;
import kr.hongik.common.repository.FileMapper;
import kr.hongik.common.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileService {
	@Autowired
	private FileMapper fileMapper;

	
//	FileId MAX 조회
	public int selectMaxFileId() {
		return fileMapper.selectMaxFileId();
	}
	
	
//	파일 업로드
	public void insertFileInfo(FileInfo fileInfo) {
		fileMapper.insertFileInfo(fileInfo);
	}
	
	public FileInfo selectFileInfo(String fileId) {
		return fileMapper.selectFileInfo(fileId);
	}


}

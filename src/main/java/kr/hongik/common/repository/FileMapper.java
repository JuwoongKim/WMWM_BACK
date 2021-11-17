package kr.hongik.common.repository;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import kr.hongik.common.model.FileInfo;

@Mapper
public interface FileMapper {

	int selectMaxFileId();
	
	void insertFileInfo(FileInfo fileInfo);
	
	public FileInfo selectFileInfo(String fileId);
	
}

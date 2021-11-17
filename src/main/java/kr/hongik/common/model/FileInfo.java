package kr.hongik.common.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 * 파일 정보
 * @author SeuReon Sung
 * 2019.06.17
 */
@Data
@Alias("fileInfo")
public class FileInfo {
	private String fileId;
	private int seq;
	private String filePath;
	private String fileName;
	private String fileOriginName;
	private Date regDate;
	private String regId;
	private Date modDate;
	private String modId;
}

package egovframework.dev.test.vo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class TestVO {

	private int seq;
	private int[] seqList;
	private String title;
	private String contents;
	private Date regDtm;
	private int pageNo=1;
	private int firstIndex;
	private int lastIndex;
	private int ttcnt;
	private MultipartFile file;
	private String fileName;
	private String fileSaveName;


	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public java.util.Date getRegDtm() {
		return regDtm;
	}

	public void setRegDtm(java.util.Date regDtm) {
		this.regDtm = regDtm;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getTtcnt() {
		return ttcnt;
	}

	public void setTtcnt(int ttcnt) {
		this.ttcnt = ttcnt;
	}

	public int[] getSeqList() {
		return seqList;
	}

	public void setSeqList(int[] seqList) {
		this.seqList = seqList;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSaveName() {
		return fileSaveName;
	}

	public void setFileSaveName(String fileSaveName) {
		this.fileSaveName = fileSaveName;
	}


}

package egovframework.dev.board.vo;

import java.util.Date;

public class BoardVO {

	private int seq;
	private String title;
	private String contents;
	private Date regDtm;
	private String boardType;
	private int pageNo=1;
	private int firstIndex;
	private int lastIndex;
	private int ttcnt;
	private int[] seqArray;

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

	public Date getRegDtm() {
		return regDtm;
	}

	public void setRegDtm(Date regDtm) {
		this.regDtm = regDtm;
	}

	public String getBoardType() {
		return boardType;
	}

	public void setBoardType(String boardType) {
		this.boardType = boardType;
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

	public int[] getSeqArray() {
		return seqArray;
	}

	public void setSeqArray(int[] seqArray) {
		this.seqArray = seqArray;
	}

}

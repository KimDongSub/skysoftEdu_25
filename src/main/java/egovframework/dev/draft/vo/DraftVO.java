package egovframework.dev.draft.vo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;

public class DraftVO {

	private int seq;
	private Date regDtm;
	private int cPageNo = 1;
	private int firstIndex;
	private int lastRecordIndex;
	private int recordCountPerPage=5;
	//기안자
	private int userSeq;
	private String draftTypeCd;
	private String draftTypeNm;
	private String groupCd;
	private String rankCd;
	private String userNm;
	private String emerPhone;
	private String reason;
	private String etc;
	private String halfCd;
	private String docNm;
	private String halfTime;

	private String startDt;
	private String endDt;
	private String drafterNm;
	private String reviewerNm;
	private String approvalNm;
	private String stateNm;
	private String stateCd;
	private String stepNm;
	private String stepCd;
	private int ttcnt;
	private String mngNm;
	private String groupNm;
	private String rankNm;
	private String mngRankNm;
	private String mngGroupNm;
	private String mngTypeNm;
	private String mngTypeCd;
	private int mngRefSize;
	private String halfNm;
	private String reviewerCm;
	private String approvalCm;
	private String pageState;

	private MultipartFile file;
	private String useYn;
	private String saveFileNm;
	private String realFileNm;
	private String title;
	private String recoveryYn;


	//기안자 끝

	public int getcPageNo() {
	return cPageNo;
	}
	public void setcPageNo(int cPageNo) {
		this.cPageNo = cPageNo;
	}
	public int getFirstIndex() {
		return firstIndex;
	}
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public Date getRegDtm() {
		return regDtm;
	}
	public void setRegDtm(Date regDtm) {
		this.regDtm = regDtm;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	public String getDraftTypeCd() {
		return draftTypeCd;
	}
	public void setDraftTypeCd(String draftTypeCd) {
		this.draftTypeCd = draftTypeCd;
	}
	public String getGroupCd() {
		return groupCd;
	}
	public void setGroupCd(String groupCd) {
		this.groupCd = groupCd;
	}
	public String getRankCd() {
		return rankCd;
	}
	public void setRankCd(String rankCd) {
		this.rankCd = rankCd;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getEmerPhone() {
		return emerPhone;
	}
	public void setEmerPhone(String emerPhone) {
		this.emerPhone = emerPhone;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public String getHalfCd() {
		return halfCd;
	}
	public void setHalfCd(String halfCd) {
		this.halfCd = halfCd;
	}
	public String getDocNm() {
		return docNm;
	}
	public void setDocNm(String docNm) {
		this.docNm = docNm;
	}
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getDrafterNm() {
		return drafterNm;
	}
	public void setDrafterNm(String drafterNm) {
		this.drafterNm = drafterNm;
	}
	public String getReviewerNm() {
		return reviewerNm;
	}
	public void setReviewerNm(String reviewerNm) {
		this.reviewerNm = reviewerNm;
	}
	public String getApprovalNm() {
		return approvalNm;
	}
	public void setApprovalNm(String approvalNm) {
		this.approvalNm = approvalNm;
	}

	public String getHalfTime() {
		return halfTime;
	}
	public void setHalfTime(String halfTime) {
		this.halfTime = halfTime;
	}
	public String getStateNm() {
		return stateNm;
	}
	public void setStateNm(String stateNm) {
		this.stateNm = stateNm;
	}
	public String getStepNm() {
		return stepNm;
	}
	public void setStepNm(String stepNm) {
		this.stepNm = stepNm;
	}
	public String getStateCd() {
		return stateCd;
	}
	public void setStateCd(String stateCd) {
		this.stateCd = stateCd;
	}
	public String getStepCd() {
		return stepCd;
	}
	public void setStepCd(String stepCd) {
		this.stepCd = stepCd;
	}
	public String getDraftTypeNm() {
		return draftTypeNm;
	}
	public void setDraftTypeNm(String draftTypeNm) {
		this.draftTypeNm = draftTypeNm;
	}
	public int getTtcnt() {
		return ttcnt;
	}
	public void setTtcnt(int ttcnt) {
		this.ttcnt = ttcnt;
	}
	public int getLastRecordIndex() {
		return lastRecordIndex;
	}
	public void setLastRecordIndex(int lastRecordIndex) {
		this.lastRecordIndex = lastRecordIndex;
	}
	public String getMngNm() {
		return mngNm;
	}
	public void setMngNm(String mngNm) {
		this.mngNm = mngNm;
	}
	public String getGroupNm() {
		return groupNm;
	}
	public void setGroupNm(String groupNm) {
		this.groupNm = groupNm;
	}
	public String getRankNm() {
		return rankNm;
	}
	public void setRankNm(String rankNm) {
		this.rankNm = rankNm;
	}
	public String getMngRankNm() {
		return mngRankNm;
	}
	public void setMngRankNm(String mngRankNm) {
		this.mngRankNm = mngRankNm;
	}
	public String getMngTypeNm() {
		return mngTypeNm;
	}
	public void setMngTypeNm(String mngTypeNm) {
		this.mngTypeNm = mngTypeNm;
	}
	public String getMngTypeCd() {
		return mngTypeCd;
	}
	public void setMngTypeCd(String mngTypeCd) {
		this.mngTypeCd = mngTypeCd;
	}
	public String getMngGroupNm() {
		return mngGroupNm;
	}
	public void setMngGroupNm(String mngGroupNm) {
		this.mngGroupNm = mngGroupNm;
	}
	public int getMngRefSize() {
		return mngRefSize;
	}
	public void setMngRefSize(int mngRefSize) {
		this.mngRefSize = mngRefSize;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getSaveFileNm() {
		return saveFileNm;
	}
	public void setSaveFileNm(String saveFileNm) {
		this.saveFileNm = saveFileNm;
	}
	public String getRealFileNm() {
		return realFileNm;
	}
	public void setRealFileNm(String realFileNm) {
		this.realFileNm = realFileNm;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getHalfNm() {
		return halfNm;
	}
	public void setHalfNm(String halfNm) {
		this.halfNm = halfNm;
	}
	public String getReviewerCm() {
		return reviewerCm;
	}
	public void setReviewerCm(String reviewerCm) {
		this.reviewerCm = reviewerCm;
	}
	public String getApprovalCm() {
		return approvalCm;
	}
	public void setApprovalCm(String approvalCm) {
		this.approvalCm = approvalCm;
	}
	public String getPageState() {
		return pageState;
	}
	public void setPageState(String pageState) {
		this.pageState = pageState;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRecoveryYn() {
		return recoveryYn;
	}
	public void setRecoveryYn(String recoveryYn) {
		this.recoveryYn = recoveryYn;
	}

}

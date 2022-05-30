package kh.spring.myweb.board.model.vo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

// lombok 입맛대로 디버깅이 잘안됨
//@Getter
//@Setter
//@ToString
@Data
@Component
public class Board {
//	B_NO         NOT NULL NUMBER         
//	B_TITLE      NOT NULL VARCHAR2(300)  
//	B_CONTENT    NOT NULL VARCHAR2(3000) 
//	B_COUNT      NOT NULL NUMBER         
//	B_WRITE_DATE NOT NULL TIMESTAMP(6)   
//	B_WRITER     NOT NULL VARCHAR2(60)   
//	M_ID         NOT NULL VARCHAR2(20)   

	private int bNo;
	private String bTitle;
	private String bContent;
	private int bCount;
	private Timestamp bWriteDate;
	private String bWriter;
	private String mId;
	private int reCommentCnt;
	// 게시글 한개에 댓글이 여러개
	private List<ReComment> reCommentList;
	
	
	@Override
	public String toString() {
		return "Board [bNo=" + bNo + ", bTitle=" + bTitle + ", bContent=" + bContent + ", bCount=" + bCount
				+ ", bWriteDate=" + bWriteDate + ", bWriter=" + bWriter + ", mId=" + mId + ", reCommentCnt="
				+ reCommentCnt + ", reCommentList=" + reCommentList + "]";
	}
	
	public int getbNo() {
		return bNo;
	}
	public void setbNo(int bNo) {
		this.bNo = bNo;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getbContent() {
		return bContent;
	}
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	public int getbCount() {
		return bCount;
	}
	public void setbCount(int bCount) {
		this.bCount = bCount;
	}
	public Timestamp getbWriteDate() {
		return bWriteDate;
	}
	public void setbWriteDate(Timestamp bWriteDate) {
		this.bWriteDate = bWriteDate;
	}
	public String getbWriter() {
		return bWriter;
	}
	public void setbWriter(String bWriter) {
		this.bWriter = bWriter;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public int getReCommentCnt() {
		return reCommentCnt;
	}
	public void setReCommentCnt(int reCommentCnt) {
		this.reCommentCnt = reCommentCnt;
	}
	public List<ReComment> getReCommentList() {
		return reCommentList;
	}
	public void setReCommentList(List<ReComment> reCommentList) {
		this.reCommentList = reCommentList;
	}
	
	
}

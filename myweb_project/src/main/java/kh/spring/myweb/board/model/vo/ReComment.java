package kh.spring.myweb.board.model.vo;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class ReComment {
//	R_NO         NOT NULL NUMBER        
//	B_NO         NOT NULL NUMBER        
//	R_WRITER     NOT NULL VARCHAR2(60)  
//	R_WRITE_DATE NOT NULL TIMESTAMP(6)  
//	R_CONTENT    NOT NULL VARCHAR2(900) 
//	M_ID         NOT NULL VARCHAR2(20) 
	private int rNo;
	private int bNo;
	private String rWriter;
	private Timestamp rWriteDate;
	private String rContent;
	private String mId;
	
	@Override
	public String toString() {
		return "ReComment [rNo=" + rNo + ", bNo=" + bNo + ", rWriter=" + rWriter + ", rWriteDate=" + rWriteDate
				+ ", rContent=" + rContent + ", mId=" + mId + "]";
	}
	public int getrNo() {
		return rNo;
	}
	public void setrNo(int rNo) {
		this.rNo = rNo;
	}
	public int getbNo() {
		return bNo;
	}
	public void setbNo(int bNo) {
		this.bNo = bNo;
	}
	public String getrWriter() {
		return rWriter;
	}
	public void setrWriter(String rWriter) {
		this.rWriter = rWriter;
	}
	public Timestamp getrWriteDate() {
		return rWriteDate;
	}
	public void setrWriteDate(Timestamp rWriteDate) {
		this.rWriteDate = rWriteDate;
	}
	public String getrContent() {
		return rContent;
	}
	public void setrContent(String rContent) {
		this.rContent = rContent;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	
	
}

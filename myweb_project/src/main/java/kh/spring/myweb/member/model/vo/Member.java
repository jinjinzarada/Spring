package kh.spring.myweb.member.model.vo;

import org.springframework.stereotype.Component;

@Component("member")
public class Member {

//	private int a = 5;
//	public int sum(int b, int c) {
//		return a+b+c;
	
	private String mId;
	private String nickname;
	private String email;
	private String phone;
	private String password;
	
	private int page;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@Override
	public String toString() {
		return "Member [mId=" + mId + ", nickname=" + nickname + ", email=" + email + ", phone=" + phone + ", password="
				+ password + ", page=" + page + "]";
	}
	
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}

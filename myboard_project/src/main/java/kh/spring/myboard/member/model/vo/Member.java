package kh.spring.myboard.member.model.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Member {
//	ID VARCHAR2(15 BYTE),
//	 PASSWD VARCHAR2(15 BYTE) NOT NULL,
//	 NAME VARCHAR2(20 BYTE) NOT NULL,
//	 EMAIL VARCHAR2(30 BYTE),
//	 GENDER CHAR(1 BYTE) CHECK (GENDER IN ('M', 'F')),
//	 AGE NUMBER,
//	 PHONE CHAR(13 BYTE),
//	 ADDRESS VARCHAR2(50 BYTE),
//	 ENROLL_DATE DATE DEFAULT SYSDATE,
	private String id;
	private String passwd;
	private String name;
	private String email;
	private String gender;   // charAt(0)
	private String age;  //TODO
	private String phone;
	private String address;
	private Date enroll_date;
	
	@Override
	public String toString() {
		return "Member [id=" + id + ", passwd=" + passwd + ", name=" + name + ", email=" + email + ", gender=" + gender
				+ ", age=" + age + ", phone=" + phone + ", address=" + address + ", enroll_date=" + enroll_date + "]";
	}
	
	
}

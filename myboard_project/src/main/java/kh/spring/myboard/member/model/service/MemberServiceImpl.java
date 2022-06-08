package kh.spring.myboard.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.myboard.member.model.dao.MemberDao;
import kh.spring.myboard.member.model.vo.Member;

@Service
public class MemberServiceImpl {

	@Autowired
	private MemberDao dao;

	public int insertMember(Member member) {
		return dao.insertMember(member);

	}

	public Member selectLogin(Member member) {
		return dao.selectLogin(member);
	}
}
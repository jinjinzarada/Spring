package kh.spring.myboard.member.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.myboard.member.model.vo.Member;

@Repository
public class MemberDao {
	
	@Autowired
	private SqlSessionTemplate sqlsession;

	public int insertMember(Member member) {
		return sqlsession.insert("Member.insertMember", member);
	}
	
	public Member selectLogin(Member member) {
		return sqlsession.selectOne("Member.selectLogin", member);
	}

}

package kh.spring.myweb.member.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.aspectj.lang.annotation.Pointcut;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.myweb.member.model.vo.Member;

@Repository("memberDao")
public class MemberDao {
	private static final Logger logger = LoggerFactory.getLogger(MemberDao.class);
	
	@Autowired
//  의 기능 : SqlSession 자료형으로 객체가 만들어진 것이 있다면 찾아서 사용하겠음요.	
//  찾아줘, 이미 생성된 객체있는데 그걸 가져와 쓰고싶음요
//	private SqlSession sqlSession;
	
	private SqlSessionTemplate sqlSession;
//  이게 좀더 명확쓰요	
	
	public Member login(String id, String pwd) {
		HashMap<String, String> map= new HashMap<String, String>();
		map.put("id", id);
		map.put("pwd", pwd);
		return sqlSession.selectOne("memberMapper.login", map);
	}
	public int insertMember(Member vo) {
		return sqlSession.insert("memberMapper.insertMember", vo);
	}
	public int updateMember(Member vo) {
		return sqlSession.update("memberMapper.updateMember", vo);
	}
	public int deleteMember(Member vo) {
		return sqlSession.delete("memberMapper.deleteMember", vo);
	}
	public List<Member> selectAllMember() {
		return sqlSession.selectList("memberMapper.selectAllMember");
	}
	public Member selectOneMember(String mId){
		return sqlSession.selectOne("memberMapper.selectOneMember", mId);
	}
	
}

package kh.spring.myweb.member.model.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kh.spring.myweb.member.model.dao.MemberDao;
import kh.spring.myweb.member.model.vo.Member;

@Service
@Transactional
public class MemberService {
	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);
	
	//private MemberDao memberDao = new MemberDao();
	@Autowired
	private MemberDao memberDao;

	public Member login(String id, String pwd) {
//		String a;
//		a.length();
//		logger.info("login srv");
		return memberDao.login(id, pwd);
	}
	public int insertMember(Member vo) {
		return memberDao.insertMember(vo);
//		int result = memberDao.updateMember(vo);
//		result =  memberDao.insertMember(vo);
//		return result;
	}
	public int updateMember(Member vo) {
		return memberDao.updateMember(vo);
	}
	public int deleteMember(Member vo) {
		return memberDao.deleteMember(vo);
	}
	public List<Member> selectAllMember(){
		return memberDao.selectAllMember();
	}
	public Member selectOneMember(String mId){
		return memberDao.selectOneMember(mId);
	}
	
}

package kh.spring.myweb.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.myweb.board.model.vo.Board;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int selectTotalCnt() {
		return sqlSession.selectOne("Board.selectTotalCnt");
	}
	
	public List<Board> selectBoardList(int currentPage, int pageSize){
		return sqlSession.selectList("Board.selectBoardList", null, new RowBounds((currentPage-1)*pageSize , pageSize));
	}
	
	public Board selectBoardAndReComment(int bNo){
// 规过 1
//		return sqlSesson.selectOne("Board.selectBoardAndReComment", bNo);
// 规过 2
		Board board = sqlSession.selectOne("Board.selectBoard", bNo);
		board.setReCommentList(sqlSession.selectList("Board.selectReCommentList", bNo));
		return board;
	}
	
	public int deleteBoard(int bNo){
		return sqlSession.delete("Board.deleteBoard", bNo);
	}
	
	public int insertBoard(Board board){
		return sqlSession.insert("Board.insertBoard", board);
	}
	
}

package kh.spring.myboard.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.myboard.board.model.vo.Board;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlsession;
	
	// 원글
	public int insertBoard(Board board) {
		return sqlsession.insert("Board.insertBoard",board);
	}
	// 답글 seq 업데이트
	public int updateBoardReplySeq(Board board) {
		return sqlsession.update("Board.updateBoardReplySeq",board);
	}
	// 답글
	public int insertBoardReply(Board board) {
		return sqlsession.insert("Board.insertBoardReply",board);
	}
	
	
	public List<Board> selectBoardListAll(){
		return sqlsession.selectList("Board.selectBoardListAll");
	}
	public Board selectBoard(String board_num){
		return sqlsession.selectOne("Board.selectBoard", board_num);
	}
}

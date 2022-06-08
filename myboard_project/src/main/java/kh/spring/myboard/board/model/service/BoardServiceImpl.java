package kh.spring.myboard.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.myboard.board.model.dao.BoardDao;
import kh.spring.myboard.board.model.vo.Board;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDao dao;

	@Override
	public int insertBoard(Board board) {
		if(board.getRefnum() > 0) {
			// 답글
			dao.updateBoardReplySeq(board);
			return dao.insertBoardReply(board);
		} else {
			// 원글
			return dao.insertBoard(board);
		}
	}

	@Override
	public int updateBoard(Board board) {
		return dao.updateBoard(board);
	}

	@Override
	public int deleteBoard(String board_num) {
		return dao.deleteBoard(board_num);
	}

	@Override
	public int deleteBoard(List<String> board_num) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Board selectBoard(String board_num) {
		return dao.selectBoard(board_num);
	}

	@Override
	public List<Board> selectBoardListAll() {
		return dao.selectBoardListAll();
	}
	
}

package kh.spring.myweb.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.myweb.board.model.dao.BoardDao;
import kh.spring.myweb.board.model.vo.Board;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao dao;
	
	public int selectTotalCnt() {
		return dao.selectTotalCnt();
	}
	
	public List<Board> selectBoardList(int currentPage, int pageSize){
		return dao.selectBoardList(currentPage, pageSize);
	}
	
	public Board selectBoardAndReComment(int bNo){
		return dao.selectBoardAndReComment(bNo);
	}
	public int deleteBoard(int bNo){
		return dao.deleteBoard(bNo);
	}
	public int insertBoard(Board board){
		return dao.insertBoard(board);
	}
	
}

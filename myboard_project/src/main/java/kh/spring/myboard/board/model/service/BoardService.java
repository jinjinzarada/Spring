package kh.spring.myboard.board.model.service;

import java.util.List;

import kh.spring.myboard.board.model.vo.Board;

public interface BoardService {
	/**
	 * @param board 게시글로 추가할 내용 
	 * @return 추가된 글 갯수
	 * @author Jinny
	 */
	public int insertBoard(Board board);
	/**
	 *@param board 수정할 글번호 
	 *@return 수정된 글의 갯수
	 */
	public int updateBoard(Board board);
	/**
	 * @param board_num 삭제할 글번호 하나
	 * @return 삭제된 글의 갯수
	 */
	public int deleteBoard(String board_num); // 하나의 글을 삭제  
	
	/**
	 * @param board_num 삭제할 글번호 리스트
	 * @return : 삭제된 글의 갯수
	 * 
	 */
	
	public int deleteBoard(List<String> board_num); //여러 글 삭제, return 삭제된 글 갯수
	
	public Board selectBoard(String board_num);
	public List<Board> selectBoardListAll();
	
	/** 
	* @param pageNum 읽을 페이지
	* @param limit 읽을 게시글 수
	* @param searchWord 검색할 단어
	* @return 추가된 글개수
	*/ 
//	public List<Board> selectBoardListAll(int pageNum, int limit, String searchWord);
}

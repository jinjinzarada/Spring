package kh.spring.myboard.board.controller;

import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kh.spring.myboard.board.model.service.BoardService;
import kh.spring.myboard.board.model.service.BoardServiceImpl;
import kh.spring.myboard.board.model.vo.Board;
import kh.spring.myboard.common.FileUpload;
import kh.spring.myboard.member.model.vo.Member;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardServiceImpl service;
	@Autowired
	private FileUpload commonFile;
	
	@GetMapping("/write")
	public ModelAndView pageInsert(ModelAndView mv
			, @RequestParam(name="refnum", defaultValue = "0") String refnumStr
			) {
		int refnum = 0;
		try {
			refnum = Integer.parseInt(refnumStr);
		}catch (Exception e) {
		}
		
		System.out.println("[[쵠진]]"+refnum);
		
		mv.addObject("refnum", refnum);
		mv.setViewName("board/insert");
		return mv;
	}
	@PostMapping("/write")
	public ModelAndView insert(ModelAndView mv
			, Board board
			, HttpSession session
			, @RequestParam(name="uploadfile", required = false) MultipartFile uploadfile
			, HttpServletRequest req
			) {
		// 로그인 정보 확인하여 작성자
		Member member = (Member) session.getAttribute("loginSsInfo");
		if (member == null) { 
			mv.setViewName("redirect:/member/login");
			return mv;
		}
		board.setBoard_writer(member.getId());
		
		// 첨부파일있다면 첨부파일 저장
		if(uploadfile !=null) {
			String rename_filename = commonFile.saveFile(uploadfile, req);
			if(rename_filename != null) {
				//파일저장에 성공하면 DB에 저장할 데이터를 채워줌
				board.setBoard_original_filename(uploadfile.getOriginalFilename());
				board.setBoard_rename_filename(rename_filename);
			}
		}
		
		// db글 insert
		int result = service.insertBoard(board);
		mv.setViewName("redirect:/");
		return mv;
	}
	@GetMapping("/list")
	public ModelAndView selectList(ModelAndView mv) {
		
		mv.addObject("boardlist", service.selectBoardListAll());
		mv.setViewName("board/list");
		return mv;
	}
	@GetMapping("/read")
	public ModelAndView selectBoard(ModelAndView mv
			, @RequestParam(name="board_num", required = false) String board_num
			, RedirectAttributes rttr
			) {
		if(board_num == null) {
			rttr.addFlashAttribute("msg", "읽을 글번호가 없습니다. 읽을 글을 다시 선택해주세요");
			mv.setViewName("redirect:/board/list");
		}
		mv.addObject("board", service.selectBoard(board_num));
		mv.setViewName("board/read");
		return mv;
	}
	
	@PostMapping("/update")
	public ModelAndView pageupdateBoard(ModelAndView mv
			, @RequestParam("board_num") String board_num			
			) {
		mv.addObject("board", service.selectBoard(board_num));
		mv.setViewName("board/update");
		return mv;
	}
	@PostMapping("/updateDo")
//	화면에 안보여짐 구리게 지어도 상관없삼
	public ModelAndView updateBoard(ModelAndView mv
			, Board board
			, @RequestParam(name="uploadfile", required = false) MultipartFile uploadfile
			, HttpServletRequest req
			) {
		
		String before_rename_filename= board.getBoard_rename_filename();
		String before_original_filename = board.getBoard_original_filename();
		
		// 변경할첨부파일 있다면 첨부파일 저장
		if(uploadfile !=null) {
			String rename_filename = commonFile.saveFile(uploadfile, req);
			if(rename_filename != null) {
				//파일저장에 성공하면 DB에 저장할 데이터를 채워줌
				board.setBoard_original_filename(uploadfile.getOriginalFilename());
				board.setBoard_rename_filename(rename_filename);
				
				// 기존 파일 있다면 파일서버에서 삭제함
				if(before_rename_filename !=null && !before_rename_filename.equals("")) {
					commonFile.removeFile(before_rename_filename, req);
				}
			}
		} 
		// 변경할첨부파일 없고 기존첨부파일명도 삭제되어있다면 기존 파일 삭제하고 업데이트해야함.
		else if(before_original_filename ==null || before_original_filename.equals("")) {
			board.setBoard_original_filename(null);
			board.setBoard_rename_filename(null);
			if(before_rename_filename !=null && !before_rename_filename.equals("")) {
				commonFile.removeFile(before_rename_filename, req);
			}
		}
		
		// DB글 update
		int result = service.updateBoard(board);
		
		
		mv.setViewName("redirect:/board/list");
		return mv;
	}
	
//	@PostMapping("/delete")
//	public ModelAndView deleteBoard(ModelAndView mv
//		,@RequestParam(name="board_num", required = false) String board_num
//		,RedirectAttribute rttr
//
//		) {
//		int result = service.deleteBoard(board_num);
//		if(result>0) {
//			rttr.addFlashAttribute("msg","게시글"+board_num+"번 삭제되었습니다.");
//		}
//		mv.setViewName("redirect:/board/list");
//		return mv;
//	}
//	ajax방식 - ModelAndView 사용안함요
//	@PostMapping("/delete")
//	public String deleteBoard(
//			@RequestParam(name="board_num", required = false) String board_num
////			, PrintWriter out
//			) {
//				
//			int result = service.deleteBoard(board_num);
//			String msg="";
//			if(result>0) {
//				msg="게시글"+board_num+"번 삭제되었습니다.";
//			} else {
//				msg="게시글"+board_num+"번 삭제되었습니다.";
//			}
//		
////		out.print(msg);
////		out.flush();
////		out.close();
//			
//	}
	
//	ajax방식 - @ResponseBody, 한글깨짐
	@PostMapping(value="/delete", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String deleteBoard(
			@RequestParam(name="board_num", required = false) String board_num
		) {
		int result = service.deleteBoard(board_num);
		String msg="";
		if(result>0) {
			msg= "게시글 "+board_num+"번 삭제되었습니다.";
		}else {
			msg="게시글이 삭제되지 못했습니다. 다시 확인하고 삭제해주세요.";
		}
		return msg;
	}
}

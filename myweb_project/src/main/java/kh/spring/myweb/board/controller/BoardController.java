package kh.spring.myweb.board.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kh.spring.myweb.board.model.service.BoardService;
import kh.spring.myweb.board.model.vo.Board;
import kh.spring.myweb.member.model.vo.Member;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService service;

	// @RequestMapping(value = "/list" ,method = RequestMethod.GET)
	@GetMapping("/list")
	public ModelAndView list(@RequestParam(name = "page", defaultValue = "1") int currentPage, ModelAndView mv,
			@RequestParam(name = "test1", defaultValue = "1") int test1,
			@RequestParam(name = "test2", defaultValue = "2") int test2,
			@RequestParam(name = "test5", defaultValue = "5") int test5
			, HttpServletRequest req) {
		System.out.println(req.getParameter("test5"));

		final int pageSize = 5;
		final int pageBlock = 3;
		int totalCnt = service.selectTotalCnt();

		// paging 처리
		int pageCnt = (totalCnt / pageSize) + (totalCnt % pageSize == 0 ? 0 : 1);
		int startPage = 1;
		int endPage = 1;
		if (currentPage % pageBlock == 0) {
			startPage = ((currentPage / pageBlock) - 1) * pageBlock + 1;
		} else {
			startPage = (currentPage / pageBlock) * pageBlock + 1;
		}
		endPage = startPage + pageBlock - 1;
		if (endPage > pageCnt) {
			endPage = pageCnt;
		}

		List<Board> boardlist = service.selectBoardList(currentPage, pageSize);
		mv.addObject("boardVolist", boardlist);
		mv.addObject("startPage", startPage);
		mv.addObject("endPage", endPage);
		mv.addObject("pageCnt", pageCnt);
		mv.addObject("currentPage", currentPage);
		mv.setViewName("board/boardlist");
//		System.out.println(volist);
//		
//		request.setAttribute("boardVolist", volist);
//		request.setAttribute("startPage", startPage);
//		request.setAttribute("endPage", endPage);
//		request.setAttribute("pageCnt", pageCnt);
//		request.setAttribute("currentPage", currentPage);
//		request.getRequestDispatcher("WEB-INF/view/board/boardlist.jsp").forward(request, response);
		return mv;
	}

	@GetMapping("/read")
	public ModelAndView read(ModelAndView mv, @RequestParam(name = "bno", defaultValue = "0") int bNo) {
		if (bNo == 0) {
			mv.setViewName("redirect:/board/list");
			return mv;
		}
		// db 읽어오기
		Board board = service.selectBoardAndReComment(bNo);
		board.setbContent(board.getbContent().replaceAll("(\r\n|\n)", "<br>"));
		board.setbContent(board.getbContent().replaceAll(" ", "&nbsp;"));
		mv.addObject("board", board);
		mv.setViewName("board/boardread");
		return mv;
	}

	@GetMapping("/delete")
	public ModelAndView delete(ModelAndView mv, @RequestParam(name = "bNo", defaultValue = "0") int bNo,
			RedirectAttributes rttr) {
		if (bNo == 0) {
			mv.setViewName("redirect:/board/list");
			return mv;
		}
		int board = service.deleteBoard(bNo);
		mv.addObject("test1", bNo); // @RequestParam url에 보임
		
		if (board > 0) {
			rttr.addFlashAttribute("test4", "게시글" + bNo + "번 삭제 되었습니다."); // url에 안보임.. jsp에서 사용가능함. ${test4} 1회만가능
		} else {
			rttr.addFlashAttribute("test4", "게시글" + bNo + "번 삭제에 실패했습니다.");// url에 안보임.. jsp에서 사용가능함. ${test4} 1회만가능
		}
		mv.setViewName("redirect:/board/list");
		return mv;
	}

	// @RequestMapping(value = "/writeWithFile" ,method = RequestMethod.GET)
	@GetMapping("/write")
//	public ModelAndView write(ModelAndView mv, HttpSession session) {
//		Member member = (Member) session.getAttribute("loginSsInfo");
//		if (member == null) { // 로그인안되어있다면 로그인으로 이동
//			mv.setViewName("redirect:/login");
//			return mv;
//		}
//		mv.setViewName("board/write");
//		return mv;
//	}
	public String write(ModelAndView mv, HttpSession session) {
		Member member = (Member) session.getAttribute("loginSsInfo");
		if (member == null) { // 로그인안되어있다면 로그인으로 이동
			return "redirect:/login";
		}
		return "board/write";
	}
	
//	@RequestMapping(value = "/write" ,method = RequestMethod.POST)
	@PostMapping("/write")
	public ModelAndView writeDo(ModelAndView mv, Board board, HttpSession session) {
		Member member = (Member) session.getAttribute("loginSsInfo");
		if (member == null) { // 로그인안되어있다면 로그인으로 이동
			mv.setViewName("redirect:/login");
			return mv;
		}
		board.setmId(member.getmId());
		//board.setbWriter(member.getNickname());

		int result = service.insertBoard(board);

		mv.setViewName("redirect:/board/list");
		return mv;
	}

	@PostMapping("/writeWithFile")
	public ModelAndView writeWithFileDo(
			ModelAndView mv
			, @RequestParam(name = "defaultImgFile", required = false) MultipartFile defaultfile
			, @RequestParam(name = "uploadfile", required = false) MultipartFile[] files
			, Board board
			, HttpServletRequest req
			, HttpSession session
			) {
		//ORA-01400: cannot insert NULL into ("SCOTT"."BOARD"."B_WRITER")
		Member member = (Member) session.getAttribute("loginSsInfo");
		if (member == null) { // 로그인안되어있다면 로그인으로 이동
			mv.setViewName("redirect:/login");
			return mv;
		}
		board.setmId(member.getmId());
		if(defaultfile != null) {
			String dbFilePath = saveFile(defaultfile, req);
			if (dbFilePath !=null) {
				System.out.println("여기");
				board.setbFilePath(dbFilePath);
			}
		}
		// 멀티파일 저장
		List<String> dbFilesPath = null;
		if(files != null) {
			dbFilesPath = new ArrayList<String>();
			for(int i=0; i<files.length;i++) {
				if(files[i] !=null && !files[i].equals("")) {
					dbFilesPath.add(saveFile(files[i], req));
				}
			}
		}
		System.out.println(dbFilesPath);
		int result = service.insertBoard(board);
		mv.setViewName("redirect:/board/list");
		return mv;
	}
	private String saveFile(MultipartFile report, HttpServletRequest request) {
		String dbFilePath = null;
		String resourcesPath = "resources";
		String uploadPath = "uploadFiles";
		long currentTime = System.currentTimeMillis();
		
		String root = request.getSession().getServletContext().getRealPath(resourcesPath);
		//C:\z_workspace\z_spring\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\myweb_project\resources
		String savePath = root + "\\"+ uploadPath;
		File folder = new File(savePath);
		if (!folder.exists()) {
			folder.mkdirs(); // 폴더가 없다면 생성한다. s 꼭 붙이는 걸 추천요
		}
		String filePath = null;
		try {
			// 파일 저장
			System.out.println(report.getOriginalFilename() + "을 저장합니다.");
			System.out.println("저장 경로 : " + savePath);
			filePath = folder + "\\" +currentTime+ report.getOriginalFilename();
			report.transferTo(new File(filePath)); // 파일을 저장한다
			System.out.println("파일 명 : " + report.getOriginalFilename());
			System.out.println("파일 경로 : " + filePath);
			System.out.println("파일 전송이 완료되었습니다.");
			//TODO  reanme report.getOriginalFilename()--> TODO
			dbFilePath = resourcesPath+"/"+uploadPath+"/"+currentTime+report.getOriginalFilename();
			
		} catch (Exception e) {
			System.out.println("파일 전송 에러 : " + e.getMessage());
		}
		return dbFilePath;
	}

	private void removeFile(String board_file, HttpServletRequest request) {
		String resourcesPath = "resources";
		String uploadPath = "uploadFiles";
		
		String root = request.getSession().getServletContext().getRealPath(resourcesPath);
		String savePath = root + "\\"+ uploadPath;

		String filePath = savePath + "\\" + board_file;
		try {
			// 파일 저장
			System.out.println(board_file + "을 삭제합니다.");
			System.out.println("기존 저장 경로 : " + savePath);

			File delFile = new File(filePath);
			delFile.delete();

			System.out.println("파일 삭제가 완료되었습니다.");
		} catch (Exception e) {
			System.out.println("파일 삭제 에러 : " + e.getMessage());
		}
	}
}
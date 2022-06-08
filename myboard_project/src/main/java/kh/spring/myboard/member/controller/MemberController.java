package kh.spring.myboard.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kh.spring.myboard.member.model.service.MemberServiceImpl;
import kh.spring.myboard.member.model.vo.Member;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberServiceImpl service;
	
	@Inject
	private BCryptPasswordEncoder pwdEncoding;

//	@GetMapping("/enroll")
	@RequestMapping(value="enroll", method= RequestMethod.GET)
	public ModelAndView pageInsert(ModelAndView mv) {
		mv.setViewName("member/insert");
		return mv;
	}
	@PostMapping("/enroll")
	public ModelAndView insert(ModelAndView mv
//			, @RequestParam(name="title", defaultValue="") String t1
//			, @RequestParam(name="title", required = false) String t1
			, Member member
			, RedirectAttributes rttr
			, HttpServletRequest req
			) {
		String t2 = req.getParameter("title");
		if(t2==null) {
//			t2="aaa"; , defaultValue = "aaa"
			t2 = null; 	//	required = false
		}
//		암호화 member.setPasswd(pwdEncoding.encode(member.getPasswd()));
		int result = service.insertMember(member);
		if(result < 1) {
			rttr.addFlashAttribute("msg", "가입에 실패했습니다. 다시 회원가입 시도해주세요.");
			mv.setViewName("redirect:/member/enroll");
			return mv;
		}
		mv.setViewName("redirect:/");
		return mv;
	}
	
	
	@GetMapping("/login")
	public ModelAndView pageLogin(ModelAndView mv) {
		mv.setViewName("member/login");
		return mv;
	}
	@PostMapping("/login")
	public ModelAndView selectLogin(ModelAndView mv
			, Member member
			, RedirectAttributes rttr
			, HttpSession session
			) {
		Member result = service.selectLogin(member);
		
//		암호화 member.setPasswd(pwdEncoding.encode(member.getPasswd()));
		// 오류인 케이스
		if(result == null) {
			rttr.addFlashAttribute("msg","로그인에 실패했습니다. 아이디와 패스워드를 다시 확인해 주세요.");
			// 로그인실패했다 알림 후 로그인 페이지로 이동 
			mv.setViewName("redirect:/member/login");
			return mv;
		}		
			// 정상적 케이스 (로그인성공)
			session.setAttribute("loginSsInfo", result);
			rttr.addFlashAttribute("msg",result.getName()+"로그인 되었습니다.");
			mv.setViewName("redirect:/");
			return mv;
		
	}
	
}


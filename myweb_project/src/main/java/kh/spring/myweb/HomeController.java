package kh.spring.myweb;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.spring.myweb.member.model.service.MemberService;
import kh.spring.myweb.member.model.vo.Member;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private MemberService memberSrv;
//	@Autowired
//	@Qualifier("member") 쓰려면 autowired와 세뚜세뚜
//	private Member member;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
//	@RequestMapping(value = "/", method = RequestMethod.GET)
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value="/login")
	public String login() {
		return "member/login";
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/login")
	public String loginPost(HttpServletRequest req
			, HttpSession session
			, @RequestParam("mId") String id2
			, @RequestParam(name="password") String pwd2
			, @RequestParam(name="pwdf", required = false) String pwdf
			, @RequestParam(name="pwdrf", defaultValue = "x") String pwdrf
			, @RequestParam(name="page", defaultValue="1") int page

			, Member vo
			
			) {
		
		Member result = memberSrv.login(id2, pwd2);
		System.out.println(result);
		if(result != null) {
			// 로그인 성공 세션에 "loginSsInfo"
			session.setAttribute("loginSsInfo", result);
		} 
		
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}

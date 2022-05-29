package kh.spring.myweb.temp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TempViewPageController {

	@RequestMapping("/temp")
	public String temp() {
		String viewPage="원하는 view이름 작성함요";
		return viewPage;
	}
}

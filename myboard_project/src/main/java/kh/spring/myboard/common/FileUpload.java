package kh.spring.myboard.common;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUpload {
	
	public String saveFile(MultipartFile report, HttpServletRequest request) {
		String result_filename = null;
		long timeForRename = System.currentTimeMillis();
		String realPath = request.getSession().getServletContext().getContextPath();
		// C:\z_workspace\z_spring\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\myboard_project\
		String savePath = "\\resources\\uploadFiles";
		File folder = new File(realPath+savePath);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		String filePath = folder+"\\"+ timeForRename +"_"+report.getOriginalFilename();
		try {
			report.transferTo(new File(filePath));// 파일 저장
			
			// 파일저장 성공
			result_filename = "/resources/uploadFiles/"+ timeForRename +"_"+report.getOriginalFilename();
		} catch (IllegalStateException e) {
			// 파일저장 실패
			e.printStackTrace();
		} catch (IOException e) {
			// 파일저장 실패
			e.printStackTrace();
		}   
		// 파일저장 실패시 null, 성공시 저장한파일경로와파일명
		return result_filename;
	}

}

package com.hy.security.core.validate.code.controller;


import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.hy.security.core.validate.code.entity.ImageCode;
import com.hy.security.core.validate.code.validate.ValidateCodeGenerator;




/**
 * 图片验证码的控制类
 * @author hy
 *
 */
//@RestController
public class AValidateCodeController {
	
	public static final String SESSION_KEY = "SESSION_KEY_IMAGE_COOE";
	
	/**
	 * 操作session的工具类
	 */
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	@Autowired
	private ValidateCodeGenerator validateCodeGenerator;
	
	@GetMapping("/code/image")
	public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws IOException {
		
		ImageCode imageCode = (ImageCode) validateCodeGenerator.generate(new ServletWebRequest(request,response)); 
		
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
		
		ImageIO.write(imageCode.getImage(), "JPEG",response.getOutputStream());
	}
}

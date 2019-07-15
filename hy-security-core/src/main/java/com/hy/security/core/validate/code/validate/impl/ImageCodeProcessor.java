/**
 * 
 */
package com.hy.security.core.validate.code.validate.impl;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.hy.security.core.validate.code.entity.ImageCode;
import com.hy.security.core.validate.code.validate.AbstractValidateCodeProcessor;


/**
 * 图片验证码处理器
 * 
 * @author zhailiang
 *
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

	/**
	 * 发送图形验证码，将其写到响应中
	 */
	@Override
	protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
		ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
	}

}

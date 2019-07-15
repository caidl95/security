package com.hy.security.core.validate.code.validate.impl;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.hy.security.core.properties.SecurityProperties;
import com.hy.security.core.validate.code.entity.ImageCode;
import com.hy.security.core.validate.code.validate.ValidateCodeGenerator;


/**
 *  校验图片的生成器实现类
 * @author hy
 *
 */
@Component("imageValidateCodeGenerator")
public class ImageValidateCodeGenerator implements ValidateCodeGenerator {
	
	@Autowired
	private SecurityProperties securityProperties;

	/**
	 * 随机生成图片
	 * @param request
	 * @return
	 */
	@Override
	public ImageCode generate(ServletWebRequest request) {
		//request中如果没有width/height/length/expireIn 则在配置文件里获取
		int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width", securityProperties.getCode().getImage().getWidth());
		int height =ServletRequestUtils.getIntParameter(request.getRequest(), "height", securityProperties.getCode().getImage().getHeight());
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		 // 获取图形上下文
		Graphics g = image.getGraphics();
		 //生成随机类
		Random random = new Random();
		 // 设定背景色
		g.setColor(getRandColor(200,250));
		g.fillRect(0, 0, width, height);
		 //设定字体
		g.setFont(new Font("Times New Roman",Font.ITALIC,20));
		 // 随机产生168条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160,200));
		for(int i=0;i<155;i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(12);
			int y1 = random.nextInt(12);
			g.drawLine(x, y,x + x1,y + y1);
		}
		 //取随机产生的码
		String sRand = "";
		  //4代表4位验证码,如果要生成更多位的认证码,则加大数值
		for(int i=0;i<securityProperties.getCode().getImage().getLength();i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110),20 + random.nextInt(110), 20 +random.nextInt(110)));
			 // 设置随便码在背景图图片上的位置
			g.drawString(rand, 13 * i +6, 16);
		}
		 // 释放图形上下文
		g.dispose();
		return new ImageCode(image,sRand,securityProperties.getCode().getImage().getExpireIn());
	}
	
	/**
	 * 生成随机背景条纹
	 */
	private Color getRandColor(int i, int j) {
		 Random random = new Random();
	        if (i > 255) i = 255;
	        if (j > 255) j = 255;
	        int r = i + random.nextInt(j - i);
	        int g = i + random.nextInt(j - i);
	        int b = i + random.nextInt(j - i);
	        return new Color(r, g, b);
	}

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}
	
	
}

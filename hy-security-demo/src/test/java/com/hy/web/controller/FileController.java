package com.hy.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hy.dto.FileInfo;

/**
 * 
 * @author hy
 *
 */
@RestController
@RequestMapping("/file")
public class FileController {
	
	private String folder = "D:\\";
	
	
	@PostMapping
	public FileInfo upload(MultipartFile file) throws IllegalStateException, IOException {
		
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		
		String folder = "D:\\";
		
		File localFile = new File(folder,new Date().getTime()+".txt");
	
		file.transferTo(localFile);
		
		return new FileInfo(localFile.getAbsolutePath());
	}
	
	
	@GetMapping("{id}")
	public void download(@PathVariable String id, HttpServletRequest request,HttpServletResponse response) throws IOException {
		//需在手机端
		try (InputStream inpitStream = new FileInputStream(new File(folder,id+".txt"));
			OutputStream outputStream =response.getOutputStream();){
			
			response.setContentType("application/x-msdownload");
			response.addHeader("Content-Disposition", "attachment;filename=test.txt");
			
			IOUtils.copy(inpitStream, outputStream);
			outputStream.flush();
			
		}
	}
}

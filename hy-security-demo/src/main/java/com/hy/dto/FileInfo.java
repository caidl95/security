package com.hy.dto;

/**
 * 文件的实体类
 * @author hy
 *
 */
public class FileInfo {
	
	public FileInfo() {
	}
	
	public FileInfo(String path) {
		this.path=path;
	}
	
	public String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
}

package com.hy.wiremock;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import com.github.tomakehurst.wiremock.client.WireMock;

/**
 * 配合wiremock-standalone使用 
 * 用于模拟响应前端发起的请求
 * @author hy
 *
 */
public class MockServer {
	
//	public static void main(String[] args) throws IOException {
//		WireMock.configureFor(8062);
//		WireMock.removeAllMappings();
//		
//		mock("/order/1","01");
//		
//		}
//	
//	public static void mock(String url,String name) throws IOException {
//		ClassPathResource resource = new ClassPathResource("mock/response/"+name+".txt");
//		String content = StringUtils.join(FileUtils.readLines(resource.getFile(),"UTF-8").toArray(),"\n");
//		WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo(url)).willReturn(WireMock.aResponse().withBody(content).withStatus(200)));
//	
//	}
}

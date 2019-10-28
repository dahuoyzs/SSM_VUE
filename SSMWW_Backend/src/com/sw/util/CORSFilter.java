package com.sw.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tools.ant.taskdefs.condition.Http;

public class CORSFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest rq= (HttpServletRequest) request;
		String path=rq.getRequestURL().toString();
		System.out.println(path);
		
		
		
		boolean isDebug=false;   //此处用于开发和部署时  修改         开发时写true    部署时用false 
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		httpResponse.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		httpResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT,DELETE");
		//System.out.println("过滤器执行了");
		if (isDebug)
		{
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");//这个写着也很关键，要不在response.getOutPrint时会编码错误
			chain.doFilter(request, response);
			response.setContentType("text/html;charset=utf-8");
		}else {
			chain.doFilter(request, response);
		}
	}
}

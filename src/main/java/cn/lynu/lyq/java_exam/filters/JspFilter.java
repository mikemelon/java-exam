package cn.lynu.lyq.java_exam.filters;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
, urlPatterns = { "*.jsp" })
/**
 * 禁止在地址栏中（或重定向）直接访问jsp
 * @author Administrator
 *
 */
public class JspFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String url = httpServletRequest.getRequestURI();
		if ( url != null && url.endsWith(".jsp") 
				&& url.endsWith("main.jsp")==false 
				&& url.endsWith("importData.jsp")==false 
				&& url.endsWith("questionByKnowledge.jsp")==false 
				&& url.endsWith("questionTypes.jsp")==false 
				&& url.endsWith("examComposeTypes.jsp")==false
				&& url.endsWith("statsTypes.jsp")==false
				&& url.endsWith("test.jsp")==false  //测试用
				&& url.endsWith("userChangePassword.jsp")==false
				&& url.endsWith("register.jsp") == false
				&& url.endsWith("userList.jsp") == false
			) { // 这里可以排除掉一些特殊的允许直接地址栏（或重定向）访问的jsp
			httpServletRequest.getRequestDispatcher("/main.jsp").forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}

package com.weizhe.zuul.filter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 一个前置的过滤器
 * @author Administrator
 *
 */
@Component
public class PreFilter extends ZuulFilter {

	@Override
	public Object run() throws ZuulException {
		RequestContext requestcontext=RequestContext.getCurrentContext();
		HttpServletRequest request=requestcontext.getRequest();
		System.out.println("zuul前置过滤器获取：");
		for(Cookie c :request.getCookies())
		{
		   System.out.println("cookie名称："+c.getName()+"--->>"+c.getValue());
		}
		//如果验证错误可以通过添加错误响应
		//requestcontext.setSendZuulResponse(false);
		//requestcontext.setResponseStatusCode(500);
		return null;
	}

	@Override
	public boolean shouldFilter() {
		//判断是否满足拦截条件做好都放到这里
		return true;
	}

	@Override
	public int filterOrder() {
		
		return FilterConstants.PRE_DECORATION_FILTER_ORDER-1;
	}

	@Override
	public String filterType() {
		
		return FilterConstants.PRE_TYPE;
	}

}

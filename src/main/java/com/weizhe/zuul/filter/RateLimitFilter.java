package com.weizhe.zuul.filter;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;


/**
 * 限流拦截器.采用令牌桶算法，google开源实现
 * 
 * 还有个不错的实现 https://github.com/marcosbarbero/spring-cloud-zuul-ratelimit
 * @author Administrator
 *
 */
@Component
public class RateLimitFilter extends ZuulFilter {
	
	//每秒给多少个令牌
	private static final RateLimiter RATE_LIMITER=RateLimiter.create(100);

	@Override
	public Object run() throws ZuulException {
		if(!RATE_LIMITER.tryAcquire())
		{
			//可以自定义限流错误类
			throw new RuntimeException();
		}
		return null;
	}

	@Override
	public boolean shouldFilter() {
		
		return true;
	}

	@Override
	public int filterOrder() {
		return FilterConstants.SERVLET_DETECTION_FILTER_ORDER-1;
	}

	@Override
	public String filterType() {
		
		return FilterConstants.PRE_TYPE;
	}

}

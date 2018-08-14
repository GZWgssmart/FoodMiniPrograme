package com.gzzy.foodManProgram.interceptors;

import com.gzzy.foodManProgram.service.common.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {


	@Autowired
	private TokenService tokenService;
//
	@Bean
	MyInterceptor myInterceptor() {
		return new MyInterceptor(tokenService);
	}


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(myInterceptor()).addPathPatterns("/**").excludePathPatterns("/customers/login","/customers/autopartsClientLogin","/customers/autopartsAccountLogin","/users/login","/error","/files/preview");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}


}

package com.alekaue.alekfood.core.squiggly;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.web.RequestSquigglyContextProvider;
import com.github.bohnman.squiggly.web.SquigglyRequestFilter;

@Configuration
public class SquigglyConfig {

	@Bean
	public FilterRegistrationBean<SquigglyRequestFilter> squigglyRequestFilter(ObjectMapper objectMapper) {
		Squiggly.init(objectMapper, new RequestSquigglyContextProvider("campos", null));
		
		var filterRegistartion = new FilterRegistrationBean<SquigglyRequestFilter>();
		
		filterRegistartion.setFilter(new SquigglyRequestFilter());
		filterRegistartion.setOrder(1);
		
		return filterRegistartion;
	}
}

#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.admin.web.config;

/*-
 * ${symbol_pound}%L
 * Message Admin Service
 * %%
 * Copyright (C) 2023 xfeel
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ${symbol_pound}L%
 */

import com.xfeel.support.redis.count.RedisCounterAdapter;
import com.xfeel.support.web.event.ThrowableEventPublisher;
import com.xfeel.support.web.event.TooManyErrorEventPublisher;
import com.xfeel.support.web.filter.AccessFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public RedisCounterAdapter redisCounterAdapter(RedisTemplate<String,Long> redisTemplate){
		return new RedisCounterAdapter(redisTemplate);
	}

	@Bean
	public ThrowableEventPublisher throwableEventPublisher(){
		return new ThrowableEventPublisher();
	}

	@Bean
	public TooManyErrorEventPublisher tooManyErrorEventPublisher(){
		return new TooManyErrorEventPublisher();
	}

	@Bean
	public FilterRegistrationBean<AccessFilter> accessFilter(RedisCounterAdapter redisCounterAdapter, ThrowableEventPublisher throwableEventPublisher, TooManyErrorEventPublisher tooManyErrorEventPublisher){
		FilterRegistrationBean<AccessFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new AccessFilter(redisCounterAdapter,throwableEventPublisher,tooManyErrorEventPublisher));
		registrationBean.addUrlPatterns("/*");
		registrationBean.setName("accessFilter");
		registrationBean.setOrder(1);
		return registrationBean;
	}

}

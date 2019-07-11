package com.bizleap.internhr.application.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.thymeleaf.messageresolver.StandardMessageResolver;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//import com.bizleap.thymeleaf.dialet.EmployeePermission;
//import com.bizleap.thymeleaf.dialet.MyDialet;

@Configuration
@Import({ CoreConfig.class })
@ComponentScan(basePackages = { "com.bizleap"})
public class WebConfig extends WebMvcConfigurationSupport {
	private ThymeleafViewResolver viewResolver;

	@Value("${tenant.id}")
	private String prefix;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("*")
		.allowedMethods("*")
		.allowCredentials(false)
		.maxAge(3600);
		//registry.addMapping("/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		registry.addInterceptor(localeChangeInterceptor);
	}	

	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(StringUtils.parseLocaleString("en"));
		return cookieLocaleResolver;
	}

	@Bean
	public ServletContextTemplateResolver templateResolver() {
		ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
		resolver.setPrefix("/WEB-INF/pages/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode("HTML5");
		resolver.setCharacterEncoding("UTF-8");
		resolver.setCacheable(false);
		return resolver;
	}

	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver());
		//engine.addDialect(new MyDialet());
		//engine.addDialect(new EmployeePermission());
//		PropertiesLoader loader = new PropertiesLoader("attribute.properties");
//		loader.merge(new PropertiesLoader("default.properties"));
//		loader.merge(new PropertiesLoader(prefix + "/" + prefix + ".properties"));
		StandardMessageResolver messageResolver = new StandardMessageResolver();
//		messageResolver.setDefaultMessages(loader.getPerperties());
		engine.addMessageResolver(messageResolver);
		return engine;
	}

	/*
	 * ViewResolver view;
	 * 
	 * public ViewResolver getView() { return view; }
	 */

	@Bean
	public MultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}

	@Bean
	public ViewResolver viewResolver() {
		if (viewResolver == null) {
			viewResolver = new ThymeleafViewResolver();
			viewResolver.setTemplateEngine(templateEngine());
			viewResolver.setOrder(1);
			viewResolver.setViewNames(new String[] { "*" });
			viewResolver.setCache(false);
			viewResolver.setCharacterEncoding("UTF-8");
			viewResolver.setContentType("text/html;charset=utf-8");
		}
		return viewResolver;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:messages/messages", "classpath:messages/validation");
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(0);
		return messageSource;
	}
	
//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		converters.add(converter());
//		addDefaultHttpMessageConverters(converters);
//	}	
	
	@Bean(name = "converter")
	MappingJackson2HttpMessageConverter converter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		//do your customizations here...
		return converter;
	}	
	

	@Bean
	 public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
	  MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
	  ObjectMapper objectMapper = new ObjectMapper();
	  objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	  //objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);	  
	  objectMapper.configure(MapperFeature.AUTO_DETECT_GETTERS, true);
	  objectMapper.configure(MapperFeature.AUTO_DETECT_FIELDS, true);
	  jsonConverter.setObjectMapper(objectMapper);
	  return jsonConverter;
	 }
	 
	 @Override
	 public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	  converters.add(customJackson2HttpMessageConverter());
	  super.addDefaultHttpMessageConverters( converters );
	 }
	 
}
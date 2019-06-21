package com.bizleap.hr.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import( {AppConfig.class })
@ComponentScan(basePackages= {
		"com.bizleap.commons",
		"com.bizleap.hr",
		"com.bizleap.service"
})
public class CoreConfig {

}

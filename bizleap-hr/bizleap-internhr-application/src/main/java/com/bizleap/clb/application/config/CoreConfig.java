package com.bizleap.clb.application.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ AppConfig.class })
@ComponentScan(basePackages = { "com.bizleap.clb.messaging.service",
		"com.bizleap.clb.team.domain",
		"com.bizleap.security.domain",
		"com.bizleap.commons"})
public class CoreConfig {

}
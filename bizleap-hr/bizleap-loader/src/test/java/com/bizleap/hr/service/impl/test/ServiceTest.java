package com.bizleap.hr.service.impl.test;

import com.bizleap.hr.config.CoreConfig;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContext.xml","classpath:/hibernateContext.xml"})
@PropertySource({"classpath:log4j.properties", "classpath:/application.properties"})
public class ServiceTest {

}

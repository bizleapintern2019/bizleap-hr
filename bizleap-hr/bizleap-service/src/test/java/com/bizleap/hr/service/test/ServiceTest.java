package com.bizleap.hr.service.test;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

<<<<<<< HEAD
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContext.xml","classpath:/hibernateContext.xml"})
@PropertySource({"classpath:log4j.properties", "classpath:/application.properties"})
public class ServiceTest {

}
=======

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=CoreConfig.class)
@ContextConfiguration(locations= {"classpath:/applicationContext.xml","classpath:/hibernateContext.xml"})
@PropertySource({"classpath:log4j.properties"})
public class ServiceTest {

}
>>>>>>> 44d4dcd63ddc1cef598d0f3b27303c890c0204f9

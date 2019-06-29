package com.bizleap.hr.loader;

import org.junit.Test;

import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Position;

public class EmployeeTest {
//	@Test
//	public void parseEmployeeTest() {
//		Employee.parseEmployee("EMP002;U;Nyan;Shein;JOB004-1;07-05-2015;10-07-1962;Male;konyanshein@gmail.com;09792155929;ADR002");
//	}
	
	@Test
	public void parsePositionTest() {
		Position.parsePosition("JOB002-1;JOB002;JOB001-1,JOB004-1");
	}
}

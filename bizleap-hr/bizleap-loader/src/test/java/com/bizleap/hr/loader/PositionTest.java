package com.bizleap.hr.loader;

import org.junit.Test;

import com.bizleap.commons.domain.entity.Position;

public class PositionTest {

	@Test
	public void parsePositionTest() {
		Position.parsePosition("JOB002-1;JOB002;JOB001-1,JOB004-1");
	}
}

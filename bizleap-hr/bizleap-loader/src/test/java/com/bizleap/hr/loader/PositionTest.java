package com.bizleap.hr.loader;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.bizleap.commons.domain.entity.Position;
import com.bizleap.hr.loader.impl.test.ServiceTest;
import java.util.List;
import org.junit.Assert;

public class PositionTest extends ServiceTest {

	@Autowired
	private DataLoader dataLoader;
	
	@Test
	public void testParsePosition() throws Exception {
		testPositionList(dataLoader.loadPosition());
	}
	
	public int assertPosition(Position position,String boId,String jobId,String reportTo) {
		if(position.getBoId().equals(boId)) {
			Assert.assertEquals(position.getJob().getBoId(), jobId);
			String[] reportToItems = reportTo.split(",");
			for(int i=0;i<reportToItems.length;i++){
				Assert.assertEquals(position.getReportToList().get(i).getBoId(), reportToItems[i]);
			}
			return 1;
		}
		return 0;
	}
	
	public void testPositionList(List<Position> positionList) throws Exception {
		Assert.assertTrue(positionList != null && positionList.size() == 24);
		int successCount=0;
		for(Position position :positionList) {
			successCount += assertPosition(position,"JOB001-1","JOB001"," ");
			successCount += assertPosition(position,"JOB002-1","JOB002","JOB001-1,JOB004-1");
			successCount += assertPosition(position,"JOB002-2","JOB002","JOB001-1,JOB004-1,JOB005-1");
			successCount += assertPosition(position,"JOB002-3","JOB002","JOB001-1,JOB004-1,JOB005-1");
			successCount += assertPosition(position,"JOB002-4","JOB002","JOB001-1,JOB004-1");
			successCount += assertPosition(position,"JOB003-1","JOB003","JOB001-1,JOB004-1,JOB005-1,JOB002-2,JOB002-3");
			successCount += assertPosition(position,"JOB003-2","JOB003","JOB001-1,JOB004-1,JOB005-1,JOB002-2,JOB002-3,JOB002-4");
			successCount += assertPosition(position,"JOB003-3","JOB003","JOB001-1,JOB004-1,JOB005-1,JOB002-2,JOB002-3");
			successCount += assertPosition(position,"JOB003-4","JOB003","JOB001-1,JOB004-1,JOB002-1");
			successCount += assertPosition(position,"JOB003-5","JOB003","JOB001-1,JOB004-1,JOB002-1");
			successCount += assertPosition(position,"JOB004-1","JOB004","JOB001-1");
			successCount += assertPosition(position,"JOB005-1","JOB005","JOB001-1,JOB004-1");
			successCount += assertPosition(position,"JOB006-1","JOB006","JOB002-3,JOB002-4");
			successCount += assertPosition(position,"JOB006-2","JOB006","JOB002-3,JOB002-4,JOB003-3,JOB003-5");
			successCount += assertPosition(position,"JOB006-3","JOB006","JOB002-3,JOB002-4,JOB003-3");
			successCount += assertPosition(position,"JOB006-4","JOB006","JOB002-3,JOB002-4");
			successCount += assertPosition(position,"JOB006-5","JOB006","JOB002-3,JOB002-4");
			successCount += assertPosition(position,"JOB006-6","JOB006","JOB002-3,JOB002-4,JOB002-2,JOB003-2");
			successCount += assertPosition(position,"JOB006-7","JOB006","JOB002-3,JOB002-4");
			successCount += assertPosition(position,"JOB006-8","JOB006","JOB002-3,JOB002-4");
			successCount += assertPosition(position,"JOB006-9","JOB006","JOB002-3,JOB002-4,JOB003-1,JOB003-4");
			successCount += assertPosition(position,"JOB006-10","JOB006","JOB002-3,JOB002-4,JOB003-2,JOB003-4");
			successCount += assertPosition(position,"JOB006-11","JOB006","JOB002-3,JOB002-4,JOB003-3,JOB003-5");
			successCount += assertPosition(position,"JOB006-12","JOB006","JOB002-3,JOB002-4");
			Assert.assertTrue(successCount==1);
			successCount=0;
		}
	}
}

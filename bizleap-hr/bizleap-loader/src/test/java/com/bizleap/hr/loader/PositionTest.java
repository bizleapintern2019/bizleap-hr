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

	private List<Position> positionList;

	@Test
	public void parsePositionTest() throws Exception {
		positionList = dataLoader.loadPosition();
		Assert.assertTrue(positionList != null && positionList.size() == 24);

		for (Position position : positionList) {
			switch (position.getBoId()) {
			case "JOB001-1":
				Assert.assertEquals(position.getJob().getBoId(), "JOB001");
				Assert.assertEquals(position.getReportToList().get(0).getBoId(), " ");
				break;
			case "JOB002-1":
				Assert.assertEquals(position.getJob().getBoId(), "JOB002");
				Assert.assertEquals(position.getReportToList().get(0).getBoId(), "JOB001-1");
				Assert.assertEquals(position.getReportToList().get(1).getBoId(), "JOB004-1");
				break;
			case "JOB002-2":
				Assert.assertEquals(position.getJob().getBoId(), "JOB002");
				Assert.assertEquals(position.getReportToList().get(0).getBoId(), "JOB001-1");
				Assert.assertEquals(position.getReportToList().get(1).getBoId(), "JOB004-1");
				Assert.assertEquals(position.getReportToList().get(2).getBoId(), "JOB005-1");
				break;
			case "JOB002-3":
				Assert.assertEquals(position.getJob().getBoId(), "JOB002");
				Assert.assertEquals(position.getReportToList().get(0).getBoId(), "JOB001-1");
				Assert.assertEquals(position.getReportToList().get(1).getBoId(), "JOB004-1");
				Assert.assertEquals(position.getReportToList().get(2).getBoId(), "JOB005-1");
				break;
			case "JOB002-4":
				Assert.assertEquals(position.getJob().getBoId(), "JOB002");
				Assert.assertEquals(position.getReportToList().get(0).getBoId(), "JOB001-1");
				Assert.assertEquals(position.getReportToList().get(1).getBoId(), "JOB004-1");
				break;
			case "JOB003-1":
				Assert.assertEquals(position.getJob().getBoId(), "JOB003");
				Assert.assertEquals(position.getReportToList().get(0).getBoId(), "JOB001-1");
				Assert.assertEquals(position.getReportToList().get(1).getBoId(), "JOB004-1");
				Assert.assertEquals(position.getReportToList().get(2).getBoId(), "JOB005-1");
				Assert.assertEquals(position.getReportToList().get(3).getBoId(), "JOB002-2");
				Assert.assertEquals(position.getReportToList().get(4).getBoId(), "JOB002-3");
				break;
			case "JOB003-2":
				Assert.assertEquals(position.getJob().getBoId(), "JOB003");
				Assert.assertEquals(position.getReportToList().get(0).getBoId(), "JOB001-1");
				Assert.assertEquals(position.getReportToList().get(1).getBoId(), "JOB004-1");
				Assert.assertEquals(position.getReportToList().get(2).getBoId(), "JOB005-1");
				Assert.assertEquals(position.getReportToList().get(3).getBoId(), "JOB002-2");
				Assert.assertEquals(position.getReportToList().get(4).getBoId(), "JOB002-3");
				Assert.assertEquals(position.getReportToList().get(5).getBoId(), "JOB002-4");
				break;
			case "JOB003-3":
				Assert.assertEquals(position.getJob().getBoId(), "JOB003");
				Assert.assertEquals(position.getReportToList().get(0).getBoId(), "JOB001-1");
				Assert.assertEquals(position.getReportToList().get(1).getBoId(), "JOB004-1");
				Assert.assertEquals(position.getReportToList().get(2).getBoId(), "JOB005-1");
				Assert.assertEquals(position.getReportToList().get(3).getBoId(), "JOB002-2");
				Assert.assertEquals(position.getReportToList().get(4).getBoId(), "JOB002-3");
				break;
			case "JOB003-4":
				Assert.assertEquals(position.getJob().getBoId(), "JOB003");
				Assert.assertEquals(position.getReportToList().get(0).getBoId(), "JOB001-1");
				Assert.assertEquals(position.getReportToList().get(1).getBoId(), "JOB004-1");
				Assert.assertEquals(position.getReportToList().get(2).getBoId(), "JOB002-1");
				break;
			case "JOB003-5":
				Assert.assertEquals(position.getJob().getBoId(), "JOB003");
				Assert.assertEquals(position.getReportToList().get(0).getBoId(), "JOB001-1");
				Assert.assertEquals(position.getReportToList().get(1).getBoId(), "JOB004-1");
				Assert.assertEquals(position.getReportToList().get(2).getBoId(), "JOB002-1");
				break;
			case "JOB004-1":
				Assert.assertEquals(position.getJob().getBoId(), "JOB004");
				Assert.assertEquals(position.getReportToList().get(0).getBoId(), "JOB001-1");
				break;
			case "JOB005-1":
				Assert.assertEquals(position.getJob().getBoId(), "JOB005");
				Assert.assertEquals(position.getReportToList().get(0).getBoId(), "JOB001-1");
				Assert.assertEquals(position.getReportToList().get(1).getBoId(), "JOB004-1");
				break;
			case "JOB006-1":
				Assert.assertEquals(position.getJob().getBoId(), "JOB006");
				Assert.assertEquals(position.getReportToList().get(0).getBoId(), "JOB002-3");
				Assert.assertEquals(position.getReportToList().get(1).getBoId(), "JOB002-4");
				break;
			case "JOB006-2":
				Assert.assertEquals(position.getJob().getBoId(), "JOB006");
				Assert.assertEquals(position.getReportToList().get(0).getBoId(), "JOB002-3");
				Assert.assertEquals(position.getReportToList().get(1).getBoId(), "JOB002-4");
				Assert.assertEquals(position.getReportToList().get(2).getBoId(), "JOB003-3");
				Assert.assertEquals(position.getReportToList().get(3).getBoId(), "JOB003-5");
				break;
			case "JOB006-3":
				Assert.assertEquals(position.getJob().getBoId(), "JOB006");
				Assert.assertEquals(position.getReportToList().get(0).getBoId(), "JOB002-3");
				Assert.assertEquals(position.getReportToList().get(1).getBoId(), "JOB002-4");
				Assert.assertEquals(position.getReportToList().get(2).getBoId(), "JOB003-3");
				break;
			case "JOB006-4":
				Assert.assertEquals(position.getJob().getBoId(), "JOB006");
				Assert.assertEquals(position.getReportToList().get(0).getBoId(), "JOB002-3");
				Assert.assertEquals(position.getReportToList().get(1).getBoId(), "JOB002-4");
				break;
			case "JOB006-5":
				Assert.assertEquals(position.getJob().getBoId(), "JOB006");
				Assert.assertEquals(position.getReportToList().get(0).getBoId(), "JOB002-3");
				Assert.assertEquals(position.getReportToList().get(1).getBoId(), "JOB002-4");
				break;
			case "JOB006-6":
				Assert.assertEquals(position.getJob().getBoId(), "JOB006");
				Assert.assertEquals(position.getReportToList().get(0).getBoId(), "JOB002-3");
				Assert.assertEquals(position.getReportToList().get(1).getBoId(), "JOB002-4");
				Assert.assertEquals(position.getReportToList().get(2).getBoId(), "JOB002-2");
				Assert.assertEquals(position.getReportToList().get(3).getBoId(), "JOB003-2");
				break;
			case "JOB006-7":
				Assert.assertEquals(position.getJob().getBoId(), "JOB006");
				Assert.assertEquals(position.getReportToList().get(0).getBoId(), "JOB002-3");
				Assert.assertEquals(position.getReportToList().get(1).getBoId(), "JOB002-4");
				break;
			case "JOB006-8":
				Assert.assertEquals(position.getJob().getBoId(), "JOB006");
				Assert.assertEquals(position.getReportToList().get(0).getBoId(), "JOB002-3");
				Assert.assertEquals(position.getReportToList().get(1).getBoId(), "JOB002-4");
				break;
			case "JOB006-9":
				Assert.assertEquals(position.getJob().getBoId(), "JOB006");
				Assert.assertEquals(position.getReportToList().get(0).getBoId(), "JOB002-3");
				Assert.assertEquals(position.getReportToList().get(1).getBoId(), "JOB002-4");
				Assert.assertEquals(position.getReportToList().get(2).getBoId(), "JOB003-1");
				Assert.assertEquals(position.getReportToList().get(3).getBoId(), "JOB003-4");
				break;
			case "JOB006-10":
				Assert.assertEquals(position.getJob().getBoId(), "JOB006");
				Assert.assertEquals(position.getReportToList().get(0).getBoId(), "JOB002-3");
				Assert.assertEquals(position.getReportToList().get(1).getBoId(), "JOB002-4");
				Assert.assertEquals(position.getReportToList().get(2).getBoId(), "JOB003-2");
				Assert.assertEquals(position.getReportToList().get(3).getBoId(), "JOB003-4");
				break;
			case "JOB006-11":
				Assert.assertEquals(position.getJob().getBoId(), "JOB006");
				Assert.assertEquals(position.getReportToList().get(0).getBoId(), "JOB002-3");
				Assert.assertEquals(position.getReportToList().get(1).getBoId(), "JOB002-4");
				Assert.assertEquals(position.getReportToList().get(2).getBoId(), "JOB003-3");
				Assert.assertEquals(position.getReportToList().get(3).getBoId(), "JOB003-5");
				break;
			case "JOB006-12":
				Assert.assertEquals(position.getJob().getBoId(), "JOB006");
				Assert.assertEquals(position.getReportToList().get(0).getBoId(), "JOB002-3");
				Assert.assertEquals(position.getReportToList().get(1).getBoId(), "JOB002-4");
				break;
			default:
				Assert.assertTrue(false);
			}
		}

	}
}

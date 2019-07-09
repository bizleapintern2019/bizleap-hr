package com.bizleap.hr.loader;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.junit.Assert;
import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.hr.loader.impl.test.ServiceTest;

public class EmployeeTest extends ServiceTest {
	
		@Autowired
		private DataLoader dataLoader;

		@Test
		public void testParseEmployee() throws Exception{
			testEmployeeList(dataLoader.loadEmployee());
		}
		
		public int assertEmployee(Employee employee,String boId,String title,String firstName,String lastName,String positionId,String entranceDate,String dateOfBirth,String gender,String email,String phoneNumber,String addressId){
			if(employee.getBoId().equals(boId)) {
				Assert.assertEquals(employee.getTitle(),title);
				Assert.assertEquals(employee.getFirstName(),firstName);
				Assert.assertEquals(employee.getLastName(),lastName);
				Assert.assertEquals(employee.getPosition().getBoId(),positionId);
				Assert.assertEquals(employee.getEntranceDate(),entranceDate);
				Assert.assertEquals(employee.getDateOfBirth(),dateOfBirth);
				Assert.assertEquals(employee.getGender(),gender);
				Assert.assertEquals(employee.getEmail(),email);
				Assert.assertEquals(employee.getPhone(),phoneNumber);
				Assert.assertEquals(employee.getAddress().getBoId(),addressId);
				return 1;
			}
			return 0;
		}

		public void testEmployeeList(List<Employee> employeeList) throws Exception {
			
			Assert.assertTrue(employeeList != null && employeeList.size() == 24);
			int successCount = 0;
			for (Employee employee : employeeList) {
				successCount+= assertEmployee(employee,"EMP001","U","Nyunt","Than","JOB001-1","18-01-2014","07-07-1962","Male","nyuntthan@gmail.com","01511522","ADR001");
				successCount+= assertEmployee(employee,"EMP002","U","Nyan","Shein","JOB004-1","07-05-2015","10-07-1962","Male","konyanshein@gmail.com","09792155929","ADR002");
				successCount+= assertEmployee(employee,"EMP003","Ko","Soe","Min Htike","JOB005-1","18-03-2014","19-03-1990","Male","soeminhtike98@gmail.com","09967912511","ADR003");
				successCount+= assertEmployee(employee,"EMP004","Ko","So","Pyai Aung","JOB002-1","02-09-2014","09-07-1991","Male","usopyaiaung@gmail.com","09774847500","ADR004");
				successCount+= assertEmployee(employee,"EMP005","Ko","Ye","Yint Thu","JOB002-2","18-01-2014","29-04-1992","Male","yeyintthu.tcu@gmail.com","099759723356","ADR005");
				successCount+= assertEmployee(employee,"EMP006","Ma","Phyu","Mon Thant","JOB002-3","17-05-2014","12-11-1992","Female","phyumonthant.reason@gmail.com","09795253837","ADR006");
				successCount+= assertEmployee(employee,"EMP007","Ko","Kaung","San Kyaw","JOB002-4","01-07-2016","19-08-1993","Male","kskthedeveloper@gmail.com","0943301888","ADR007");
				successCount+= assertEmployee(employee,"EMP008","Ma","Htet","Htet San","JOB003-1","01-09-2018","05-07-1997","Female","htethtetsan57@gmail.com","0968919459","ADR008");
				successCount+= assertEmployee(employee,"EMP009","Ma","Saung","Hnin Phyu","JOB003-2","01-09-2018","21-02-1997","Female","miseint1997@gmail.com","09965722802","ADR009");
				successCount+= assertEmployee(employee,"EMP010","Ma","Hnin","Ei Hlaing","JOB003-3","01-09-2018","05-06-1997","Female","hnineihlaing130@gmail.com","09450004373","ADR010");
				successCount+= assertEmployee(employee,"EMP011","Ko","Ye","Min Ko","JOB003-4","01-09-2018","16-10-1996","Male","yeminko47@gmail.com","09798232751","ADR011");
				successCount+= assertEmployee(employee,"EMP012","Ma","Khaing","Su Thiri","JOB003-5","01-09-2018","17-07-1997","Female","khaingsuthiri@gmail.com","09798232782","ADR012");
				successCount+= assertEmployee(employee,"EMP013","Mg","Shine","Wanna","JOB006-7","02-05-2019","07-08-1998","Male","shinewanna07@gmail.com","09693543788","ADR013");
				successCount+= assertEmployee(employee,"EMP014","Mg","Nyan","Lin Htet","JOB006-6","02-05-2019","03-07-1998","Male","nyanlinhtet379@gmail.com","09693577148","ADR014");
				successCount+= assertEmployee(employee,"EMP015","Mg","Thu","Ya Oo","JOB006-5","02-05-2019","30-10-1997","Male","446thuya446@gmail.com","09795552585","ADR015");
				successCount+= assertEmployee(employee,"EMP016","Mg","Kaung","Pyae Sone Tun","JOB006-4","02-05-2019","27-04-1998","Male","harbe27@gmail.com","09698917797","ADR016");
				successCount+= assertEmployee(employee,"EMP017","Mg","Soe","Min Thein","JOB006-3","02-05-2019","02-06-1998","Male","sminthein0@gmail.com","09966550548","ADR017");
				successCount+= assertEmployee(employee,"EMP018","Ma","Ya","Mone Zin","JOB006-2","02-05-2019","30-05-1998","Female","yamonezin@ucsy.edu.mm","09766974698","ADR018");
				successCount+= assertEmployee(employee,"EMP019","Ma","Kay","Zin Han","JOB006-1","02-05-2019","25-12-1997","Female","kzh112608@gmail.com","09250508348","ADR019");
				successCount+= assertEmployee(employee,"EMP020","Ma","Su","Pyae Naing","JOB006-12","02-05-2019","02-09-1997","Female","supyaenaing1998@gmail.com","09250069832","ADR020");
				successCount+= assertEmployee(employee,"EMP021","Ma","Saw","Sandi Tin","JOB006-11","02-05-2019","10-11-1997","Female","sawsanditin@ucsy.edu.mm","09779755036","ADR021");
				successCount+= assertEmployee(employee,"EMP022","Ma","San","Thinzar Linn","JOB006-10","02-05-2019","13-10-1997","Female","santhinzarlinn1997@gmail.com","09795324758","ADR022");
				successCount+= assertEmployee(employee,"EMP023","Ma","Khin","Chanmyae Thu","JOB006-9","02-05-2019","07-01-1998","Female","khinchanmyae711998thu@gmail.com","09693934636","ADR023");
				successCount+= assertEmployee(employee,"EMP024","Mg","Thi","Han Hein","JOB006-8","02-05-2019","02-06-2000","Male","micaljohn60@gmail.com","09775338983","ADR024");

			}
			Assert.assertTrue(successCount==24);
		}
	}
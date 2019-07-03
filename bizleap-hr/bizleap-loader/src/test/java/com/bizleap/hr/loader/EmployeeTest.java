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

	private List<Employee> employeeList;

	@Test
	public void parseEmployeeTest() throws Exception {
		employeeList = dataLoader.loadEmployee();
		Assert.assertTrue(employeeList != null && employeeList.size() == 24);

		for (Employee employee : employeeList) {
			switch (employee.getBoId()) {
			case "EMP001":
				Assert.assertEquals(employee.getTitle(), "U");
				Assert.assertEquals(employee.getFirstName(), "Nyunt");
				Assert.assertEquals(employee.getLastName(), "Than");
				Assert.assertEquals(employee.getPosition().getBoId(), "JOB001-1");
				Assert.assertEquals(employee.getEntranceDate(), "18-01-2014");
				Assert.assertEquals(employee.getDateOfBirth(), "07-07-1962");
				Assert.assertEquals(employee.getGender(), "Male");
				Assert.assertEquals(employee.getEmail(), "nyuntthan@gmail.com");
				Assert.assertEquals(employee.getPhone(), "01511522");
				Assert.assertEquals(employee.getAddress().getBoId(), "ADR001");
				break;

			case "EMP002":
				Assert.assertEquals(employee.getTitle(), "U");
				Assert.assertEquals(employee.getFirstName(), "Nyan");
				Assert.assertEquals(employee.getLastName(), "Shein");
				Assert.assertEquals(employee.getPosition().getBoId(), "JOB004-1");
				Assert.assertEquals(employee.getEntranceDate(), "07-05-2015");
				Assert.assertEquals(employee.getDateOfBirth(), "10-07-1962");
				Assert.assertEquals(employee.getGender(), "Male");
				Assert.assertEquals(employee.getEmail(), "konyanshein@gmail.com");
				Assert.assertEquals(employee.getPhone(), "09792155929");
				Assert.assertEquals(employee.getAddress().getBoId(), "ADR002");
				break;

			case "EMP003":
				Assert.assertEquals(employee.getTitle(), "Ko");
				Assert.assertEquals(employee.getFirstName(), "Soe");
				Assert.assertEquals(employee.getLastName(), "Min Htike");
				Assert.assertEquals(employee.getPosition().getBoId(), "JOB005-1");
				Assert.assertEquals(employee.getEntranceDate(), "18-03-2014");
				Assert.assertEquals(employee.getDateOfBirth(), "19-03-1990");
				Assert.assertEquals(employee.getGender(), "Male");
				Assert.assertEquals(employee.getEmail(), "soeminhtike98@gmail.com");
				Assert.assertEquals(employee.getPhone(), "09967912511");
				Assert.assertEquals(employee.getAddress().getBoId(), "ADR003");
				break;

			case "EMP004":
				Assert.assertEquals(employee.getTitle(), "Ko");
				Assert.assertEquals(employee.getFirstName(), "So");
				Assert.assertEquals(employee.getLastName(), "Pyai Aung");
				Assert.assertEquals(employee.getPosition().getBoId(), "JOB002-1");
				Assert.assertEquals(employee.getEntranceDate(), "02-09-2014");
				Assert.assertEquals(employee.getDateOfBirth(), "09-07-1991");
				Assert.assertEquals(employee.getGender(), "Male");
				Assert.assertEquals(employee.getEmail(), "usopyaiaung@gmail.com");
				Assert.assertEquals(employee.getPhone(), "09774847500");
				Assert.assertEquals(employee.getAddress().getBoId(), "ADR004");
				break;

			case "EMP005":
				Assert.assertEquals(employee.getTitle(), "Ko");
				Assert.assertEquals(employee.getFirstName(), "Ye");
				Assert.assertEquals(employee.getLastName(), "Yint Thu");
				Assert.assertEquals(employee.getPosition().getBoId(), "JOB002-2");
				Assert.assertEquals(employee.getEntranceDate(), "18-01-2014");
				Assert.assertEquals(employee.getDateOfBirth(), "29-04-1992");
				Assert.assertEquals(employee.getGender(), "Male");
				Assert.assertEquals(employee.getEmail(), "yeyintthu.tcu@gmail.com");
				Assert.assertEquals(employee.getPhone(), "099759723356");
				Assert.assertEquals(employee.getAddress().getBoId(), "ADR005");
				break;

			case "EMP006":
				Assert.assertEquals(employee.getTitle(), "Ma");
				Assert.assertEquals(employee.getFirstName(), "Phyu");
				Assert.assertEquals(employee.getLastName(), "Mon Thant");
				Assert.assertEquals(employee.getPosition().getBoId(), "JOB002-3");
				Assert.assertEquals(employee.getEntranceDate(), "17-05-2014");
				Assert.assertEquals(employee.getDateOfBirth(), "12-11-1992");
				Assert.assertEquals(employee.getGender(), "Female");
				Assert.assertEquals(employee.getEmail(), "phyumonthant.reason@gmail.com");
				Assert.assertEquals(employee.getPhone(), "09795253837");
				Assert.assertEquals(employee.getAddress().getBoId(), "ADR006");
				break;

			case "EMP007":
				Assert.assertEquals(employee.getTitle(), "Ko");
				Assert.assertEquals(employee.getFirstName(), "Kaung");
				Assert.assertEquals(employee.getLastName(), "San Kyaw");
				Assert.assertEquals(employee.getPosition().getBoId(), "JOB002-4");
				Assert.assertEquals(employee.getEntranceDate(), "01-07-2016");
				Assert.assertEquals(employee.getDateOfBirth(), "19-08-1993");
				Assert.assertEquals(employee.getGender(), "Male");
				Assert.assertEquals(employee.getEmail(), "kskthedeveloper@gmail.com");
				Assert.assertEquals(employee.getPhone(), "0943301888");
				Assert.assertEquals(employee.getAddress().getBoId(), "ADR007");
				break;

			case "EMP008":
				Assert.assertEquals(employee.getTitle(), "Ma");
				Assert.assertEquals(employee.getFirstName(), "Htet");
				Assert.assertEquals(employee.getLastName(), "Htet San");
				Assert.assertEquals(employee.getPosition().getBoId(), "JOB003-1");
				Assert.assertEquals(employee.getEntranceDate(), "01-09-2018");
				Assert.assertEquals(employee.getDateOfBirth(), "05-07-1997");
				Assert.assertEquals(employee.getGender(), "Female");
				Assert.assertEquals(employee.getEmail(), "htethtetsan57@gmail.com");
				Assert.assertEquals(employee.getPhone(), "0968919459");
				Assert.assertEquals(employee.getAddress().getBoId(), "ADR008");
				break;

			case "EMP009":
				Assert.assertEquals(employee.getTitle(), "Ma");
				Assert.assertEquals(employee.getFirstName(), "Saung");
				Assert.assertEquals(employee.getLastName(), "Hnin Phyu");
				Assert.assertEquals(employee.getPosition().getBoId(), "JOB003-2");
				Assert.assertEquals(employee.getEntranceDate(), "01-09-2018");
				Assert.assertEquals(employee.getDateOfBirth(), "21-02-1997");
				Assert.assertEquals(employee.getGender(), "Female");
				Assert.assertEquals(employee.getEmail(), "miseint1997@gmail.com");
				Assert.assertEquals(employee.getPhone(), "09965722802");
				Assert.assertEquals(employee.getAddress().getBoId(), "ADR009");
				break;

			case "EMP010":
				Assert.assertEquals(employee.getTitle(), "Ma");
				Assert.assertEquals(employee.getFirstName(), "Hnin");
				Assert.assertEquals(employee.getLastName(), "Ei Hlaing");
				Assert.assertEquals(employee.getPosition().getBoId(), "JOB003-3");
				Assert.assertEquals(employee.getEntranceDate(), "01-09-2018");
				Assert.assertEquals(employee.getDateOfBirth(), "05-06-1997");
				Assert.assertEquals(employee.getGender(), "Female");
				Assert.assertEquals(employee.getEmail(), "hnineihlaing130@gmail.com");
				Assert.assertEquals(employee.getPhone(), "09450004373");
				Assert.assertEquals(employee.getAddress().getBoId(), "ADR010");
				break;

			case "EMP011":
				Assert.assertEquals(employee.getTitle(), "Ko");
				Assert.assertEquals(employee.getFirstName(), "Ye");
				Assert.assertEquals(employee.getLastName(), "Min Ko");
				Assert.assertEquals(employee.getPosition().getBoId(), "JOB003-4");
				Assert.assertEquals(employee.getEntranceDate(), "01-09-2018");
				Assert.assertEquals(employee.getDateOfBirth(), "16-10-1996");
				Assert.assertEquals(employee.getGender(), "Male");
				Assert.assertEquals(employee.getEmail(), "yeminko47@gmail.com");
				Assert.assertEquals(employee.getPhone(), "09798232751");
				Assert.assertEquals(employee.getAddress().getBoId(), "ADR011");
				break;

			case "EMP012":
				Assert.assertEquals(employee.getTitle(), "Ma");
				Assert.assertEquals(employee.getFirstName(), "Khaing");
				Assert.assertEquals(employee.getLastName(), "Su Thiri");
				Assert.assertEquals(employee.getPosition().getBoId(), "JOB003-5");
				Assert.assertEquals(employee.getEntranceDate(), "01-09-2018");
				Assert.assertEquals(employee.getDateOfBirth(), "17-07-1997");
				Assert.assertEquals(employee.getGender(), "Female");
				Assert.assertEquals(employee.getEmail(), "khaingsuthiri@gmail.com");
				Assert.assertEquals(employee.getPhone(), "09798232782");
				Assert.assertEquals(employee.getAddress().getBoId(), "ADR012");
				break;

			case "EMP013":
				Assert.assertEquals(employee.getTitle(), "Mg");
				Assert.assertEquals(employee.getFirstName(), "Shine");
				Assert.assertEquals(employee.getLastName(), "Wanna");
				Assert.assertEquals(employee.getPosition().getBoId(), "JOB006-7");
				Assert.assertEquals(employee.getEntranceDate(), "02-05-2019");
				Assert.assertEquals(employee.getDateOfBirth(), "07-08-1998");
				Assert.assertEquals(employee.getGender(), "Male");
				Assert.assertEquals(employee.getEmail(), "shinewanna07@gmail.com");
				Assert.assertEquals(employee.getPhone(), "09693543788");
				Assert.assertEquals(employee.getAddress().getBoId(), "ADR013");
				break;

			case "EMP014":
				Assert.assertEquals(employee.getTitle(), "Mg");
				Assert.assertEquals(employee.getFirstName(), "Nyan");
				Assert.assertEquals(employee.getLastName(), "Lin Htet");
				Assert.assertEquals(employee.getPosition().getBoId(), "JOB006-6");
				Assert.assertEquals(employee.getEntranceDate(), "02-05-2019");
				Assert.assertEquals(employee.getDateOfBirth(), "03-07-1998");
				Assert.assertEquals(employee.getGender(), "Male");
				Assert.assertEquals(employee.getEmail(), "nyanlinhtet379@gmail.com");
				Assert.assertEquals(employee.getPhone(), "09693577148");
				Assert.assertEquals(employee.getAddress().getBoId(), "ADR014");
				break;

			case "EMP015":
				Assert.assertEquals(employee.getTitle(), "Mg");
				Assert.assertEquals(employee.getFirstName(), "Thu");
				Assert.assertEquals(employee.getLastName(), "Ya Oo");
				Assert.assertEquals(employee.getPosition().getBoId(), "JOB006-5");
				Assert.assertEquals(employee.getEntranceDate(), "02-05-2019");
				Assert.assertEquals(employee.getDateOfBirth(), "30-10-1997");
				Assert.assertEquals(employee.getGender(), "Male");
				Assert.assertEquals(employee.getEmail(), "446thuya446@gmail.com");
				Assert.assertEquals(employee.getPhone(), "09795552585");
				Assert.assertEquals(employee.getAddress().getBoId(), "ADR015");
				break;

			case "EMP016":
				Assert.assertEquals(employee.getTitle(), "Mg");
				Assert.assertEquals(employee.getFirstName(), "Kaung");
				Assert.assertEquals(employee.getLastName(), "Pyae Sone Tun");
				Assert.assertEquals(employee.getPosition().getBoId(), "JOB006-4");
				Assert.assertEquals(employee.getEntranceDate(), "02-05-2019");
				Assert.assertEquals(employee.getDateOfBirth(), "27-04-1998");
				Assert.assertEquals(employee.getGender(), "Male");
				Assert.assertEquals(employee.getEmail(), "harbe27@gmail.com");
				Assert.assertEquals(employee.getPhone(), "09698917797");
				Assert.assertEquals(employee.getAddress().getBoId(), "ADR016");
				break;

			case "EMP017":
				Assert.assertEquals(employee.getTitle(), "Mg");
				Assert.assertEquals(employee.getFirstName(), "Soe");
				Assert.assertEquals(employee.getLastName(), "Min Thein");
				Assert.assertEquals(employee.getPosition().getBoId(), "JOB006-3");
				Assert.assertEquals(employee.getEntranceDate(), "02-05-2019");
				Assert.assertEquals(employee.getDateOfBirth(), "02-06-1998");
				Assert.assertEquals(employee.getGender(), "Male");
				Assert.assertEquals(employee.getEmail(), "sminthein0@gmail.com");
				Assert.assertEquals(employee.getPhone(), "09966550548");
				Assert.assertEquals(employee.getAddress().getBoId(), "ADR017");
				break;

			case "EMP018":
				Assert.assertEquals(employee.getTitle(), "Ma");
				Assert.assertEquals(employee.getFirstName(), "Ya");
				Assert.assertEquals(employee.getLastName(), "Mone Zin");
				Assert.assertEquals(employee.getPosition().getBoId(), "JOB006-2");
				Assert.assertEquals(employee.getEntranceDate(), "02-05-2019");
				Assert.assertEquals(employee.getDateOfBirth(), "30-05-1998");
				Assert.assertEquals(employee.getGender(), "Female");
				Assert.assertEquals(employee.getEmail(), "yamonezin@ucsy.edu.mm");
				Assert.assertEquals(employee.getPhone(), "09766974698");
				Assert.assertEquals(employee.getAddress().getBoId(), "ADR018");
				break;

			case "EMP019":
				Assert.assertEquals(employee.getTitle(), "Ma");
				Assert.assertEquals(employee.getFirstName(), "Kay");
				Assert.assertEquals(employee.getLastName(), "Zin Han");
				Assert.assertEquals(employee.getPosition().getBoId(), "JOB006-1");
				Assert.assertEquals(employee.getEntranceDate(), "02-05-2019");
				Assert.assertEquals(employee.getDateOfBirth(), "25-12-1997");
				Assert.assertEquals(employee.getGender(), "Female");
				Assert.assertEquals(employee.getEmail(), "kzh112608@gmail.com");
				Assert.assertEquals(employee.getPhone(), "09250508348");
				Assert.assertEquals(employee.getAddress().getBoId(), "ADR019");
				break;

			case "EMP020":
				Assert.assertEquals(employee.getTitle(), "Ma");
				Assert.assertEquals(employee.getFirstName(), "Su");
				Assert.assertEquals(employee.getLastName(), "Pyae Naing");
				Assert.assertEquals(employee.getPosition().getBoId(), "JOB006-12");
				Assert.assertEquals(employee.getEntranceDate(), "02-05-2019");
				Assert.assertEquals(employee.getDateOfBirth(), "02-09-1997");
				Assert.assertEquals(employee.getGender(), "Female");
				Assert.assertEquals(employee.getEmail(), "supyaenaing1998@gmail.com");
				Assert.assertEquals(employee.getPhone(), "09250069832");
				Assert.assertEquals(employee.getAddress().getBoId(), "ADR020");
				break;

			case "EMP021":
				Assert.assertEquals(employee.getTitle(), "Ma");
				Assert.assertEquals(employee.getFirstName(), "Saw");
				Assert.assertEquals(employee.getLastName(), "Sandi Tin");
				Assert.assertEquals(employee.getPosition().getBoId(), "JOB006-11");
				Assert.assertEquals(employee.getEntranceDate(), "02-05-2019");
				Assert.assertEquals(employee.getDateOfBirth(), "10-11-1997");
				Assert.assertEquals(employee.getGender(), "Female");
				Assert.assertEquals(employee.getEmail(), "sawsanditin@ucsy.edu.mm");
				Assert.assertEquals(employee.getPhone(), "09779755036");
				Assert.assertEquals(employee.getAddress().getBoId(), "ADR021");
				break;

			case "EMP022":
				Assert.assertEquals(employee.getTitle(), "Ma");
				Assert.assertEquals(employee.getFirstName(), "San");
				Assert.assertEquals(employee.getLastName(), "Thinzar Linn");
				Assert.assertEquals(employee.getPosition().getBoId(), "JOB006-10");
				Assert.assertEquals(employee.getEntranceDate(), "02-05-2019");
				Assert.assertEquals(employee.getDateOfBirth(), "13-10-1997");
				Assert.assertEquals(employee.getGender(), "Female");
				Assert.assertEquals(employee.getEmail(), "santhinzarlinn1997@gmail.com");
				Assert.assertEquals(employee.getPhone(), "09795324758");
				Assert.assertEquals(employee.getAddress().getBoId(), "ADR022");
				break;

			case "EMP023":
				Assert.assertEquals(employee.getTitle(), "Ma");
				Assert.assertEquals(employee.getFirstName(), "Khin");
				Assert.assertEquals(employee.getLastName(), "Chanmyae Thu");
				Assert.assertEquals(employee.getPosition().getBoId(), "JOB006-9");
				Assert.assertEquals(employee.getEntranceDate(), "02-05-2019");
				Assert.assertEquals(employee.getDateOfBirth(), "07-01-1998");
				Assert.assertEquals(employee.getGender(), "Female");
				Assert.assertEquals(employee.getEmail(), "khinchanmyae711998thu@gmail.com");
				Assert.assertEquals(employee.getPhone(), "09693934636");
				Assert.assertEquals(employee.getAddress().getBoId(), "ADR023");
				break;

			case "EMP024":
				Assert.assertEquals(employee.getTitle(), "Mg");
				Assert.assertEquals(employee.getFirstName(), "Thi");
				Assert.assertEquals(employee.getLastName(), "Han Hein");
				Assert.assertEquals(employee.getPosition().getBoId(), "JOB006-8");
				Assert.assertEquals(employee.getEntranceDate(), "02-05-2019");
				Assert.assertEquals(employee.getDateOfBirth(), "02-06-2000");
				Assert.assertEquals(employee.getGender(), "Male");
				Assert.assertEquals(employee.getEmail(), "micaljohn60@gmail.com");
				Assert.assertEquals(employee.getPhone(), "09775338983");
				Assert.assertEquals(employee.getAddress().getBoId(), "ADR024");
				break;

			default:
				Assert.assertTrue(false);
			}
		}
	}
}

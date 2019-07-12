package com.bizleap.hr.service.impl.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.bizleap.hr.service.test.ServiceTest;
import com.bizleap.service.AddressService;
import com.bizleap.service.DepartmentService;
import com.bizleap.service.EmployeeService;
import com.bizleap.service.JobService;
import com.bizleap.service.LocationService;
import com.bizleap.service.PositionService;

public class ThreadingTest extends ServiceTest {

	@Autowired
	private JobService jobService;
	
	@Autowired
	private PositionService positionService;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private AddressService addressService;
	
	@Test
	public void testJobServices() throws InterruptedException {
		Thread jobThread1 = new TestThread("Job Test 1",2,1000,jobService);
		Thread jobThread2 = new TestThread("Job Test 2",5,100,jobService);
		
		jobThread1.start();
		jobThread2.start();
		jobThread1.join();
		jobThread2.join();
	}
	
	@Test
	public void testPositionServices() throws InterruptedException {
		Thread positionThread1 = new TestThread("Position Test 1",3,2000,positionService);
		Thread positionThread2 = new TestThread("Position Test 2",4,300,positionService);
		
		positionThread1.start();
		positionThread2.start();
		positionThread1.join();
		positionThread2.join();
	}
	
	@Test
	public void testLocationServices() throws InterruptedException {
		Thread locationThread1 = new TestThread("Location Test 1",5,200,locationService);
		Thread locationThread2 = new TestThread("Location Test 2",2,2000,locationService);
		
		locationThread1.start();
		locationThread2.start();
		locationThread1.join();
		locationThread2.join();
	}
	
	@Test
	public void testDepartmentServices() throws InterruptedException {
		Thread departmentThread1 = new TestThread("Department Test 1",5,500,departmentService);
		Thread departmentThread2 = new TestThread("Department Test 2",2,400,departmentService);
		
		departmentThread1.start();
		departmentThread2.start();
		departmentThread1.join();
		departmentThread2.join();
	}
	
	@Test
	public void testEmployeeServices() throws InterruptedException {
		Thread employeeThread1 = new TestThread("Employee Test 1",7,50,employeeService);
		Thread employeeThread2 = new TestThread("Employee Test 2",4,1000,employeeService);
		
		employeeThread1.start();
		employeeThread2.start();
		employeeThread1.join();
		employeeThread2.join();
	}
	
	@Test
	public void testAddressServices() throws InterruptedException {
		Thread addressThread1 = new TestThread("Address Test 1",2,500,addressService);
		Thread addressThread2 = new TestThread("Address Test 2",3,400,addressService);
		
		addressThread1.start();
		addressThread2.start();
		addressThread1.join();
		addressThread2.join();
	}
}

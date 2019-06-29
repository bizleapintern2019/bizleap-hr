/*
d * ____________________
 *
 * Copyright 2014 - 2015 BizLeap Technology
 *
 * All Rights Reserved. www.bizleap.com
 *
 * NOTICE: All information contained herein is, and remains
 * the property of BizLeap Technology or its group
 * companies. The intellectual and technical concepts contained
 * herein are proprietary to BizLeap Technology may be covered by Myanmar and
 * Foreign Patents, patents in process, and are protected by
 * trade secret or copyright law. Dissemination of this information
 * or reproduction of this material is strictly forbidden unless prior
 * written permission is obtained from BizLeap Technology. Any
 * reproduction of this material must contain this notice
 */
package com.bizleap.hr.core.domain.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TimeZone;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizleap.commons.domain.AbstractEntity;
import com.bizleap.commons.domain.OperationRequest;
import com.bizleap.commons.domain.OperationResponse;
import com.bizleap.commons.domain.SearchRequest;
import com.bizleap.commons.domain.SearchResponse;
import com.bizleap.commons.domain.enums.EntityStatus;
import com.bizleap.commons.domain.enums.EntityType;
import com.bizleap.commons.domain.enums.ObjectFullnessLevel;
import com.bizleap.commons.domain.enums.ParamName;
import com.bizleap.commons.domain.enums.SearchKey;
import com.bizleap.commons.domain.enums.hr.AssignmentType;
import com.bizleap.commons.domain.enums.hr.AttendanceType;
import com.bizleap.commons.domain.enums.hr.EmploymentStatus;
import com.bizleap.commons.domain.enums.hr.EmploymentType;
import com.bizleap.commons.domain.enums.hr.LeaveType;
import com.bizleap.commons.service.impl.AbstractServiceImpl;
import com.bizleap.commons.util.Author;
import com.bizleap.commons.util.BizleapUtils;
import com.bizleap.commons.util.DateUtil;
import com.bizleap.hr.core.domain.dao.EmployeeDao;
import com.bizleap.hr.core.domain.entity.Department;
import com.bizleap.hr.core.domain.entity.Employee;
import com.bizleap.hr.core.domain.entity.EmployeeClaim;
import com.bizleap.hr.core.domain.entity.Employment;
import com.bizleap.hr.core.domain.entity.EmploymentApplication;
import com.bizleap.hr.core.domain.entity.Note;
import com.bizleap.hr.core.domain.entity.Position;
import com.bizleap.hr.core.domain.entity.WorkAssignment;
import com.bizleap.hr.core.domain.service.DataService;
import com.bizleap.hr.core.domain.service.DepartmentService;
import com.bizleap.hr.core.domain.service.EmployeeClaimService;
import com.bizleap.hr.core.domain.service.EmployeeService;
import com.bizleap.hr.core.domain.service.EmploymentApplicationService;
import com.bizleap.hr.core.domain.service.PositionService;
import com.bizleap.hr.core.domain.service.ShiftService;
import com.bizleap.hr.core.domain.service.attendance.AttendanceService;
import com.bizleap.hr.core.domain.util.DepartmentTree;
import com.bizleap.security.domain.service.exception.ServiceUnavailableException;

/**
 * 
 * @author Myat Pwint Phyu
 * @since 1.0.0
 */
@Service("employeeService")
@Transactional(readOnly = true)
public class EmployeeServiceImpl extends AbstractServiceImpl implements EmployeeService {

	private boolean exactSearch = false;

	@Value("${employee.prefix}")
	private String employeePrefix;

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	protected DataService dataService;

	@Autowired
	protected AttendanceService attendanceService;

	@Autowired
	protected ShiftService shiftService;

	@Autowired
	private QueryFactory queryFactory;

	@Lazy
	@Autowired
	private EmployeeClaimService employeeClaimService;

	@Autowired
	private PositionService positionServiceSummary;

	private SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yy-MM-dd");

	private static Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmploymentApplicationService employmentApplicationService;

	@Override
	@Transactional(readOnly = false)
	public void save(Employee employee) throws ServiceUnavailableException {
		if (employee.isBoIdRequired()) {
			employee.setBoId(getEmployeeBoId());
		}
		employee.setVersion(employee.getVersion() + 1);
		employeeDao.save(employee);
		employee.updateSearchTerm(employee);
	}

	private String getEmployeeBoId() {
		return makeBoId(EntityType.EMPLOYEE, (int) countEmployee() + 1);
	}

	@Override
	public long countEmployee() {
		return employeeDao.getCount("select count(emp) from Employee emp");
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEmployee(String boId) throws ServiceUnavailableException {
		Employee employee = findByEmployeeBoIdSingle(boId);
		if (employee != null && employee.getStatus().equals(EntityStatus.ACTIVE)) {
			employee.setStatus(EntityStatus.DELETED);
			employeeDao.save(employee);
			employee.getContactAddress().setStatus(EntityStatus.DELETED);
		}
	}

	@Override
	public List<Employee> getAllEmployees() {
		String query = "from Employee emp where emp.status = :status";
		List<Employee> empList = employeeDao.getAll(query + addEmployeeFilter());
		hibernateInitializeEmployeeList(empList);
		return empList;
	}

	private String addEmployeeFilter() {
		return " and emp.employmentStatus<>'TERMINATED' and emp.employmentStatus<>'SEPARATED' and emp.employmentStatus<>'RESIGNED' and emp.employmentStatus<>'DISMISSED'";
	}

	public List<Employee> getAllEmployees(String status) {
		String query = "from Employee emp where emp.status = :status and emp.employmentStatus<>'TERMINATED' and emp.employmentStatus<>'SEPARATED' and emp.employmentStatus<>'RESIGNED'";
		query = addQueryToFilterOutProbation(query, status, "emp");
		List<Employee> empList = employeeDao.getAll(query);
		hibernateInitializeEmployeeList(empList);
		return empList;
	}

	public List<Employee> findByEmployeeBoId(String boId, String status) throws ServiceUnavailableException {
		String queryStr = "select employee from Employee employee where employee.boId = :dataInput and employee.status = :status and employee.employmentStatus<>'TERMINATED' and employee.employmentStatus<>'SEPARATED'";
		// if (EmploymentStatus.valueOf(status) == EmploymentStatus.PROVISIONAL)
		// {
		queryStr = addQueryToFilterOutProbation(queryStr, status, "employee");
		// }
		List<Employee> employeeList;
		employeeList = employeeDao.findByString(queryStr, boId);
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	public Employee findByEmployeeApplicantIdSingle(long id) throws ServiceUnavailableException {
		List<Employee> employeeList = findByEmployeeApplicantId(id);
		if (employeeList.size() > 0) {
			return employeeList.get(0);
		}
		return null;
	}

	public List<Employee> findByEmployeeApplicantId(long id) throws ServiceUnavailableException {
		String queryStr = "select employee from Employee employee where employee.applicant.id =" + id + " and employee.status = :status and employee.employmentStatus<>'TERMINATED' and employee.employmentStatus<>'SEPARATED' and employee.employmentStatus<>'RESIGNED'";
		List<Employee> employeeList;
		employeeList = employeeDao.findByString(queryStr);
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findByEmployeeBoId(String boId) throws ServiceUnavailableException {
		String queryStr = "select employee from Employee employee where employee.boId = :dataInput and employee.status = :status and employee.employmentStatus<>'TERMINATED' and employee.employmentStatus<>'SEPARATED' and employee.employmentStatus<>'RESIGNED'";
		List<Employee> employeeList;
		employeeList = employeeDao.findByString(queryStr, boId);
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	/*
	 * @author Ye Yint Thu
	 */
	@Override
	public List<Employee> findByEmployeeBoIdAnyStatus(String boId) throws ServiceUnavailableException {
		String queryStr = "select employee from Employee employee where employee.boId = :dataInput and employee.status = :status";
		List<Employee> employeeList;
		employeeList = employeeDao.findByString(queryStr, boId);
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}
	
	public List<Employee> getOldEmployeeList()throws ServiceUnavailableException {
		String queryStr = "from Employee employee where employee.employmentStatus !=:status1 and employee.employmentStatus !=:status2";
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("status1", EmploymentStatus.ACTIVE);
		parameter.put("status2", EmploymentStatus.PROVISIONAL);
		List<Employee> oldEmployeeList = employeeDao.findByString(queryStr, parameter);
		hibernateInitializeEmployeeList(oldEmployeeList);
		return oldEmployeeList;
	}

	@Override
	public List<Employee> findByTitle(String title) throws ServiceUnavailableException {
		String queryStr = "select employee from Employee employee where employee.title LIKE :dataInput and employee.status = :status and employee.employmentStatus<>'TERMINATED' and employee.employmentStatus<>'SEPARATED'";
		List<Employee> employeeList;
		employeeList = employeeDao.findByString(queryStr, title + "%");
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public Employee findByEmployeeBoIdSingle(String boId) throws ServiceUnavailableException {
		List<Employee> employeeList = findByEmployeeBoId(boId);
		if (employeeList.size() > 0) {
			hibernateInitializeEmployeeList(employeeList);
			return employeeList.get(0);
		}
		return null;
	}

	/*
	 * @author Ye Yint Thu
	 */
	@Override
	public Employee findByEmployeeBoIdSingleAnyStatus(String boId) throws ServiceUnavailableException {
		List<Employee> employeeList = findByEmployeeBoIdAnyStatus(boId);
		if (employeeList.size() > 0) {
			return employeeList.get(0);
		}
		return null;
	}

	private List<Employee> findByTitle(String title, String status) throws ServiceUnavailableException {
		String queryStr = "select employee from Employee employee where employee.title LIKE :dataInput and employee.status = :status and employee.employmentStatus<>'TERMINATED' and employee.employmentStatus<>'SEPARATED' and emp.employmentStatus<>'RESIGNED'";
		if (status != null && status.equals(EmploymentStatus.PROVISIONAL.toString())) {
			queryStr += "and employee.EmploymentStatus=\'" + EmploymentStatus.PROVISIONAL.value() + "\'";
		} else {
			queryStr += "and employee.EmploymentStatus<>\'" + EmploymentStatus.PROVISIONAL.value() + "\'";
		}
		List<Employee> employeeList;
		employeeList = employeeDao.findByString(queryStr, title + "%");
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findByEmploymentType(String type) throws ServiceUnavailableException {
		EmploymentType empType = EmploymentType.valueOf(type);
		String queryStr = "select employee from Employee employee where employee.employmentType LIKE :dataInput and employee.status = :status and employee.employmentStatus<>'TERMINATED' and employee.employmentStatus<>'SEPARATED'";
		List<Employee> employeeList;
		employeeList = employeeDao.findByEmploymentType(queryStr, empType);
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findEmploymentTypeByDate(String date, String type) throws ServiceUnavailableException {
		EmploymentType empType = EmploymentType.valueOf(type);
		String queryStr = "select employee from Employee employee where " + " employee.entranceDate = :dateInput and " + "employee.employmentType LIKE :dataInput and employee.status = :status and employee.employmentStatus<>'TERMINATED' and employee.employmentStatus<>'SEPARATED'";
		List<Employee> employeeList;
		employeeList = employeeDao.findByDateAndStr(queryStr, parseDate(date), empType);
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findByEmploymentStatus(String type) throws ServiceUnavailableException {
		EmploymentStatus empType = EmploymentStatus.valueOf(type);
		String queryStr = "select employee from Employee employee " + "where employee.employmentStatus LIKE :dataInput " + "and employee.status = :status and employee.employmentStatus<>'TERMINATED' and employee.employmentStatus<>'SEPARATED'";
		List<Employee> employeeList;
		employeeList = employeeDao.findByEmploymentStatus(queryStr, empType);
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	public List<Employee> findWithFullName(String fullName, String status) throws ServiceUnavailableException {

		return findWithFullName(fullName, status, "LIKE");
	}

	public List<Employee> findWithFullName(String fullName, String status, String operator) throws ServiceUnavailableException {
		String query = "from Employee emp where replace(concat(emp.firstName,' ',emp.middleName,' ',emp.lastName),'  ',' ') " + operator + " :dataInput and emp.status = :status and emp.employmentStatus<>'TERMINATED' and emp.employmentStatus<>'SEPARATED' and emp.employmentStatus<>'RESIGNED' and emp.employmentStatus<> 'DISMISSED'";
		List<Employee> employeeList = employeeDao.findByString(addQueryToFilterOutProbation(query, status, "emp"), "%" + fullName + "%");
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findFullNameForAttendance(String fullName) throws ServiceUnavailableException {
		String operator = "LIKE";
		List<Employee> employeeList = findWithFullName(fullName, null, operator);
		List<Employee> newEmployeeList = new ArrayList<Employee>();
		for (Employee emp : employeeList) {
			if (!emp.getEmploymentStatus().equals("RESIGNED")) {
				newEmployeeList.add(emp);
			}
		}
		hibernateInitializeEmployeeList(newEmployeeList);
		return newEmployeeList;
	}

	@Override
	public List<Employee> findByDob(String dob) throws ServiceUnavailableException {
		return findByDob(dob, null);
	}

	private List<Employee> findByDob(String dob, String status) throws ServiceUnavailableException {
		String queryStr = "select emp from Employee emp where year(:dateInput) = year(emp.dateOfBirth) " + "AND month(:dateInput) = month(emp.dateOfBirth) " + "AND day(:dateInput) = day(emp.dateOfBirth)" + "AND emp.status = :status and emp.employmentStatus<>'TERMINATED' and emp.employmentStatus<>'SEPARATED' and emp.employmentStatus<>'RESIGNED'";
		queryStr = addQueryToFilterOutProbation(queryStr, status, "emp");
		List<Employee> employeeList;
		employeeList = employeeDao.findByDate(queryStr, parseDate(dob));
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findByDobMonth(int month) throws ServiceUnavailableException {
		return findByDobMonth(month, null);
	}

	private List<Employee> findByDobMonth(int month, String status) throws ServiceUnavailableException {
		String queryStr = "select emp from Employee emp where " + ":dataInput = month(emp.dateOfBirth) " + "AND emp.status = :status and emp.employmentStatus<>'TERMINATED' and emp.employmentStatus<>'SEPARATED'";
		queryStr = addQueryToFilterOutProbation(queryStr, status, "emp");
		List<Employee> employeeList;
		employeeList = employeeDao.findByInteger(queryStr, month);
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	public List<Employee> byDepartment(Department department) {
		String query = "select distinct p.employee from Department d join d.jobs j join j.positionList p where d.boId = :dataInput and p.employee.status = :status";
		return employeeDao.findByString(query, department.getBoId());
	}

	@Override
	public List<Employee> findByPhone(String phone) throws ServiceUnavailableException {
		return findByPhone(phone, null);
	}

	private List<Employee> findByPhone(String phone, String status) throws ServiceUnavailableException {
		String queryStr = "select employee from Employee employee where ( employee.phoneNumber LIKE :dataInput or employee.applicant.contactAddress.phoneNumber LIKE :dataInput or employee.applicant.permanentAddress.phoneNumber LIKE :dataInput ) and employee.status = :status and employee.employmentStatus<>'TERMINATED' and employee.employmentStatus<>'SEPARATED' and employee.employmentStatus<>'RESIGNED' and employee.employmentStatus<>'DISMISSED'";
		queryStr = addQueryToFilterOutProbation(queryStr, status, "employee");
		List<Employee> employeeList;
		phone = phone.replaceAll(" ", "");
		employeeList = employeeDao.findByString(queryStr, "%" + phone + "%");
		hibernateInitializeEmployeeList(employeeList);
		return (employeeList);
	}

	@Override
	public List<Employee> findByEmail(String email) throws ServiceUnavailableException {
		String queryStr = "select employee from Employee employee where employee.applicant.email LIKE :dataInput and employee.status = :status and employee.employmentStatus<>'TERMINATED' and employee.employmentStatus<>'SEPARATED'";
		List<Employee> employeeList;
		employeeList = employeeDao.findByString(queryStr, email + "%");
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	private List<Employee> findByEmail(String email, String status) throws ServiceUnavailableException {
		String queryStr = "select employee from Employee employee where employee.applicant.email LIKE :dataInput and employee.status = :status and employee.employmentStatus<>'TERMINATED' and employee.employmentStatus<>'SEPARATED' and employee.employmentStatus<>'RESIGNED'";
		queryStr = addQueryToFilterOutProbation(queryStr, status, "employee");
		List<Employee> employeeList;
		employeeList = employeeDao.findByString(queryStr, email + "%");
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findByNrc(String nrc) throws ServiceUnavailableException {
		return findByNrc(nrc, null);
	}

	private List<Employee> findByNrc(String nrc, String status) throws ServiceUnavailableException {
		String queryStr = "select employee from Employee employee where employee.applicant.nrc LIKE :dataInput and employee.status = :status and employee.employmentStatus<>'TERMINATED' and employee.employmentStatus<>'SEPARATED' and employee.employmentStatus<>'RESIGNED' and employee.employmentStatus<>'DISMISSED'";
		queryStr = addQueryToFilterOutProbation(queryStr, status, "employee");
		List<Employee> employeeList;
		employeeList = employeeDao.findByString(queryStr, nrc + "%");
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findEmployeeByAddress(String address) throws ServiceUnavailableException {
		return findEmployeeByAddress(address, null);
	}

	private List<Employee> findEmployeeByAddress(String address, String status) throws ServiceUnavailableException {
		String queryStr = "select employee from Employee employee where employee.applicant.contactAddress.addressLine1 LIKE :dataInput or employee.applicant.contactAddress.addressLine2 LIKE :dataInput and employee.status = :status and employee.employmentStatus<>'TERMINATED' and employee.employmentStatus<>'SEPARATED' and employee.employmentStatus<>'RESIGNED'";
		queryStr = addQueryToFilterOutProbation(queryStr, status, "employee");
		List<Employee> employeeList;
		employeeList = employeeDao.findByString(queryStr, "%" + address + "%");
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findEmployeeByCity(String city) throws ServiceUnavailableException {
		return findEmployeeByCity(city, null);
	}

	private List<Employee> findEmployeeByCity(String city, String status) throws ServiceUnavailableException {
		String queryStr = "select employee from Employee employee where (employee.applicant.contactAddress.city LIKE :dataInput or employee.applicant.permanentAddress.city LIKE :dataInput) and employee.status = :status  and employee.employmentStatus<>'TERMINATED' and employee.employmentStatus<>'SEPARATED' and employee.employmentStatus<>'RESIGNED' and employee.employmentStatus<>'DISMISSED'";
		// String queryStr = "select employee from Employee employee where
		// employee.applicant.contactAddress.city LIKE :dataInput and
		// employee.status = :status and employee.employmentStatus<>'TERMINATED'
		// and employee.employmentStatus<>'SEPARATED'";
		queryStr = addQueryToFilterOutProbation(queryStr, status, "employee");
		List<Employee> employeeList;
		employeeList = employeeDao.findByString(queryStr, exactSearch ? city : city + "%");
		if (employeeList.size() == 0) {
			queryStr = "select employee from Employee employee where employee.applicant.contactAddress.city LIKE :dataInput and employee.status = :status  and employee.employmentStatus<>'TERMINATED' and employee.employmentStatus<>'SEPARATED'";
			employeeList = employeeDao.findByString(queryStr, exactSearch ? city : city + "%");
		}
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findEmployeeByState(String state) throws ServiceUnavailableException {
		return findEmployeeByState(state, null);
	}

	private List<Employee> findEmployeeByState(String state, String status) throws ServiceUnavailableException {
		String queryStr = "select employee from Employee employee where (employee.applicant.contactAddress.state LIKE :dataInput or employee.applicant.permanentAddress.state LIKE :dataInput) and employee.status = :status  and employee.employmentStatus<>'TERMINATED' and employee.employmentStatus<>'SEPARATED' and employee.employmentStatus<>'RESIGNED' and employee.employmentStatus<>'DISMISSED'";
		queryStr = addQueryToFilterOutProbation(queryStr, status, "employee");
		List<Employee> employeeList = employeeDao.findByString(queryStr, "%" + state + "%");
		if (employeeList.size() == 0) {
			queryStr = "select employee from Employee employee where employee.applicant.contactAddress.state LIKE :dataInput and employee.status = :status  and employee.employmentStatus<>'TERMINATED' and employee.employmentStatus<>'SEPARATED'";
			employeeList = employeeDao.findByString(queryStr, exactSearch ? state : state + "%");
		}
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	public List<Employee> findByAttendanceType(String type) throws ServiceUnavailableException {
		String queryStr = "select distinct emp from Employee emp join emp.attendanceList aList where aList.attendanceType = :dataInput and emp.status = :status  and emp.employmentStatus<>'TERMINATED' and emp.employmentStatus<>'SEPARATED'";
		List<Employee> employeeList;
		employeeList = employeeDao.findByATTENDANCE_TYPE(queryStr, AttendanceType.valueOf(type));
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findByFingerprintCode(String fingerPrintCode) throws ServiceUnavailableException {
		String queryStr = "select employee from Employee employee where employee.fingerprintCode = :dataInput and" + " employee.status = :status " + " and employee.employmentStatus<>'TERMINATED' and employee.employmentStatus<>'SEPARATED'";
		List<Employee> employeeList = employeeDao.findByString(queryStr, fingerPrintCode);
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public Employee findByFingerprintCodeAnyStatusSingle(String fingerprintCode) throws ServiceUnavailableException {
		String queryStr = "select employee from Employee employee where employee.fingerprintCode = :dataInput and" + " employee.status = :status ";
		List<Employee> employeeList = employeeDao.findByString(queryStr, fingerprintCode);
		hibernateInitializeEmployeeList(employeeList);
		return employeeList.isEmpty() ? null : employeeList.get(0);
	}

	@Override
	public Employee findByFingerprintCodeExactly(String fingerPrintCode) throws ServiceUnavailableException {
		List<Employee> employeeList = findByFingerprintCode(fingerPrintCode);
		return employeeList.isEmpty() ? null : employeeList.get(0);
	}

	@Override
	public List<Employee> findByJoiningDate(String date) throws ServiceUnavailableException {
		String queryStr = "select employee from Employee employee where year(:dateInput) = year(employee.entranceDate) " + "AND month(:dateInput) = month(employee.entranceDate) " + "AND day(:dateInput) = day(employee.entranceDate)" + "AND employee.status = :status  and employee.employmentStatus<>'TERMINATED' and employee.employmentStatus<>'SEPARATED' and employee.employmentStatus<>'RESIGNED' and employee.employmentStatus<>'DISMISSED'";
		List<Employee> employeeList;
		employeeList = employeeDao.findByDate(queryStr, parseDate(date));
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	public List<Employee> findByJoiningDate(String date, String status) throws ServiceUnavailableException {
		String queryStr = "select employee from Employee employee where year(:dateInput) = year(employee.entranceDate) " + "AND month(:dateInput) = month(employee.entranceDate) " + "AND day(:dateInput) = day(employee.entranceDate)" + "AND employee.status = :status  and employee.employmentStatus<>'TERMINATED' and employee.employmentStatus<>'SEPARATED' and employee.employmentStatus<>'RESIGNED' and employee.employmentStatus<>'DISMISSED'";
		queryStr = addQueryToFilterOutProbation(queryStr, status, "employee");
		List<Employee> employeeList;
		employeeList = employeeDao.findByDate(queryStr, DateUtil.parseDate(date));
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findByLeaveStartDate(String date) throws ServiceUnavailableException {
		String queryStr = "select emp from Employee emp join emp.leaves lList where year(:dateInput) = year(lList.startDate) " + "AND month(:dateInput) = month(lList.startDate) " + "AND day(:dateInput) = day(lList.startDate)" + "AND emp.status = :status  and emp.employmentStatus<>'TERMINATED' and emp.employmentStatus<>'SEPARATED'";
		List<Employee> employeeList;
		employeeList = employeeDao.findByDate(queryStr, parseDate(date));
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findByJoiningYear(int year) throws ServiceUnavailableException {
		String queryStr = "select employee from Employee employee where year(employee.entranceDate) = :dataInput and employee.status = :status  and employee.employmentStatus<>'TERMINATED' and employee.employmentStatus<>'SEPARATED'";
		List<Employee> employeeList;
		employeeList = employeeDao.findByInteger(queryStr, year);
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	public List<Employee> findByJoiningYear(int year, String status) throws ServiceUnavailableException {
		String queryStr = "select employee from Employee employee where year(employee.entranceDate) = :dataInput and employee.status = :status  and employee.employmentStatus<>'TERMINATED' and employee.employmentStatus<>'SEPARATED' and employee.employmentStatus<>'RESIGNED' and employee.employmentStatus<>'DISMISSED'";
		queryStr = addQueryToFilterOutProbation(queryStr, status, "employee");
		List<Employee> employeeList;
		employeeList = employeeDao.findByInteger(queryStr, year);
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findByEntranceDateRange(String startDate, String endDate) throws ServiceUnavailableException {
		String queryStr = "select employee from Employee employee where employee.entranceDate BETWEEN :dateInput AND :dateInput1 and employee.status = :status  and employee.employmentStatus<>'TERMINATED' and employee.employmentStatus<>'SEPARATED' and employee.employmentStatus<>'DISMISSED'";
		List<Employee> employeeList;
		employeeList = employeeDao.findByDateRange(queryStr, parseDate(startDate), parseDate(endDate));
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	private List<Employee> findByEntranceDateRange(String startDate, String endDate, String status) throws ServiceUnavailableException {
		String queryStr = "select employee from Employee employee where employee.entranceDate BETWEEN :dateInput AND :dateInput1 and employee.status = :status  and employee.employmentStatus<>'TERMINATED' and employee.employmentStatus<>'SEPARATED' and employee.employmentStatus<>'RESIGNED' and employee.employmentStatus<>'DISMISSED'";
		queryStr = addQueryToFilterOutProbation(queryStr, status, "employee");
		List<Employee> employeeList;
		employeeList = employeeDao.findByDateRange(queryStr, DateUtil.parseDate(startDate), DateUtil.parseDate(endDate));
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findByEmploymentDateRange(String startDate, String endDate) throws ServiceUnavailableException {
		return findByEmploymentDateRange(startDate, endDate, null);
	}

	private List<Employee> findByEmploymentDateRange(String startDate, String endDate, String status) throws ServiceUnavailableException {
		String queryStr = "select emp from Employee emp join emp.employmentList empList " + "where empList.endDate=null " + "and empList.startDate BETWEEN :dateInput AND :dateInput1 " + "and emp.status = :status  and emp.employmentStatus<>'TERMINATED' and emp.employmentStatus<>'SEPARATED' and emp.employmentStatus<>'RESIGNED' and emp.employmentStatus<>'DISMISSED'";
		queryStr = addQueryToFilterOutProbation(queryStr, status, "emp");
		List<Employee> employeeList;
		employeeList = employeeDao.findByDateRange(queryStr, parseDate(startDate), parseDate(endDate));
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findByLeaveDateRange(String startDate, String endDate) throws ServiceUnavailableException {
		String queryStr = "select emp from Employee emp join emp.leaves lList where lList.startDate BETWEEN :dateInput AND :dateInput1 and emp.status = :status  and emp.employmentStatus<>'TERMINATED' and emp.employmentStatus<>'SEPARATED'";
		List<Employee> employeeList;
		employeeList = employeeDao.findByDateRange(queryStr, parseDate(startDate), parseDate(endDate));
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findByDepartment(String department) throws ServiceUnavailableException {
		String queryStr = "select emp from Employee emp where emp.primaryPosition.job.department.name LIKE :dataInput and emp.status = :status  and emp.employmentStatus<>'TERMINATED' and emp.employmentStatus<>'SEPARATED'";
		List<Employee> employeeList;
		employeeList = employeeDao.findByString(queryStr, exactSearch ? department : "%" + department + "%");
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	private List<Employee> findByDepartment(String department, String status) throws ServiceUnavailableException {
		String queryStr = "select emp from Employee emp where emp.primaryPosition.job.department.name LIKE :dataInput and emp.status = :status  and emp.employmentStatus<>'TERMINATED' and emp.employmentStatus<>'SEPARATED' and emp.employmentStatus<>'RESIGNED' and emp.employmentStatus<>'DISMISSED'";
		queryStr = addQueryToFilterOutProbation(queryStr, status, "emp");
		List<Employee> employeeList;
		employeeList = employeeDao.findByString(queryStr, exactSearch ? department : "%" + department + "%");
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findByEducation(String degree) throws ServiceUnavailableException {
		String queryStr = "select emp from Employee emp join emp.applicant.educations edu where edu.grade LIKE :dataInput and emp.status = :status  and emp.employmentStatus<>'TERMINATED' and emp.employmentStatus<>'SEPARATED'";
		List<Employee> employeeList;
		employeeList = employeeDao.findByString(queryStr, exactSearch ? degree : "%" + degree + "%");
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	private List<Employee> findByEducation(String degree, String status) throws ServiceUnavailableException {
		String queryStr = "select emp from Employee emp join emp.applicant.educations edu where edu.grade LIKE :dataInput and emp.status = :status  and emp.employmentStatus<>'TERMINATED' and emp.employmentStatus<>'SEPARATED' and emp.employmentStatus<>'RESIGNED'";
		List<Employee> employeeList;
		queryStr = addQueryToFilterOutProbation(queryStr, status, "emp");
		employeeList = employeeDao.findByString(queryStr, exactSearch ? degree : "%" + degree + "%");
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findByRank(String rank) throws ServiceUnavailableException {
		String queryStr = "select emp from Employee emp " + "where emp.primaryJob.jobTitle LIKE :dataInput " + "and emp.status = :status  and emp.employmentStatus<>'TERMINATED' and emp.employmentStatus<>'SEPARATED' and emp.employmentStatus<>'RESIGNED'";
		List<Employee> employeeList;
		employeeList = employeeDao.findByString(queryStr, exactSearch ? rank : "%" + rank + "%");
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	public List<Employee> findByRank(String rank, String status) throws ServiceUnavailableException {
		String queryStr = "select emp from Employee emp where emp.primaryJob.jobTitle LIKE :dataInput and emp.status = :status  and emp.employmentStatus<>'TERMINATED' and emp.employmentStatus<>'SEPARATED' and emp.employmentStatus<>'RESIGNED'";
		queryStr = addQueryToFilterOutProbation(queryStr, status, "emp");
		List<Employee> employeeList;
		employeeList = employeeDao.findByString(queryStr, exactSearch ? rank : "%" + rank + "%");
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findByBranch(String branch) throws ServiceUnavailableException {
		return findByBranch(branch, null);
	}

	private List<Employee> findByBranch(String branch, String status) throws ServiceUnavailableException {
		String queryStr = "select emp from Employee emp join emp.employmentList employment where employment.department.branch LIKE :dataInput and emp.status = :status  and emp.employmentStatus<>'TERMINATED' and emp.employmentStatus<>'SEPARATED' and emp.employmentStatus<>'RESIGNED'";
		queryStr = addQueryToFilterOutProbation(queryStr, status, "emp");
		List<Employee> employeeList;
		employeeList = employeeDao.findByString(queryStr, exactSearch ? branch : "%" + branch + "%");
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findByLeaveType(String leaveType) throws ServiceUnavailableException {
		LeaveType lType = null;
		switch (leaveType) {
		case "ANNUAL":
			lType = LeaveType.ANNUAL;
			break;
		case "CASUAL":
			lType = LeaveType.CASUAL;
			break;
		}
		String queryStr = "select employee from Employee employee join employee.leaves lList where lList.leaveType LIKE :dataInput and employee.status = :status  and employee.employmentStatus<>'TERMINATED' and employee.employmentStatus<>'SEPARATED'";
		List<Employee> employeeList;
		employeeList = employeeDao.findByLeaveType(queryStr, lType);
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findByLeaveMonth(int month, String empName) throws ServiceUnavailableException {
		String queryStr = "select emp from Employee emp join emp.leaves lList where month(lList.startDate) = :dataInput " + "or month(lList.endDate) = :dataInput " + "and emp.firstName = :dataInput1 " + "and emp.status = :status  and emp.employmentStatus<>'TERMINATED' and emp.employmentStatus<>'SEPARATED'";
		List<Employee> employeeList;
		employeeList = employeeDao.findByIntegerString(queryStr, month, empName);
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findByTotalServiceOver(int totalSerivce) throws ServiceUnavailableException {
		return findByTotalServiceOver(totalSerivce, null);
	}

	private List<Employee> findByTotalServiceOver(int totalService, String status) throws ServiceUnavailableException {
		Date date = decreaseDate(totalService);
		List<Employee> employeeList = new ArrayList<Employee>();
		List<Employee> empList = getAllEmployees();
		for (Employee employee : empList) {
			try {
				if (employee.getEntranceDate() != null) {
					if (employee.getEntranceDate().before(date) || employee.getEntranceDate().equals(date))
						employeeList.add(employee);
				} else if (!employee.getEmploymentList().isEmpty() && employee.getEmploymentList().size() > 0) {
					if ((employee.getEmploymentList().get(0).getStartDate().before(date) || employee.getEmploymentList().get(0).getStartDate().equals(date)))
						employeeList.add(employee);
				}
			} catch (Exception e) {
				// logger.error("Entrance date is null." + employee.getBoId());
			}
		}
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findByTotalServiceUnder(int totalService) throws ServiceUnavailableException {
		return findByTotalServiceUnder(totalService, null);
	}

	private List<Employee> findByTotalServiceUnder(int totalService, String status) throws ServiceUnavailableException {
		Date date = decreaseDate(totalService);
		List<Employee> employeeList = new ArrayList<Employee>();
		// List<Employee> sList = getAllEmployees(status);
		List<Employee> empList = getAllEmployees();
		for (Employee employee : empList) {
			if (employee.getEntranceDate() != null) {
				if (employee.getEntranceDate().after(date) || employee.getEntranceDate().equals(date))
					employeeList.add(employee);
			} else if (employee.getEmploymentList().size() > 0) {
				if (!employee.getEmploymentList().isEmpty() && employee.getEmploymentList().get(0).getStartDate().after(date))
					employeeList.add(employee);
			}
		}
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findByTotalServiceRange(int start, int end) throws ServiceUnavailableException {
		return findByTotalServiceRange(start, end, null);
	}

	private List<Employee> findByTotalServiceRange(int start, int end, String status) throws ServiceUnavailableException {
		Date startDate = decreaseDate(start);
		Date endDate = decreaseDate(end);
		List<Employee> employeeList = new ArrayList<Employee>();
		List<Employee> sList = getAllEmployees(status);
		for (Employee employee : sList) {
			if (employee.getEmploymentList().size() > 0) {
				if ((employee.getEmploymentList().get(0).getStartDate().after(startDate) || employee.getEmploymentList().get(0).getStartDate().equals(startDate)) && (employee.getEmploymentList().get(0).getStartDate().before(endDate) || employee.getEmploymentList().get(0).getStartDate().equals(endDate)))
					employeeList.add(employee);
			}
		}
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	public List<Employee> findTodayAttendanceByAttendanceType(String attendanceType) throws ServiceUnavailableException {
		Date today = Calendar.getInstance(TimeZone.getDefault()).getTime();
		String queryStr = "select distinct emp from Employee emp join emp.attendanceList aList where " + " aList.date = :dateInput and " + " aList.attendanceType = :dataInput and " + " emp.status = :status  and emp.employmentStatus<>'TERMINATED' and emp.employmentStatus<>'SEPARATED'";
		List<Employee> employeeList = employeeDao.findByDateAndString(queryStr, today, AttendanceType.valueOf(attendanceType));
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findByFamilyMemberData(String name, String rank, String dept) throws ServiceUnavailableException {
		List<Employee> empNameList = findByEmployeeName(name);
		String queryStr = "select emp from Employee emp join emp.employmentList empList " + "where empList.job.department.name LIKE :dataInput OR empList.job.department.branch LIKE :dataInput " + "and empList.job.jobTitle LIKE :dataInput1 " + "and emp.status = :status  and emp.employmentStatus<>'TERMINATED' and emp.employmentStatus<>'SEPARATED'";
		List<Employee> employeeList = new ArrayList<Employee>();

		for (Employee emp : employeeDao.findByString(queryStr, exactSearch ? dept : "%" + dept + "%", exactSearch ? rank : "%" + rank + "%")) {
			for (Employee empl : empNameList)
				if (empl.getBoId().equals(emp.getBoId()))
					employeeList.add(emp);
		}
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	public List<Employee> findByTodayAttendance() throws ServiceUnavailableException {
		Date today = Calendar.getInstance(TimeZone.getDefault()).getTime();
		String queryStr = "select distinct emp from Employee emp join emp.attendanceList aList where aList.date = :dateInput and emp.status = :status  and emp.employmentStatus<>'TERMINATED' and emp.employmentStatus<>'SEPARATED'";
		List<Employee> employeeList = employeeDao.findByDate(queryStr, today);
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	private List<Employee> findAttendanceByDateRange(String startDate, String endDate) throws ServiceUnavailableException {
		// String queryStr =
		// "select employee from Employee employee where
		// employee.attendanceList.date BETWEEN :dateInput AND :dateInput1 and
		// employee.status = :status";
		String queryStr = "select distinct emp from Employee emp join emp.attendanceList aList where aList.date BETWEEN :dateInput AND :dateInput1 and employee.status = :status  and emp.employmentStatus<>'TERMINATED' and emp.employmentStatus<>'SEPARATED'";
		List<Employee> employeeList = employeeDao.findByDateRange(queryStr, parseDate(startDate), parseDate(endDate));
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	public List<Employee> findAdvanceSearch_DateWithAll(String queryStr, String startDate, String endDate, String attendanceType, String value) {
		List<Employee> employeeList = null;
		// thetmar's edit in 30.6.2016
		if (value.contains(":")) {
			List<String> empNameList = new ArrayList<String>();
			StringTokenizer tokenizer = new StringTokenizer(value, ":");
			while (tokenizer.hasMoreTokens()) {
				empNameList.add(tokenizer.nextToken());
			}
			employeeList = employeeDao.findByAdvanceStringWithDate2(queryStr, parseDate(startDate), parseDate(endDate), AttendanceType.valueOf(attendanceType), empNameList);
		} else {
			employeeList = employeeDao.findByAdvanceStringWithDate(queryStr, parseDate(startDate), parseDate(endDate), AttendanceType.valueOf(attendanceType), exactSearch ? value : "%" + value + "%");
		}
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	public List<Employee> findAdvanceSearch_NoDateWithAll(String queryStr, String attendanceType, String value) {
		List<Employee> employeeList = null;
		// thetmar's edit in 30.6.2016
		if (value.contains(":")) {
			List<String> empNameList = new ArrayList<String>();
			StringTokenizer tokenizer = new StringTokenizer(value, ":");
			while (tokenizer.hasMoreTokens()) {
				empNameList.add(tokenizer.nextToken());
			}
			employeeList = employeeDao.findByAdvanceString2(queryStr, AttendanceType.valueOf(attendanceType), empNameList);
		} else {
			employeeList = employeeDao.findByAdvanceString(queryStr, AttendanceType.valueOf(attendanceType), exactSearch ? value : "%" + value + "%");
		}
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	public List<Employee> findAdvanceSearch_DateOnly(String queryStr, String startDate, String endDate) {
		List<Employee> employeeList = employeeDao.findByDateRange(queryStr, parseDate(startDate), parseDate(endDate));
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	public List<Employee> findAdvanceSearch_DateWithAttendanceType(String queryStr, String startDate, String endDate, String attendanceType) {
		List<Employee> employeeList = employeeDao.findByAdvanceStringWithoutValue(queryStr, parseDate(startDate), parseDate(endDate), AttendanceType.valueOf(attendanceType));
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	public List<Employee> findAdvanceSearch_NoDateWithAttendanceType(String queryStr, String attendanceType) {
		List<Employee> employeeList = employeeDao.findByAdvanceStringWithoutDateAndValue(queryStr, AttendanceType.valueOf(attendanceType));
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	public List<Employee> findAdvanceSearch_NoDateWithSearchValue(String queryStr, String value) {
		List<Employee> employeeList = employeeDao.findByString(queryStr, exactSearch ? value : "%" + value + "%");
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	public List<Employee> findAdvanceSearch_DateWithSearchValue(String queryStr, String startDate, String endDate, String value) {
		List<Employee> employeeList = null;
		// thetmar's edit in 30.6.2016
		if (value.contains(":")) {
			List<String> empNameList = new ArrayList<String>();
			StringTokenizer tokenizer = new StringTokenizer(value, ":");
			while (tokenizer.hasMoreTokens()) {
				empNameList.add(tokenizer.nextToken());
			}
			employeeList = employeeDao.findByAdvanceStringWithDateNoAttendanceType2(queryStr, parseDate(startDate), parseDate(endDate), empNameList);
		} else {
			employeeList = employeeDao.findByAdvanceStringWithDateNoAttendanceType(queryStr, parseDate(startDate), parseDate(endDate), exactSearch ? value : "%" + value + "%");
		}
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	private List<Employee> findAdvanceSearchWithMultipleKeys(Map<String, String> advanceSearchMap, AbstractEntity entity) {
		String queryStr = queryFactory.createAttendanceAdvanceSearchQuery(advanceSearchMap, entity);
		return employeeDao.findByParamMap(queryStr, advanceSearchMap);
	}

	private List<Employee> doFilterByStatus(List<Employee> employeeList, String status) {
		if (status == null)
			return employeeList;
		List<Employee> empList = new ArrayList<Employee>();
		try {
			for (Employee employee : employeeList) {
				if (employee.getEmploymentStatus() == EmploymentStatus.valueOf(status))
					empList.add(employee);
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return empList;
	}

	@Override
	public SearchResponse<Employee> searchEmployee(SearchRequest searchRequest) throws ServiceUnavailableException {
		List<Employee> employeeList = null;
		doExactSearch(searchRequest);

		switch (searchRequest.getKey()) {
		case SEARCH_TERM:
			if (searchRequest.getEntityType() == EntityType.EMPLOYEE) {
				employeeList = findEmployeeInMultipleFields(searchRequest.getUserBoId(), searchRequest.getValue());
				searchRequest.setStatus(null);
			} else if (searchRequest.getEntityType() == EntityType.PERMANENT)
				employeeList = findPermanentInMultipleFields(searchRequest.getUserBoId(), searchRequest.getValue());
			else
				employeeList = findProbationInMultipleFields(searchRequest.getUserBoId(), searchRequest.getValue());
			break;
		case ALL:
			if (searchRequest.getStatus() == null)
				employeeList = getAllEmployees();
			else
				employeeList = getAllEmployees(searchRequest.getStatus());
			break;
		case ID:
			if (searchRequest.getStatus() == null)
				employeeList = findByEmployeeBoId(searchRequest.getValue());
			else
				employeeList = findByEmployeeBoId(searchRequest.getValue(), searchRequest.getStatus());
			break;
		case NAME:
		case FIRST_NAME:
		case LAST_NAME:
			employeeList = findWithFullName(searchRequest.getValue(), searchRequest.getStatus());
			break;
		case EDUCATION:
			employeeList = findByEducation(searchRequest.getValue(), searchRequest.getStatus());
			break;
		case DEPARTMENT:
			employeeList = findByDepartment(searchRequest.getValue(), searchRequest.getStatus());
			break;
		case TITLE:
			employeeList = findByTitle(searchRequest.getValue(), searchRequest.getStatus());
			break;
		case EMAIL:
			employeeList = findByEmail(searchRequest.getValue(), searchRequest.getStatus());
			break;
		case JOINING_YEAR:
			employeeList = findByJoiningYear(Integer.parseInt(searchRequest.getValue()), searchRequest.getStatus());
			break;
		case JOINING_DATE:
			employeeList = findByJoiningDate(searchRequest.getValue(), searchRequest.getStatus());
			break;
		case DATE_RANGE:
			employeeList = findByEntranceDateRange(searchRequest.getStartDate(), searchRequest.getEndDate(), searchRequest.getStatus());
			break;
		case EMPLOYMENT_DATE_RANGE:
			employeeList = findByEmploymentDateRange(searchRequest.getStartDate(), searchRequest.getEndDate(), searchRequest.getStatus());
			break;
		case CITY:
			employeeList = findEmployeeByCity(searchRequest.getValue(), searchRequest.getStatus());
			break;
		case STATE:
			employeeList = findEmployeeByState(searchRequest.getValue(), searchRequest.getStatus());
			break;
		case PHONE:
			employeeList = findByPhone(searchRequest.getValue(), searchRequest.getStatus());
			break;
		case ADDRESS:
			employeeList = findEmployeeByAddress(searchRequest.getValue(), searchRequest.getStatus());
			break;
		case NRC:
			employeeList = findByNrc(searchRequest.getValue(), searchRequest.getStatus());
			break;
		case TYPE:
			employeeList = findByEmploymentType(searchRequest.getValue());
			break;
		case EMPLOYMENT_STATUS:
			employeeList = findByEmploymentStatus(searchRequest.getValue());
			break;
		case LEAVE:
			employeeList = findByLeaveType(searchRequest.getValue());
			break;
		case LEAVE_START_DATE:
			employeeList = findByLeaveStartDate(searchRequest.getValue());
			break;
		case LEAVE_DATE_RANGE:
			employeeList = findByLeaveDateRange(searchRequest.getStartDate(), searchRequest.getEndDate());
			break;
		case RANK:
			employeeList = findByRank(searchRequest.getValue(), searchRequest.getStatus());
			break;
		case BRANCH:
			employeeList = findByBranch(searchRequest.getValue(), searchRequest.getStatus());
			break;
		case SERVICE:
			employeeList = byServiceYear(searchRequest.getValue(), searchRequest.getStatus());
			break;
		case SERVICE_OVER:
			employeeList = findByTotalServiceOver(Integer.parseInt(searchRequest.getValue()), searchRequest.getStatus());
			break;
		case SERVICE_UNDER:
			employeeList = findByTotalServiceUnder(Integer.parseInt(searchRequest.getValue()), searchRequest.getStatus());
			break;
		case SERVICE_RANGE:
			employeeList = findByTotalServiceRange(Integer.parseInt(searchRequest.getValue()), Integer.parseInt(searchRequest.getValue()), searchRequest.getStatus());
			break;
		case DOB:
			employeeList = findByDob(searchRequest.getValue(), searchRequest.getStatus());
			break;
		case DOB_MONTH:
			employeeList = findByDobMonth(Integer.parseInt(searchRequest.getValue()), searchRequest.getStatus());
			break;
		case FINGERPRINT_CODE:
			employeeList = findByFingerprintCode(searchRequest.getValue());
			break;
		case TODAY_ATTENDANCE:
			employeeList = findByTodayAttendance();
			break;
		case TODAY_STATUS:
			employeeList = findTodayAttendanceByAttendanceType(searchRequest.getValue());
			break;
		case ATTENDANCE_TYPE:
			employeeList = findTodayAttendanceByAttendanceType(searchRequest.getValue());
			break;
		case ATTENDANCE_DATE_RANGE:
			employeeList = findAttendanceByDateRange(searchRequest.getStartDate(), searchRequest.getEndDate());
			break;
		case ADVANCE_SEARCH:
			employeeList = findAdvanceSearchWithMultipleKeys(searchRequest.getAdvanceSearchMap(), searchRequest.getEntity());
			break;
		case ADVANCED_SEARCH:
			employeeList = advancedSearch(searchRequest.getAdvanceSearchMap());
			break;
		case ADVANCED_SEARCH_HIERARCHY:
			employeeList = combine_AdvancedSearch(searchRequest);
			break;
		case HIERARCHY:

			if (searchRequest.isHierachySearch())
				employeeList = byHierarchy(searchRequest.getValue());
			else
				employeeList = withoutHierarchy(searchRequest.getValue());

			break;
		case IGNORE_STATUS:
			employeeList = findEmployeeByIdIgnoreStatus(searchRequest.getValue());
			break;
		case ATTENDANCE_ONE_EMP_DETAIL:
			employeeList = findAttendanceInfoforOneEmployee(searchRequest.getAdvanceSearchMap());
			break;
		default:
			employeeList = null;
		}
		if (searchRequest.getKey() == SearchKey.ID)
			return new SearchResponse(employeeList);
		return new SearchResponse(doFilterByStatus(employeeList, searchRequest.getStatus()));
	}

	public List<Employee> findEmployeeByIdIgnoreStatus(String id) throws ServiceUnavailableException {
		List<Employee> employeeList = employeeDao.findByString("from Employee emp where emp.boId = :dataInput and emp.status = :status", id);
		hibernateInitializeEmployeeList(employeeList);
		if (employeeList != null && employeeList.isEmpty())
			return new ArrayList<>();
		return employeeList;
	}

	public List<Employee> advancedSearch(Map<String, String> paramsMap) throws ServiceUnavailableException {

		StringBuffer buffer = new StringBuffer();
		// String departmentName = "";
		if (paramsMap.get("grade") != null) {
			buffer.append("select employee from Employee employee ");
			buffer.append("join employee.applicant.educations edu where edu.grade LIKE :grade");
			// paramsMap.put("grade", "%" + entry.getValue() + "%");
		} else {
			buffer.append("select employee from Employee employee where 1=1 ");
		}
		for (Entry<String, String> entry : paramsMap.entrySet()) {
			String key = entry.getKey();
			if (key.equals("grade"))
				continue;
			else
				buffer.append(" and ");

			if (key.contains("_"))
				buffer.append(getQueryForStartDateEndDateRange("employee", entry));
			else if (key.contains("Range"))
				buffer.append(getQueryForDateRange("employee", entry));
			else if (ParamName.EMPLOYMENT_TYPE == toParamType(key))
				buffer.append("employee." + key + " LIKE :" + key);
			else if (ParamName.EMPLOYMENT_STATUS == toParamType(key)) {
				// buffer.append("employee." + entry.getKey() + " LIKE :" +
				// entry.getKey());
				buffer.append(getEmploymentStatusQuery(entry.getValue(), "employee." + ParamName.EMPLOYMENT_STATUS.getValue()) + " and employee.status = :status");
			} else if (ParamName.COMPENSATIONTYPE == toParamType(key))
				buffer.append("employee." + entry.getKey() + " LIKE :" + key);
			else if (key.equals("employee")) {
				buffer.append(getQueryForName("employee", entry));

			} else if (key.equals("job")) {
				buffer.append(getQueryForJobTitle("employee", entry));
				// paramsMap.put(key, "%" + entry.getValue() + "%");
			} else if (key.equals("city")) {
				// buffer.append(" ( employee.applicant.permanentAddress.city
				// like :city or employee.applicant.contactAddress.city like
				// :city )");
				buffer.append(" ( employee.applicant.contactAddress.city like :city )");
				paramsMap.put("city", "%" + entry.getValue() + "%");

			} else if (key.equals("state")) {
				// buffer.append(" ( employee.applicant.permanentAddress.state
				// like :state or employee.applicant.contactAddress.state like
				// :state )");
				buffer.append(" ( employee.applicant.contactAddress.state like :state )");
				paramsMap.put("state", "%" + entry.getValue() + "%");

			} else if (key.equals("service-year")) {
				buffer.append(queryFactory.buildServiceYear(entry, "employee"));

			} else if (key.equals("religion")) {
				buffer.append(getQueryForReligion("employee", entry));

			} else if (key.equals("entranceDate")) {
				buffer.append(queryFactory.buildEntranceDateRange(entry, "employee"));
			} else if (key.equals("gender")) {
				buffer.append(getQueryForGender("employee", entry));

			} else if (key.equals("age")) {
				buffer.append(queryFactory.buildServiceYear(entry, "employee"));

			} else if (ParamName.MATRIMONIALSTATUS == toParamType(key)) {

				buffer.append(" ( employee.applicant." + key + " like :" + key + " )");

			} else if (entry.getKey().equals("phoneNumber")) {
				// buffer.append(" (
				// (employee.applicant.contactAddress.phoneNumber like
				// :phoneNumber) or
				// (employee.applicant.permanentAddress.phoneNumber like
				// :phoneNumber ))");
				buffer.append(" ( employee.applicant.contactAddress.phoneNumber like :phoneNumber )");
				paramsMap.put("phoneNumber", "%" + entry.getValue() + "%");

			} else if (key.equals("jobBand")) {
				buffer.append(" employee.primaryPosition.job.ban = :jobBand");
				// paramsMap.put("jobBand", Integer.parseInt(entry.getValue()));

			} else if (key.equals("department")) {
				buffer.append(queryFactory.generateQueryByDepartmentName("employee", "department"));

			} else
				buffer.append("employee." + entry.getKey() + " LIKE :" + entry.getKey());
		}
		if (!paramsMap.containsKey(ParamName.EMPLOYMENT_STATUS.getValue()))
			buffer.append(" and employee.status = :status and employee.employmentStatus<>'RESIGNED' and employee.employmentStatus<>'TERMINATED'");
		paramsMap.remove(ParamName.EMPLOYMENT_STATUS.getValue());
		paramsMap.remove("service-year");
		paramsMap.remove("age");
		paramsMap.remove("entranceDate");

		List<Employee> employeeList = employeeDao.findByParamMap(buffer.toString(), paramsMap);
		/*
		 * List<Employee> departmentFilteredList = new ArrayList<Employee>(); if (departmentName != "" && employeeList != null) { for (Employee emp : employeeList) { if (emp.getPrimaryPosition() != null &&
		 * emp.getPrimaryPosition().getJob() != null && emp.getPrimaryPosition().getJob().getDepartment() != null && emp.getPrimaryPosition().getJob().getDepartment().getName().equals( departmentName))
		 * departmentFilteredList.add(emp); } hibernateInitializeEmployeeList(departmentFilteredList); return departmentFilteredList; }
		 */
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	public List<Employee> combine_AdvancedSearch(SearchRequest searchRequest) throws ServiceUnavailableException {
		Map<String, String> paramsMap = searchRequest.getAdvanceSearchMap();
		Department department = null;
		if (!searchRequest.getValue().contains(",")) {
			department = dataService.findByDepartmentBoIdSingle(searchRequest.getValue().toString());
			if (searchRequest.getValue() != null && !searchRequest.isHierachySearch())
				paramsMap.put("department", department.getName());
		}

		StringBuffer buffer = new StringBuffer();
		// String departmentName = "";
		buffer.append("select employee from Employee employee where 1=1 ");

		for (Entry<String, String> entry : paramsMap.entrySet()) {
			buffer.append(" and ");
			String key = entry.getKey();
			if (key.contains("_"))
				buffer.append(getQueryForStartDateEndDateRange("employee", entry));
			else if (key.contains("Range"))
				buffer.append(getQueryForDateRange("employee", entry));
			else if (ParamName.EMPLOYMENT_TYPE == toParamType(key))
				buffer.append("employee." + key + " LIKE :" + key);
			else if (ParamName.EMPLOYMENT_STATUS == toParamType(key)) {
				// buffer.append("employee." + entry.getKey() + " LIKE :" +
				// entry.getKey());
				buffer.append(getEmploymentStatusQuery(entry.getValue(), "employee." + ParamName.EMPLOYMENT_STATUS.getValue()) + " and employee.status = :status");
			} else if (ParamName.COMPENSATIONTYPE == toParamType(key))
				buffer.append("employee." + entry.getKey() + " LIKE :" + key);
			else if (key.equals("employee")) {
				buffer.append(getQueryForName("employee", entry));
			} else if (key.equals("job")) {
				buffer.append(getQueryForJobTitle("employee", entry));
				// paramsMap.put(key, "%" + entry.getValue() + "%");
			} else if (key.equals("city")) {
				// buffer.append(" ( employee.applicant.permanentAddress.city
				// like :city or employee.applicant.contactAddress.city like
				// :city )");
				buffer.append(" ( employee.applicant.contactAddress.city like :city )");
				paramsMap.put("city", "%" + entry.getValue() + "%");

			} else if (key.equals("state")) {
				// buffer.append(" ( employee.applicant.permanentAddress.state
				// like :state or employee.applicant.contactAddress.state like
				// :state )");
				buffer.append(" ( employee.applicant.contactAddress.state like :state )");
				paramsMap.put("state", "%" + entry.getValue() + "%");

			} else if (key.equals("service-year")) {
				buffer.append(queryFactory.buildServiceYear(entry, "employee"));

			} else if (key.equals("religion")) {
				buffer.append(getQueryForReligion("employee", entry));

			} else if (key.equals("entranceDate")) {
				buffer.append(queryFactory.buildEntranceDateRange(entry, "employee"));

				// buffer.append(getQueryForJoinDate("employee", entry));

			} else if (key.equals("gender")) {
				buffer.append(getQueryForGender("employee", entry));

			} else if (key.equals("grade")) {
				buffer = new StringBuffer();
				buffer.append("select employee from Employee employee ");
				buffer.append("join employee.applicant.educations edu where edu.grade LIKE :grade");

			} else if (key.equals("age")) {
				buffer.append(queryFactory.buildServiceYear(entry, "employee"));

			} else if (ParamName.MATRIMONIALSTATUS == toParamType(key)) {

				buffer.append(" ( employee.applicant." + key + " like :" + key + " )");

			} else if (entry.getKey().equals("phoneNumber")) {
				// buffer.append(" (
				// (employee.applicant.contactAddress.phoneNumber like
				// :phoneNumber) or
				// (employee.applicant.permanentAddress.phoneNumber like
				// :phoneNumber ))");
				buffer.append(" ( employee.applicant.contactAddress.phoneNumber like :phoneNumber )");
				paramsMap.put("phoneNumber", "%" + entry.getValue() + "%");

			} else if (key.equals("jobBand")) {
				buffer.append(" employee.primaryPosition.job.ban = :jobBand");
				// paramsMap.put("jobBand", Integer.parseInt(entry.getValue()));

			} else if (key.equals("department")) {
				buffer.append(queryFactory.generateQueryByDepartmentName("employee", "department"));

			} else
				buffer.append("employee." + entry.getKey() + " LIKE :" + entry.getKey());
		}
		if (!paramsMap.containsKey(ParamName.EMPLOYMENT_STATUS.getValue()))
			buffer.append(" and employee.status = :status and employee.employmentStatus<>'RESIGNED' and employee.employmentStatus<>'TERMINATED'");

		paramsMap.remove(ParamName.EMPLOYMENT_STATUS.getValue());
		paramsMap.remove("service-year");
		paramsMap.remove("age");
		paramsMap.remove("entranceDate");

		List<Employee> employeeList = employeeDao.findByParamMap(buffer.toString(), paramsMap);
		/*
		 * List<Employee> departmentFilteredList = new ArrayList<Employee>(); if (departmentName != "" && employeeList != null) { for (Employee emp : employeeList) { if (emp.getPrimaryPosition() != null &&
		 * emp.getPrimaryPosition().getJob() != null && emp.getPrimaryPosition().getJob().getDepartment() != null && emp.getPrimaryPosition().getJob().getDepartment().getName().equals( departmentName))
		 * departmentFilteredList.add(emp); } hibernateInitializeEmployeeList(departmentFilteredList); return departmentFilteredList; }
		 */
		DepartmentService departmentService = (DepartmentService) entityServiceFactory.createService(EntityType.DEPARTMENT, ObjectFullnessLevel.DETAILED);
		List<Department> departmentList = new ArrayList<Department>();

		if (searchRequest.getValue() != null) {
			String[] values = searchRequest.getValue().split(",");
			for (int i = 0; i < values.length; i++)
				departmentList.add(departmentService.findByDepartmentBoIdSingle(values[i]));

			return employeeResultList(employeeList, departmentList);
		}

		if (searchRequest.isHierachySearch()) {
			DepartmentTree tree = new DepartmentTree(departmentService.getAllDepartments());
			Set<Department> departments = tree.getAllDeparmtent(department.getBoId());

			return employeeResultList(employeeList, new ArrayList<Department>(departments));
		}
		return employeeList;
	}

	private List<Employee> employeeResultList(List<Employee> employeeList, List<Department> departmentList) {
		List<Employee> employees = new ArrayList<Employee>();
		for (Employee employee : employeeList) {
			for (Department eachDepartment : departmentList) {

				if (employee.getPrimaryJob().getDepartment().getName().equals(eachDepartment.getName())) {
					employees.add(employee);
				}
			}
		}
		hibernateInitializeEmployeeList(employees);
		return employees;
	}

	private SearchRequest doExactSearch(SearchRequest request) {
		exactSearch = false;
		String searchValue = request.getValue();
		if (searchValue != null)
			if (searchValue.endsWith("=")) {
				exactSearch = true;
				request.setValue(searchValue.substring(0, searchValue.length() - 1));
			}
		return request;
	}

	@Override
	public List<Employee> findByEmployeeName(String name) throws ServiceUnavailableException {
		return findByName(name, null);
	}

	public List<Employee> findByName(String name) throws ServiceUnavailableException {
		return findByName(name, null);
	}

	private List<Employee> findByName(String name, String status) throws ServiceUnavailableException {
		return findWithFullName(name, status);
	}

	@Author(name = "Soe Min Htike")
	public List<Employee> findByPositionId(List<String> positionid) throws ServiceUnavailableException {
		List<Employee> positionList = new ArrayList<Employee>();
		for (String id : positionid) {
			positionList.add(dataService.findByPositionBoId(id).get(0).getEmployee());
		}
		return positionList;
	}

	private List<Employee> byHierarchy(String departmentId) throws ServiceUnavailableException {
		DepartmentService departmentService = (DepartmentService) entityServiceFactory.createService(EntityType.DEPARTMENT, ObjectFullnessLevel.DETAILED);
		DepartmentTree tree = new DepartmentTree(departmentService.getAllDepartments());
		Set<Employee> employees = new HashSet<>();
		for (String id : departmentId.split(",")) {
			if (id.equals(""))
				continue;
			Set<Department> departments = tree.getAllDeparmtent(id);
			for (Department department : departments) {
				employees.addAll(byDepartment(department));
			}
		}

		return new ArrayList<>(employees);
	}

	private List<Employee> withoutHierarchy(String departmentId) throws ServiceUnavailableException {

		Set<Employee> employees = new HashSet<>();
		Set<Department> departments = null;
		for (String id : departmentId.split(",")) {
			if (BizleapUtils.isEmpty(id))
				continue;
			Department department = dataService.findByDepartmentBoIdSingle(id);
			if (department != null)
				employees.addAll(findByDepartment(department.getName()));

		}
		return new ArrayList<>(employees);
	}

	private String addQueryToFilterOutProbation(String original, String status, String align) {
		if (status == null)
			return original;
		if (status != null && status.equals(EmploymentStatus.PROVISIONAL.toString())) {
			original += " and " + align + ".employmentStatus=\'" + EmploymentStatus.PROVISIONAL.value() + "\'";
		} else {
			original += " and " + align + ".employmentStatus<>\'" + EmploymentStatus.PROVISIONAL.value() + "\'";
		}
		return original;
	}

	private String addQueryToFilterStatus(String original, EmploymentStatus status, String align) {
		if (status == null)
			return original;
		original += " and " + align + ".employmentStatus<>\'" + status.value() + "\' ";
		return original;
	}

	private String limitResultOut(String query, int offset, int count) {
		return query += " limit " + count + " offset " + offset + " order bye Employee.name";
	}

	private String getAvailableId(int index) {
		int lastIndex = index <= 0 ? (int) countEmployee() : index;
		lastIndex = getUnreservedEmployeeId(getUnusedEmployeeId(lastIndex));
		String suggestionId = makeBoId(lastIndex);
		if (findByEmployeeIdIgnoreStatus(suggestionId) == null) {
			return suggestionId;
		}
		return getAvailableId(lastIndex);
	}

	private int getUnusedEmployeeId(int index) {
		Employee employee = null;
		do {
			employee = findByEmployeeIdIgnoreStatus(makeBoId(++index));
		} while (employee != null);
		return index;
	}

	private int getUnreservedEmployeeId(int index) {
		try {
			do {
				List<EmploymentApplication> employmentApplicationList = employmentApplicationService.findByApplyBoId(makeBoId(++index));
				if (employmentApplicationList.isEmpty())
					break;

			} while (true);
		} catch (ServiceUnavailableException e) {
			logger.error("Can't check duplicate applied BoId.", e);
		}
		return index;
	}

	private Employee findByEmployeeIdIgnoreStatus(String boId) {
		String queryStr = "from Employee employee where employee.boId = :dataInput";
		return employeeDao.findByEmployeeIdIgnoreStatus(queryStr, boId);
	}

	private String makeBoId(int count) {
		String prefix = employeePrefix.trim().equals("_") ? "" : employeePrefix.trim();
		return makeBoId(prefix, count);
	}

	@Override
	public OperationResponse<Employee> requestEmployeeOperation(OperationRequest<Employee> request) throws ServiceUnavailableException {
		OperationResponse<Employee> response = new OperationResponse<>();
		response.fail();
		switch (request.getOperation()) {
		case AVAILABLE_ID:
			response.setResult(getAvailableId(-1));
			response.success();
			break;
		default:
			throw new ServiceUnavailableException();
		}
		return response;
	}

	private String getEmploymentStatusQuery(String employmentStatus, String prefix) {

		StringBuffer buffer = new StringBuffer();
		if (employmentStatus == null || employmentStatus.trim().isEmpty())
			return "";
		buffer.append(" (");
		for (EmploymentStatus status : EmploymentStatus.parseList(employmentStatus)) {
			buffer.append(" " + prefix + " = '" + status.toString() + "' or ");
		}
		return buffer.substring(0, buffer.length() - 4) + " )";
	}

	private List<Employee> filterByDate(String startDate, String endDate, List<Employee> employeeList) {
		List<Employee> employeeList2 = new ArrayList();
		for (Employee employee : employeeList) {
			try {
				List attend = attendanceService.findAttendanceForOneEmployeeByAttendanceDateRange(employee.getBoId(), DateUtil.parseDate(startDate), DateUtil.parseDate(endDate));
				employee.setAttendanceList(attend);
				employeeList2.add(employee);
			} catch (ServiceUnavailableException ex) {

			}
		}
		return employeeList2;
	}

	private List<Employee> filterByAttendanceType(String attendanceType, List<Employee> employeeList) {
		List<Employee> employeeList2 = new ArrayList();
		for (Employee employee : employeeList) {
			List attend = employee.getAttendanceListbyType(attendanceType);
			employee.setAttendanceList(attend);
			employeeList2.add(employee);
		}
		return employeeList2;
	}

	@Override
	public List<Employee> findAttendanceInfoforOneEmployee(Map searchMap) throws ServiceUnavailableException {
		String id = "";
		if (searchMap.containsKey("employeeId"))
			id = searchMap.get("employeeId").toString();

		String startDate = (String) searchMap.get("startDate");
		String endDate = (String) searchMap.get("endDate");
		String attendanceType = (String) searchMap.get("attendanceType");

		String queryStr = "select emp from Employee emp join emp.attendanceList aList where ";

		if (!BizleapUtils.isEmpty(startDate) && !BizleapUtils.isEmpty(endDate))
			queryStr += " aList.date BETWEEN :dateInput1 AND :dateInput2 and ";

		if (!BizleapUtils.isEmpty(id))
			queryStr += " emp.boId= :dataInput1 and ";

		if (!BizleapUtils.isEmpty(attendanceType)) {
			// queryStr += "aList.attendanceType = :dataInput2 and ";
			StringBuffer queryBuffer = new StringBuffer();
			for (String attendance : attendanceType.split(",")) {
				queryBuffer.append(" OR aList.attendanceType = '" + attendance + "' ");
			}
			queryStr += " (" + queryBuffer.substring(3) + ") AND ";
			// parameters.remove("attendanceType");
		}

		queryStr += " emp.status = :status  and emp.employmentStatus<>'TERMINATED' and emp.employmentStatus<>'SEPARATED'";
		List<Employee> employeeList = employeeDao.findByDateAndString2(queryStr, parseDate(startDate), parseDate(endDate), id, null);
		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	public long getTotalActiveEmployee() {
		return employeeDao.getCount("select count(emp) from Employee emp where emp.employmentStatus='ACTIVE' or emp.employmentStatus='PROVISIONAL' and emp.status='ACTIVE'");
	}

	public long getTotalPermanentEmployee() {
		return employeeDao.getCount("select count(emp) from Employee emp where emp.employmentStatus='ACTIVE' and emp.status='ACTIVE'");
	}

	public long getTotalProbationEmployee() {
		return employeeDao.getCount("select count(emp) from Employee emp where emp.employmentStatus='PROVISIONAL' and emp.status='ACTIVE'");
	}

	public int getDueProbation() {
		Date date = new Date();
		date = DateUtil.addMonth(date, -3);
		String startDate = sqlDateFormat.format(date);
		date = DateUtil.addDay(date, 14);
		String endDate = sqlDateFormat.format(date);
		String query = "select count(emp) from Employee emp where emp.employmentStatus = '" + EmploymentStatus.PROVISIONAL.name() + "' and emp.entranceDate BETWEEN '" + startDate + "' and '" + endDate + "' and emp.status='" + EntityStatus.ACTIVE.name() + "'";
		return (int) employeeDao.getCount(query);
	}

	public long getTotalEmployee(EmploymentStatus status) {
		status = status == null ? EmploymentStatus.ACTIVE : status;
		return employeeDao.getCount("select count(emp) from Employee emp where emp.employmentStatus='" + status.toString() + "' and emp.status='ACTIVE'");
	}

	// @Override
	public List<Employee> findEmployeeInMultipleFields__(String userId, String searchTerm) throws ServiceUnavailableException {

		if (StringUtils.isBlank(searchTerm))
			return null;
		String queryStr = null;
		List<Employee> employeeList = null;

		queryStr = "from Employee employee where " + "(CONCAT(employee.firstName,'',employee.middleName,' ',employee.lastName) LIKE CONCAT('%',:dataInput1,'%') " + "or employee.boId LIKE :dataInput2 " + "or employee.applicant.contactAddress.city LIKE :dataInput3 " + "or employee.primaryPosition.job.department.name LIKE CONCAT('%',:dataInput4,'%') " + ") " + "and (employee.status = :status)";

		employeeList = employeeDao.findByStringMultiple(queryStr, searchTerm, 4);

		hibernateInitializeEmployeeList(employeeList);
		return employeeList;
	}

	@Override
	public List<Employee> findEmployeeInMultipleFields(String userId, String searchTerm) throws ServiceUnavailableException {

		if (StringUtils.isBlank(searchTerm))
			return null;

		if (searchTerm.startsWith(",") || searchTerm.startsWith(">") || searchTerm.startsWith("<"))
			return buildServiceYear(searchTerm, null);

		List<Employee> employeeList = null;

		String queryStr = "from Employee employee where employee.searchTerms LIKE :dataInput and (employee.status = :status)" + " and employee.employmentStatus<>'RESIGNED' and employee.employmentStatus<>'TERMINATED'";
		employeeList = employeeDao.findByString(queryStr, "%" + searchTerm + "%");
		if (CollectionUtils.isNotEmpty(employeeList)) {
			hibernateInitializeEmployeeList(employeeList);
		}
		return employeeList;
	}

	public List<Employee> buildServiceYear(String searchTerm, String status) {
		String queries = searchTerm;
		if (BizleapUtils.isEmpty(queries))
			return null;

		String[] queriesArray = queries.split(";");
		List<Employee> employeeList = null;
		String queryStr;

		StringBuffer buffer = new StringBuffer();
		buffer.append("select employee from Employee employee where 1=1 and ");
		for (String query : queriesArray) {
			buffer.append(queryFactory.build(query, "employee", searchTerm));
		}

		buffer.append(" and employee.status = :status and employee.employmentStatus<>'RESIGNED' and employee.employmentStatus<>'TERMINATED'");

		queryStr = addQueryToFilterOutProbation(buffer.toString(), status, "employee");
		employeeList = employeeDao.findByString(queryStr);

		if (CollectionUtils.isNotEmpty(employeeList)) {
			hibernateInitializeEmployeeList(employeeList);
		}

		return employeeList;

	}

	@Override
	public List<Employee> findPermanentInMultipleFields(String userId, String searchTerm) {

		if (StringUtils.isBlank(searchTerm))
			return null;

		if (searchTerm.startsWith(",") || searchTerm.startsWith(">") || searchTerm.startsWith("<"))
			return buildServiceYear(searchTerm, "ACTIVE");

		List<Employee> employeeList = null;

		String query = "from Employee employee where employee.searchTerms LIKE :dataInput and " + "(employee.status = :status) and employee.employmentStatus<>'RESIGNED' and employee.employmentStatus<>'TERMINATED' and" + " employee.employmentStatus<>'PROVISIONAL'";
		employeeList = employeeDao.findByString(query, "%" + searchTerm + "%");
		if (CollectionUtils.isNotEmpty(employeeList))
			hibernateInitializeEmployeeList(employeeList);
		return employeeList;

	}

	public List<Employee> findProbationInMultipleFields(String userId, String searchTerm) {

		if (StringUtils.isBlank(searchTerm))
			return null;

		if (searchTerm.startsWith(",") || searchTerm.startsWith(">") || searchTerm.startsWith("<"))
			return buildServiceYear(searchTerm, "PROVISIONAL");

		List<Employee> employees = null;
		String query = "from Employee employee where employee.searchTerms LIKE :dataInput and" + " (employee.status = :status) and employee.employmentStatus<>'RESIGNED' and employee.employmentStatus<>'TERMINATED' and" + " employee.employmentStatus='PROVISIONAL'";
		employees = employeeDao.findByString(query, "%" + searchTerm + "%");
		if (CollectionUtils.isNotEmpty(employees))
			hibernateInitializeEmployeeList(employees);

		return employees;
	}

	@Override
	public List<Employee> findEmployeeInFullText(List searchField, String searchTerm) {
		/*
		 * commented out on 07/10/2018 due to tomcat unstability when shutting down while these threads are active
		 * 
		 * @Service
		 *
		 * 
		 * List<Employee> employeeList = employeeDao.findByFullTextSearch(searchField, searchTerm); return employeeList;
		 *******/
		return null;
	}

	public List<WorkAssignment> findWorkAssignmentForEmployees(Employee employee, Date startDate, Date endDate, AssignmentType type) throws ServiceUnavailableException {
		List<WorkAssignment> assignmentList = new ArrayList<WorkAssignment>();
		List<WorkAssignment> workAssignmentList = new ArrayList<WorkAssignment>();

		if (employee != null && startDate == null && endDate == null && type == null) {
			workAssignmentList.addAll(employee.getWorkAssignments());
		} else if (employee != null && startDate != null && endDate != null && type == null) {
			assignmentList = employee.getWorkAssignments();
			if (!CollectionUtils.isEmpty(assignmentList)) {
				for (WorkAssignment assignment : assignmentList) {
					Date start = assignment.getStartDate();
					Date end = assignment.getEndDate();
					if ((start.after(startDate) && end.before(endDate)) || start.equals(startDate) || (end.equals(endDate)))
						workAssignmentList.add(assignment);
				}
			}
		} else if (employee != null && startDate == null && endDate == null && type != null) {
			assignmentList = employee.getWorkAssignments();
			if (!CollectionUtils.isEmpty(assignmentList)) {
				for (WorkAssignment assignment : assignmentList) {
					if (assignment.getAssignmentType().equals(type))
						workAssignmentList.add(assignment);
				}
			}
		}
		return workAssignmentList;

	}

	public long salaryOf(Employee employee, Date date) {
		employee.getLastEmployment();
		return 0;
	}

	@Override
	public List<WorkAssignment> findWorkAssignmentForEmployeeByDateRange(Employee employee, Date startDate, Date endDate) throws ServiceUnavailableException {
		if (startDate == null || endDate == null)
			return new ArrayList<>();
		List<WorkAssignment> assignmentList = employee.getWorkAssignments();
		// Employee>>>>"+employee.getBoId()+"Size<<<<"+assignmentList.size());
		List<WorkAssignment> workAssignmentList = new ArrayList<WorkAssignment>();
		if (!CollectionUtils.isEmpty(assignmentList)) {
			for (WorkAssignment assignment : assignmentList) {
				Date start = assignment.getStartDate();
				Date end = assignment.getEndDate();
				if ((start.before(endDate) && end.after(startDate)) || start.equals(startDate) || (end.equals(endDate)))
					workAssignmentList.add(assignment);
			}
		}
		// workAssignmentService.hibernateInitializeWorkAssignmentList(workAssignmentList);
		// Employee$$$$$$$$$$$$$"+workAssignmentList.size());
		return workAssignmentList;
	}

	@Override
	public void hibernateInitializeForPosition(Employee employee) {
		try {
			dataService.hibernateInitialize(employee.getPrimaryPosition());
			dataService.hibernateInitialize(employee.getSecondaryPosition());

			if (employee.getPrimaryPosition() != null) {
				for (Position position : employee.getPrimaryPosition().getReportByList())
					dataService.hibernateInitialize(position);
				for (Position position : employee.getPrimaryPosition().getReportToList())
					dataService.hibernateInitialize(position);
			}
			if (employee.getSecondaryPosition() != null) {
				for (Position position : employee.getSecondaryPosition().getReportByList())
					dataService.hibernateInitialize(position);
				for (Position position : employee.getSecondaryPosition().getReportToList())
					dataService.hibernateInitialize(position);
			}
		} catch (Exception e) {
			logger.error("Can't initialize position", e);
		}
	}

	/*
	 * Below four fields were read using eager; now they have been been changed to LAZY loading So initialize them here
	 * 
	 * @Date July/10/2018
	 * 
	 * @Author("NT")
	 */
	public void hibernateInitializeEagersFields(Employee employee) throws ServiceUnavailableException {

		// @NT 07/08/18 Added because we are removing eager, also added
		// View.Thin
		// without any object field in the employee

		Hibernate.initialize(employee.getSearchTerms());
		Hibernate.initialize(employee.getName());
		Hibernate.initialize(employee.getAddress());
		Hibernate.initialize(employee.getPermanentAddress());
		Hibernate.initialize(employee.getContactAddress());
		// Hibernate.initialize(employee.getContactAddress().getCity());
		Hibernate.initialize(employee.getPrimaryPosition());
		Hibernate.initialize(employee.getSecondaryPosition());
		Hibernate.initialize(employee.getContactPhoneNumber());
		Hibernate.isInitialized(employee.getPhoneNumber());
		Hibernate.initialize(employee.getEmail());

		positionServiceSummary.initializeCallMonitor();
		positionServiceSummary.hibernateInitialize(employee.getPrimaryPosition());
		positionServiceSummary.cleanUpCallMonitor();

		positionServiceSummary.initializeCallMonitor();
		positionServiceSummary.hibernateInitialize(employee.getSecondaryPosition());
		positionServiceSummary.cleanUpCallMonitor();

	}

	@Override
	public void hibernateInitialize(Employee employee) throws ServiceUnavailableException {
		if (employee == null)
			return;
		Hibernate.initialize(employee.getLeaves());
		Hibernate.initialize(employee.getShift());
		Hibernate.initialize(employee.getShiftGroup());
		Hibernate.initialize(employee.getPromotions());
		Hibernate.initialize(employee.getDemotions());
		Hibernate.initialize(employee.getEmploymentList());
		hibernateInitializeEmployment(employee.getEmploymentList());
		Hibernate.initialize(employee.getAwards());
		Hibernate.initialize(employee.getPunishments());
		Hibernate.initialize(employee.getTrainings());
		Hibernate.initialize(employee.getResignations());
		Hibernate.initialize(employee.getTerminations());
		Hibernate.initialize(employee.getPayList());
		Hibernate.initialize(employee.getIncrements());
		Hibernate.initialize(employee.getWorkAssignments());
		Hibernate.initialize(employee.getDepartment());
		Hibernate.initialize(employee.getClaims());
		Hibernate.initialize(employee.getPrimaryPosition());
		hibernateInitializeEagersFields(employee);

		employeeClaimService.hibernateInitializeEmployeeClaimList(new ArrayList<EmployeeClaim>(employee.getClaims()));
		dataService.hibernateInitializeDepartment(employee.getDepartment());
		dataService.hibernateInitializeShift(employee.getShift());
		dataService.hibernateInitializeShiftGroup(employee.getShiftGroup());
		dataService.hibernateInitalizeAttendanceList(employee.getAttendanceList());
		dataService.hibernateInitializePayList(employee.getPayList());
		dataService.hibernateInitializeAppriasalList(employee.getProvisionalApprisalList());
		dataService.hibernateInitializeAppriasalList(employee.getAnnualApprisalList());
		dataService.hibernateInitializeAppriasalList(employee.getMonthlyApprisalList());
		dataService.hibernateInitializeAppriasalList(employee.getProvisionalApprisalList());
		dataService.hibernateInitializeApplicant(employee.getApplicant());

		Hibernate.initialize(employee.getNote());
		for (Note note : employee.getNote()) {
			Hibernate.initialize(note);
		}
	}

	@Override
	public void hibernateInitializeEmployeeList(List<Employee> employeeList) {
		if (CollectionUtils.isNotEmpty(employeeList))
			for (Employee employee : employeeList) {
				try {
					hibernateInitialize(employee);
				} catch (ServiceUnavailableException e) {
					logger.error("Can't initialize employee", e);
					return;
				}
			}
	}

	@Override
	public void hibernateInitializeEmployees(Set<Employee> employeeList) {
		if (CollectionUtils.isNotEmpty(employeeList))
			for (Employee employee : employeeList) {
				try {
					hibernateInitialize(employee);
				} catch (ServiceUnavailableException e) {
					logger.error("Can't initialize employee", e);
					return;
				}
			}
	}

	@Override
	public void hibernateInitializeEmployeeAsList(Employee employee) {
		if (employee != null) {
			List<Employee> employeeList = new ArrayList<Employee>();
			employeeList.add(employee);
			hibernateInitializeEmployeeList(employeeList);
		}
	}

	protected void hibernateInitializeEmployment(List<Employment> employmentList) throws ServiceUnavailableException {
		if (employmentList != null) {
			for (Employment employment : employmentList) {
				dataService.hibernateInitialize(employment.getJob());
				if (employment.getCompany() == null)
					continue;
				Hibernate.initialize(employment.getCompany());
				if (employment.getCompany().getEmployerAddress() != null)
					Hibernate.initialize(employment.getCompany().getEmployerAddress());
				if (employment.getCompany().getDepartmentList() != null)
					dataService.hibernateInitializeDepartmentList(employment.getCompany().getDepartmentList());
			}
		}
	}

	public List<Employee> byServiceYear(String query, String status) {
		int year = 0;
		int month = 0;
		if (query.contains(">") || query.contains("<") || query.contains("=")) {
			year = Integer.parseInt(query.substring(1)) * -1;
			Date startDate = DateUtil.addYear(new Date(), year);

			if (query.contains(">")) {
				String queryString = "from Employee e where e.entranceDate <= :dateInput and e.status =:status ";
				queryString = addFilter(queryString, status, "e");
				return employeeDao.findByDate(queryString, startDate);
			} else if (query.contains("<")) {
				String queryString = "from Employee e where e.entranceDate > :dateInput and e.status =:status ";
				queryString = addFilter(queryString, status, "e");
				return employeeDao.findByDate(queryString, startDate);
			} else if (query.contains("=")) {
				return searchEqual(query.substring(1), status);
			}

		} else {
			return searchEqual(query, status);
		}
		return new ArrayList<>();
	}

	private List<Employee> searchEqual(String raw, String status) {
		int year = 0;
		int month = 0;
		if (raw.indexOf(".") == -1) {
			year = Integer.parseInt(raw) * -1;
		} else {
			String str[] = raw.split(".");
			year = Integer.parseInt(str[0]) * -1;
			month = Integer.parseInt(str[1]) * -1;
		}
		Date startDate = DateUtil.addYear(new Date(), year);
		startDate = DateUtil.addMonth(startDate, month);
		Date endDate = DateUtil.addYear(startDate, -1);

		String queryString = "from Employee e where e.entranceDate<=:dateInput and e.entranceDate>=:dateInput1 and e.status =:status ";
		queryString = addFilter(queryString, status, "e");
		return employeeDao.findByDateRange(queryString, startDate, endDate);
	}

	private String addFilter(String query, String status, String prefix) {
		if (status == null)
			return String.format("%s and ( %s.employmentStatus='%s' or %s.employmentStatus = '%s') ", query, prefix, EmploymentStatus.ACTIVE.name(), prefix, EmploymentStatus.PROVISIONAL.name());

		EmploymentStatus eStatus = EmploymentStatus.valueOf(status);
		return String.format("%s and %s.employmentStatus='%s' ", query, prefix, eStatus.name());
	}

}

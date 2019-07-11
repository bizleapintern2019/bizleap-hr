package com.bizleap.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizleap.commons.domain.entity.Employee;
import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.JobDao;
import com.bizleap.service.JobService;
import com.bizleap.service.PositionService;

//@Author: Nyan Lin Htet
@Service
// @Transactional(readOnly = true)
public class JobServiceImpl implements JobService {

	@Autowired
	private JobDao jobDao;
	
	@Autowired
	private PositionService positionService;

	public void saveJob(Job job) throws IOException, ServiceUnavailableException {
		jobDao.save(job);
	}

	@Transactional(readOnly = true)
	public List<Job> getAll() throws ServiceUnavailableException {
		List<Job> jobList = jobDao.getAll("from Job");
		if(!CollectionUtils.isEmpty(jobList)) {
			hibernateInitializedList(jobList);
			return jobList;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public  Job findByBoId(String boId) throws ServiceUnavailableException {
		String query = "from Job job where job.boId=:dataInput";
		List<Job> jobList = jobDao.findByString(query, boId);
		if(!CollectionUtils.isEmpty(jobList)) {
			hibernateInitializeJob(jobList.get(0));
			return jobList.get(0);
		}
		return null;
	}

	@Transactional(readOnly = true)
	public List<Job> findByTitle(String title) throws ServiceUnavailableException {
		String query = "from Job job where job.jobTitle=:dataInput";
		List<Job> jobList = jobDao.findByString(query, title);
		if(!CollectionUtils.isEmpty(jobList)) {
			hibernateInitializedList(jobList);
			return jobList;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public List<Job> findBySalary(int salary) throws ServiceUnavailableException {
		String query = "from Job job where job.salary=:dataInput";
		List<Job> jobList = jobDao.findByInteger(query, salary);
		if(!CollectionUtils.isEmpty(jobList)) {
			hibernateInitializedList(jobList);
			return jobList;
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public Job findJobByPositionBoId(String positionBoId) throws ServiceUnavailableException {
		Position position = positionService.findByBoId(positionBoId);
		String query = "select job from Job job inner join job.positionList position where position.id=:dataInput";
		List<Job> jobList = jobDao.findByLong(query, position.getId());
		if(!CollectionUtils.isEmpty(jobList)){
			hibernateInitializedList(jobList);
			return jobList.get(0);
		}
		return null;
	}
	
	public void hibernateInitializedJob(Job job) {
		Hibernate.initialize(job);
		positionService.hibernateInitializedList(job.getPositionList());
	}

	
	public void hibernateInitializeJob(Job job){
		Hibernate.initialize(job);
	/*//	for (Position position : job.getPositionList()) {
			Hibernate.initialize(position.getReportToList());
			Hibernate.initialize(position.getReportByList());
		//}
*/		
		positionService.hibernateInitializedList(job.getPositionList());
	}
	
	public void hibernateInitializedList(List<Job> jobList) {
		for(Job job : jobList) {
			hibernateInitializedJob(job);
		}
	}
}
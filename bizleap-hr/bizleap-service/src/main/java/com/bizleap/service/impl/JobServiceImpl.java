package com.bizleap.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.entity.Position;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.JobDao;
import com.bizleap.service.JobService;

//@Author: Nyan Lin Htet
@Service
// @Transactional(readOnly = true)
public class JobServiceImpl implements JobService {

	@Autowired
	private JobDao jobDao;

	public void saveJob(Job job) throws IOException, ServiceUnavailableException {
		jobDao.save(job);
	}

	@Transactional(readOnly = true)
	public List<Job> getAll() throws ServiceUnavailableException {
		List<Job> jobList = jobDao.getAll("from Job job");
		
		if(!CollectionUtils.isEmpty(jobList)) {
			hibernateInitializedList(jobList);
			return jobList;
		}
		return null;
	}

	@Transactional(readOnly = true)
	public List<Job> findByBoId(String boId) throws ServiceUnavailableException {
		String query = "from Job job where job.boId=:dataInput";
		List<Job> jobList = jobDao.findByString(query, boId);
		if(!CollectionUtils.isEmpty(jobList)) {
			hibernateInitializedList(jobList);
			return jobList;
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

	public void hibernateInitializedList(List<Job> jobList) {
		for(Job job : jobList) {
			Hibernate.initialize(job.getPositionList());
			for(Position position : job.getPositionList()) {
				Hibernate.initialize(position.getReportToList());
				Hibernate.initialize(position.getReportByList());
			}
		}
	}
}
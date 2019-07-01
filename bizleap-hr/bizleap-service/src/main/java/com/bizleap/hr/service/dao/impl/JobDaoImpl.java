package com.bizleap.hr.service.dao.impl;

import org.springframework.stereotype.Repository;

import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.hr.service.dao.JobDao;

//@Author: Nyan Lin Htet
@Repository
public class JobDaoImpl extends AbstractDaoImpl<Job, String> implements JobDao {
	
    protected JobDaoImpl() {
        super(Job.class);
    }

    @Override
    public void save(Job job) throws ServiceUnavailableException {
        saveOrUpdate(job);
    }
}
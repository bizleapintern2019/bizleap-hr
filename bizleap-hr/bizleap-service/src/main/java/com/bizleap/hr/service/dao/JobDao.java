package com.bizleap.hr.service.dao;

import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface JobDao extends AbstractDao<Job, String> {
    public void save(Job job) throws ServiceUnavailableException;
}
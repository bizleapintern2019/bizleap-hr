package com.bizleap.service;

import java.io.IOException;
import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface JobService {
	void saveJob(Job job) throws IOException, ServiceUnavailableException;
}

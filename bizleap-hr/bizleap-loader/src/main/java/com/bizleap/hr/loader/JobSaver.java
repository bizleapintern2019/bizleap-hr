package com.bizleap.hr.loader;

import java.io.IOException;
import java.util.List;

import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;

public interface JobSaver {
	void savePass1() throws ServiceUnavailableException, IOException;
	void setJobList(List<Job> jobList);
}
package com.bizleap.hr.loader.impl;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Job;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.commons.domain.utils.Printer;
import com.bizleap.hr.loader.JobSaver;
import com.bizleap.service.JobService;

@Service
public class JobSaverImpl implements JobSaver{
	private static Logger logger = Logger.getLogger(CompanySaverImpl.class);
    private static Printer printer = new Printer(logger);

    @Autowired
    JobService jobService;

    List<Job> jobList;

    
    public void savePass1() throws ServiceUnavailableException, IOException {
        printer.line("Saving Company: "+ getJobList().size());
        for(Job job:getJobList()) {
            jobService.saveJob(job);
        }
        printer.line("Saving Completed");
    }

    public void setJobList(List<Job> jobList) {
        this.jobList=jobList;
    }

    public List<Job> getJobList() {
        return this.jobList;
    }
}

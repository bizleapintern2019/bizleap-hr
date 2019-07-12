package com.bizleap.service;
import java.util.List;

import com.bizleap.commons.domain.entity.AbstractEntity;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;;

public interface DataService {
	public List<AbstractEntity> getAllEntity() throws ServiceUnavailableException;
}

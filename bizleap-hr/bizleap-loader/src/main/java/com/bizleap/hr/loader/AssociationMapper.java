package com.bizleap.hr.loader;

import java.util.Map;

import com.bizleap.commons.domain.entity.Error;

public interface AssociationMapper {
	
	public void setUpAssociations();
	
	public Map<Integer, Error> getErrorMap();
}

package com.bizleap.hr.loader;

import java.util.HashMap;
import java.util.Map;

import com.bizleap.commons.domain.entity.Error;

public interface AssociationMapper {
	public void setUpAssociations();

	public void handleLinkedError(int lineNumber,String message, Object source);
	
	public void setErrorHashMap(HashMap<Integer, Error> errorHashMap);
	
	public Map<Integer, Error> getErrorHashMap();
}

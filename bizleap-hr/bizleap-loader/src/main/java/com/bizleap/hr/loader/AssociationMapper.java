package com.bizleap.hr.loader;


import java.util.Map;

import com.bizleap.commons.domain.entity.Error;

public interface AssociationMapper {
	public void setUpAssociations();
	public void handleLinkedError(int lineNumber,String message,Object source);
	public Map<Integer, Error> getErrorHashMap();
	public void setErrorHashMap(Map<Integer, Error> errorHashMap);
}

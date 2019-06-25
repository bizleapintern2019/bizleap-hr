package com.bizleap.hr.loader;


import java.util.Map;
import com.bizleap.commons.domain.entity.Error;

public interface AssociationMapper {
	public void setUpAssociations();

	public void handleLinkageError(int lineNumber,String message, Object source);
	
	public void setErrorMap(Map<Integer, Error> errorHashMap);
	
	public Map<Integer, Error> getErrorMap();
}

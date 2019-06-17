package com.bizleap.hr.loader;

import java.util.HashMap;
import com.bizleap.commons.domain.entity.Error;

public interface AssociationMapper {
	public void setUpAssociations();
	public void handleLinkedError(int lineNumber,String message,Object source);
	public HashMap<Integer, Error> getErrorHashMap();
	public void setErrorHashMap(HashMap<Integer, Error> errorHashMap);
}

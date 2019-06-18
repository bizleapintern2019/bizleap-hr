package com.bizleap.hr.loader;

import java.util.Map;

import com.bizleap.commons.domain.entity.ErrorCollection;

public interface AssociationMapper {
	public void setUpAssociations();
	//public void handleLinkedError(String message,Object source);
	public Map<Integer, ErrorCollection> getErrorHashMap();
}

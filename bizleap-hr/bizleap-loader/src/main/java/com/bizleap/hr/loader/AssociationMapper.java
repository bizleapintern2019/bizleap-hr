package com.bizleap.hr.loader;

import java.util.HashMap;

import com.bizleap.commons.domain.entity.ErrorCollection;
public interface AssociationMapper {
	public void setUpAssociations();
	public void handleLinkedError(int lineNumber,String message,Object source);
	public HashMap<Integer,ErrorCollection> getHashMap();
	public void setHasnMap(HashMap<Integer,ErrorCollection> errorHashMap);
}

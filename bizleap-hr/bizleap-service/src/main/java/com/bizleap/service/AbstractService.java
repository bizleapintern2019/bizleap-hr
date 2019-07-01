package com.bizleap.service;

//import com.bizleap.commons.domain.enums.EntityType;

public interface AbstractService {
	
	long getCount();
//	String getNextBoId(EntityType entityType);
	String makeBoId(String prefix,int currentObjCount);
//	String makeBoId(EntityType entityType, int currentObjectCount);

}

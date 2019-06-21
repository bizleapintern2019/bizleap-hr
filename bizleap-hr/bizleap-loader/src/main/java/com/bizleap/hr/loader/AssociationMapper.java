package com.bizleap.hr.loader;

import java.util.HashMap;

import com.bizleap.commons.domain.entity.Error;

public interface AssociationMapper {
	public void setUpAssociations();
	public HashMap<Integer, Error> getErrorMap();
}

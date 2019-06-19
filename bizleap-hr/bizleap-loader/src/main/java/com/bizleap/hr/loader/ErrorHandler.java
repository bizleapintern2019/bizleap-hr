package com.bizleap.hr.loader;

import java.util.Map;

import com.bizleap.commons.domain.entity.Error;

public interface ErrorHandler {
	
	public Map<Integer, Error> getErrorMap();
	
	public void handleLoadingError(int index, int lineNumber, String message, Object source);
	
	public void handleLinkedError(int index, String message, Object source);
	
	public boolean hasError();
}

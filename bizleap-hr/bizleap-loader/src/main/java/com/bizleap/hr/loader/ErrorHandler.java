package com.bizleap.hr.loader;

import java.util.Map;

import com.bizleap.commons.domain.entity.LoadingError;

public interface ErrorHandler {
	public void handleLoadingError(int index,int lineNumber, String message, Object source);
	public void handleLinkageError(int index,String message, Object source);
	public Map<Integer, LoadingError> getErrorMap();
	public boolean hasError();
}
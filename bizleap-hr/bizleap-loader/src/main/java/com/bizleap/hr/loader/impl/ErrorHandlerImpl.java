package com.bizleap.hr.loader.impl;

import java.util.HashMap;
import java.util.Map;

import com.bizleap.commons.domain.entity.Error;
import com.bizleap.hr.loader.ErrorHandler;

public class ErrorHandlerImpl implements ErrorHandler {

	public Map<Integer, Error> errorMap = new HashMap<Integer, Error>();

	public Map<Integer, Error> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(Map<Integer, Error> errorMap) {
		this.errorMap = errorMap;
	
	}
	
	public boolean hasError() {
		return getErrorMap() != null && !getErrorMap().isEmpty();
	}

	public void handleLoadingError(int index, int lineNumber, String message, Object source) {
		
		Error error = new Error(lineNumber, source, message);

		if(errorMap == null) {
			errorMap = new HashMap<Integer, Error>();
		}
		errorMap.put(index, error);
	}

	public void handleLinkedError(int index, String message, Object source) {

		Error error = new Error(source,message);

		if(errorMap == null) {
			errorMap = new HashMap<Integer, Error>();
		}
		errorMap.put(index, error);
	}
}
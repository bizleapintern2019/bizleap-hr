package com.bizleap.hr.loader.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Error;
import com.bizleap.hr.loader.ErrorHandler;

@Service
public class ErrorHandlerImpl implements ErrorHandler {
	
	public Map<Integer,Error> errorMap;
	
	public ErrorHandlerImpl() {}
	
	public Map<Integer, Error> getErrorMap() {
		return errorMap;
	}
	
	public void setErrorHashMap(Map<Integer, Error> errorMap) {
		this.errorMap = errorMap;
	}

	@Override
	public void handleLoadingError(int index, int lineNumber, String message, Object source) {
		Error error= new Error(lineNumber,source,message);
		if(errorMap == null){
			errorMap = new HashMap<Integer, Error>();
		}
		errorMap.put(index,error);
	}

	@Override
	public void handleLinkedError(int index, String message, Object source) {
		Error error= new Error(source,message);
		if(errorMap == null){
			errorMap = new HashMap<Integer, Error>();
		}
		errorMap.put(index,error);
	}

	@Override
	public boolean hasError() {
		return errorMap != null && !errorMap.isEmpty();
	}
}

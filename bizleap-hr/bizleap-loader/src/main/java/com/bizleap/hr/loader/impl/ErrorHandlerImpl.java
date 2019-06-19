package com.bizleap.hr.loader.impl;

import java.util.HashMap;
import java.util.Map;

import com.bizleap.commons.domain.entity.LoadingError;
import com.bizleap.hr.loader.ErrorHandler;

public class ErrorHandlerImpl implements ErrorHandler {

	public Map<Integer,LoadingError> errorMap;

	public ErrorHandlerImpl() {
		
	}
	
	public Map<Integer, LoadingError> getErrorMap() {
		return errorMap;
	}
	
	public void setErrorMap(Map<Integer, LoadingError> errorMap) {
		this.errorMap = errorMap;
	}
	
	public void handleLoadingError(int index, int lineNumber, String message, Object source) {
		System.out.println("Index in Loading Error" + index);
		LoadingError error = new LoadingError(index, source, message);
		if(errorMap == null) {
			errorMap = new HashMap<Integer, LoadingError>();
		}
		errorMap.put(index,error);
	}
	
	public void handleLinkedError(int index, String message, Object source) {
		System.out.println("Index in Linked Error" + index);
		LoadingError error = new LoadingError(source, message);
		if(errorMap == null) {
			errorMap = new HashMap<Integer, LoadingError>();
		}
		errorMap.put(index,error);
	}

	@Override
	public boolean hasError() {
		return errorMap!= null && !errorMap.isEmpty();
	}
}
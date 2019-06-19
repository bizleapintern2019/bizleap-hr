package com.bizleap.hr.loader.impl;
import java.util.HashMap;
import java.util.Map;

import com.bizleap.commons.domain.entity.Error;
import com.bizleap.hr.loader.ErrorCollector;

public class ErrorCollectorImpl implements ErrorCollector{
	public Map<Integer,Error> errorMap;

	public ErrorCollectorImpl() {
		
	}
	
	public Map<Integer, Error> getErrorMap() {
		return errorMap;
	}
	
	public void setErrorMap(Map<Integer, Error> errorMap) {
		this.errorMap = errorMap;
	}

	
public void handleLoadingError(int indexNumber, int lineNumber, String message, Object source) {
	System.out.println("Index in Loading Error"+indexNumber);
	Error error= new Error(indexNumber,source,message);
	if(errorMap == null){
		errorMap = new HashMap<Integer, Error>();
	}
	errorMap.put(indexNumber,error);
}

public void handleLinkedError(int indexNumber, String message, Object source) {
	System.out.println("Index in Linked Error"+indexNumber);
	Error error= new Error(source,message);
	if(errorMap == null){
		errorMap = new HashMap<Integer, Error>();
	}
	errorMap.put(indexNumber,error);
}

@Override
public boolean hasError() {
	return errorMap != null && !errorMap.isEmpty();
}

}

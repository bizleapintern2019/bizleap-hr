package com.bizleap.hr.loader.impl;

import java.util.HashMap;
import java.util.Map;

import com.bizleap.commons.domain.entity.ErrorCollection;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorCollector;

public class ErrorCollectorImpl implements ErrorCollector{
public Map<Integer,ErrorCollection> errorMap;

	public ErrorCollectorImpl() {
		
	}
	
	public Map<Integer, ErrorCollection> getErrorHashMap() {
		return errorMap;
	}
	
	public void setErrorHashMap(Map<Integer, ErrorCollection> errorHashMap) {
		this.errorMap = errorHashMap;
	}

	
public void handleLoadingError(int indexNumber, int lineNumber, String message, Object source) {
	System.out.println("Index in Loading Error"+indexNumber);
	ErrorCollection error= new ErrorCollection(indexNumber,source,message);
	if(errorMap == null){
		errorMap = new HashMap<Integer, ErrorCollection>();
	}
	errorMap.put(indexNumber,error);
}

public void handleLinkedError(int indexNumber, String message, Object source) {
	System.out.println("Index in Linked Error"+indexNumber);
	ErrorCollection error= new ErrorCollection(source,message);
	if(errorMap == null){
		errorMap = new HashMap<Integer, ErrorCollection>();
	}
	errorMap.put(indexNumber,error);
}
}

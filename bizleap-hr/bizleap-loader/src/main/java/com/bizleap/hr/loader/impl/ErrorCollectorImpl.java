package com.bizleap.hr.loader.impl;

import java.util.HashMap;
import java.util.Map;

import com.bizleap.commons.domain.entity.ErrorCollection;
import com.bizleap.hr.loader.DataManager;
import com.bizleap.hr.loader.ErrorCollector;

public class ErrorCollectorImpl implements ErrorCollector{
public static Map<Integer,ErrorCollection> errorMap;
DataManager dataManager;

public Map<Integer, ErrorCollection> getErrorHashMap() {
	return errorMap;
}

public void setErrorHashMap(Map<Integer, ErrorCollection> errorHashMap) {
	this.errorMap = errorHashMap;
}

public ErrorCollectorImpl(DataManager dataManager) {
	this.dataManager=dataManager;
}
	
public void handleLoadingError(int index,int lineNumber, String message, Object source) {
		
		System.out.println("Index in Loading Error"+index);
		ErrorCollection error= new ErrorCollection(lineNumber,source,message);
		//System.out.println("Error is"+error+"");
		if(errorMap == null){
			errorMap = new HashMap<Integer, ErrorCollection>();
		}
		errorMap.put(index,error);
		setErrorHashMap(errorMap);
		//System.out.println("in Error hash Map"+errorHashMap);
		
	}

	public void handleLinkedError(int index,String message, Object source) {
		
		System.out.println("Index in Linked Error"+index);
		
		ErrorCollection error= new ErrorCollection(source,message);
		if(errorMap == null){
			errorMap = new HashMap<Integer, ErrorCollection>();
		}
		
		errorMap.put(index,error);
		setErrorHashMap(errorMap);
		//setErrorHashMap(errorHashMap);
	}

	
}

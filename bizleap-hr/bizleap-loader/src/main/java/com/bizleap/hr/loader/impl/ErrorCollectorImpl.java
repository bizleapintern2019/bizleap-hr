package com.bizleap.hr.loader.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.ErrorCollection;
import com.bizleap.hr.loader.ErrorCollector;

@Service
public class ErrorCollectorImpl implements ErrorCollector{
	public Map<Integer,ErrorCollection> errorMap;
	Logger logger = Logger.getLogger(ErrorCollectorImpl.class);

	public ErrorCollectorImpl() {
		
	}
	
	public Map<Integer, ErrorCollection> getErrorHashMap() {
		return errorMap;
	}
	
	public void setErrorHashMap(Map<Integer, ErrorCollection> errorHashMap) {
		this.errorMap = errorHashMap;
	}

	public void handleLoadingError(int indexNumber, int lineNumber, String message, Object source) {
		logger.info("Index in Loading Error"+indexNumber);
		ErrorCollection error= new ErrorCollection(indexNumber,source,message);
		if(errorMap == null){
			errorMap = new HashMap<Integer, ErrorCollection>();
		}
		errorMap.put(indexNumber,error);
	}

	public void handleLinkageError(int indexNumber, String message, Object source) {
		logger.info("Index in Linked Error"+indexNumber);
		ErrorCollection error= new ErrorCollection(source,message);
		if(errorMap == null){
			errorMap = new HashMap<Integer, ErrorCollection>();
		}
		errorMap.put(indexNumber,error);
	}
	
	public boolean hasError() {
		return errorMap != null && !errorMap.isEmpty();
	}
}

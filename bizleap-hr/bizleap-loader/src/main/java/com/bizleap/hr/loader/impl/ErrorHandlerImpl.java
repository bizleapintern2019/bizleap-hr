package com.bizleap.hr.loader.impl;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Error;
import com.bizleap.hr.loader.ErrorHandler;

@Service
public class ErrorHandlerImpl implements ErrorHandler {

	public Map<Integer,Error> errorMap;

	public Map<Integer, Error> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(Map<Integer, Error> errorMap) {
		this.errorMap = errorMap;
	}

	public void handleLoadingError(int indexNumber, int lineNumber, String message, Object source) {

		System.out.println("Loading Error when reading files at: "+indexNumber);
		Error error= new Error(indexNumber,source,message);
		if(errorMap == null){
			errorMap = new HashMap<Integer, Error>();
		}
		errorMap.put(indexNumber,error);
	}

	public void handleLinkageError(int indexNumber, String message, Object source) {

		System.out.println("Error to Link between Company and Employee at: "+indexNumber);
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

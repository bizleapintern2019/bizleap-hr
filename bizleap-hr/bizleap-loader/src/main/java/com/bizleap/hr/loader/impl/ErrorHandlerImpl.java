package com.bizleap.hr.loader.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Error;
import com.bizleap.hr.loader.ErrorHandler;

@Service
public class ErrorHandlerImpl implements ErrorHandler {

	public Map<Integer, Error> errorMap = new HashMap<Integer, Error>();

	public Map<Integer, Error> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(Map<Integer, Error> errorMap) {
		this.errorMap = errorMap;
	}

	private void insertError(int index, Error error) {

		if(errorMap == null) {
			errorMap = new HashMap<Integer, Error>();
		}
		errorMap.put(index, error);
	}

	public void handleLoadingError(int index, int lineNumber, String message, Object source) {
		insertError(index, new Error(lineNumber, source, message));
	}

	public void handleLinkedError(int index, String message, Object source) {
		insertError(index, new Error(source,message));
	}
}
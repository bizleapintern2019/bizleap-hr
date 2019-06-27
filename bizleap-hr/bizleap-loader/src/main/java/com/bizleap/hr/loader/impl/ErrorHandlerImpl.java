package com.bizleap.hr.loader.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Error;
import com.bizleap.hr.loader.ErrorHandler;

@Service
public class ErrorHandlerImpl implements ErrorHandler {
	public Map<Integer, Error> errorMap;
	private int index = 0;

	public ErrorHandlerImpl() {

	}

	public Map<Integer, Error> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(Map<Integer, Error> errorMap) {
		this.errorMap = errorMap;
	}

	public void handleLoadingError(int lineNumber, String message, Object source) {
		Error error = new Error(lineNumber, source, message);
		if (errorMap == null) {
			errorMap = new HashMap<Integer, Error>();
		}
		errorMap.put(++index, error);
	}

	public void handleLinkageError(String message, Object source) {
		Error error = new Error(source, message);
		if (errorMap == null) {
			errorMap = new HashMap<Integer, Error>();
		}
		errorMap.put(++index, error);
	}

	public boolean hasError() {
		return errorMap != null && !errorMap.isEmpty();
	}
}
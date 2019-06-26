package com.bizleap.hr.loader.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Error;
import com.bizleap.hr.loader.ErrorHandler;

@Service
public class ErrorHandlerImpl implements ErrorHandler {
	public Map<Integer, Error> errorMap;
	Logger logger = Logger.getLogger(ErrorHandlerImpl.class);

	public ErrorHandlerImpl() {

	}

	public Map<Integer, Error> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(Map<Integer, Error> errorMap) {
		this.errorMap = errorMap;
	}

	public void handleLoadingError(int indexNumber, int lineNumber, String message, Object source) {
		logger.info("Index in Loading Error" + indexNumber);
		Error error = new Error(indexNumber, source, message);
		if (errorMap == null) {
			errorMap = new HashMap<Integer, Error>();
		}
		errorMap.put(indexNumber, error);
	}

	public void handleLinkageError(int indexNumber, String message, Object source) {
		logger.info("Index in Linked Error" + indexNumber);
		Error error = new Error(source, message);
		if (errorMap == null) {
			errorMap = new HashMap<Integer, Error>();
		}
		errorMap.put(indexNumber, error);
	}

	public boolean hasError() {
		return errorMap != null && !errorMap.isEmpty();
	}
}
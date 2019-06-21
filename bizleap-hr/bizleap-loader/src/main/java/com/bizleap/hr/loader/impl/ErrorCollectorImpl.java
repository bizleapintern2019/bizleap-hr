package com.bizleap.hr.loader.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Error;
import com.bizleap.hr.loader.ErrorCollector;

@Service
public class ErrorCollectorImpl implements ErrorCollector {

	private Map<Integer, Error> errorMap;

	public Map<Integer, Error> getErrorHashMap() {
		return errorMap;
	}

	public void setErrorHashMap(Map<Integer, Error> errorHashMap) {
		this.errorMap = errorHashMap;
	}

	public void insertError(int index, Error error) {
		if (errorMap == null) {
			errorMap = new HashMap<Integer, Error>();
		}
		errorMap.put(index, error);
	}

	public void handleLoadingError(int index, int lineNumber, String message, Object source) {
		insertError(index, new Error(lineNumber, source, message));
	}

	public void handleLinkedError(int index, String message, Object source) {
		insertError(index, new Error(source, message));
	}

	public boolean hasError() {
		return errorMap != null && !errorMap.isEmpty();
	}
}

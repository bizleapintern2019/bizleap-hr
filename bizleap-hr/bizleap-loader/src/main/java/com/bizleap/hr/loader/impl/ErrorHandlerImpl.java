package com.bizleap.hr.loader.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.bizleap.commons.domain.entity.LoadingError;
import com.bizleap.hr.loader.ErrorHandler;

@Service
public class ErrorHandlerImpl implements ErrorHandler {
	private Map<Integer,LoadingError> errorMap;

	public ErrorHandlerImpl() {
		
	}
	
	public Map<Integer, LoadingError> getErrorMap() {
		return errorMap;
	}
	
	public void setErrorMap(Map<Integer, LoadingError> errorMap) {
		this.errorMap = errorMap;
	}
	
	private void insertError(int index, LoadingError error) {
		if(errorMap == null) {
			errorMap = new HashMap<Integer, LoadingError>();
		}
		errorMap.put(index,error);
	}
	
	public void handleLoadingError(int index, int lineNumber, String message, Object source) {
		insertError(index, new LoadingError(lineNumber, source, message));
	}
	
	public void handleLinkageError(int index, String message, Object source) {
		insertError(index, new LoadingError(source, message));
	}

	@Override
	public boolean hasError() {
		return errorMap!= null && !errorMap.isEmpty();
	}
}
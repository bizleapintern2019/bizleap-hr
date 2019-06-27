package com.bizleap.hr.loader;

import java.util.Map;

import com.bizleap.commons.domain.entity.Error;

public interface ErrorHandler {
	void handleLoadingError(int lineNumber, String message, Object source);
	void handleLinkageError(String message, Object source);
	Map<Integer,Error> getErrorMap();
	boolean hasError();
}

package com.bizleap.hr.loader;

import java.util.Map;
import com.bizleap.commons.domain.entity.Error;
public interface ErrorCollector {
	public void handleLoadingError(int index,int lineNumber, String message, Object source);
	public void handleLinkedError(int index,String message, Object source);
	public Map<Integer,Error> getErrorMap();
	public boolean hasError();
}

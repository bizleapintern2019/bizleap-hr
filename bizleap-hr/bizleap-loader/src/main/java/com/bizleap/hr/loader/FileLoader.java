package com.bizleap.hr.loader;

public interface FileLoader {
	public boolean hasError();
	
	public void setHasError(boolean hasError);
	
	public void start(String fileReader)throws Exception;

	public boolean hasNext()throws Exception;

	public String getLine();

	public void stop()throws Exception;
	
	public int getLineNumber();
}

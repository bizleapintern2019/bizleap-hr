package com.bizleap.hr.loader;

public interface FileLoader {
	
	public void start(String fileReader)throws Exception;

	public boolean hasNext()throws Exception;

	public String getLine();

	public void stop()throws Exception;
	
	public int getLineNumber();
}

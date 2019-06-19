package com.bizleap.hr.loader;

public interface FileLoader {
	public void start(String filePath) throws Exception;
	public void finish() throws Exception;
	public boolean hasNext() throws Exception;
	public String getLine();
	public int getLineNumber();
}

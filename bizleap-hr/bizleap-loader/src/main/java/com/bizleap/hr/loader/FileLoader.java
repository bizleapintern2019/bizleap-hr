package com.bizleap.hr.loader;

public interface FileLoader {
	void start(String filePath) throws Exception;
	void finish() throws Exception;
	String getLine() throws Exception;
	boolean hasMore() throws Exception;
	int getLineNumber();
}
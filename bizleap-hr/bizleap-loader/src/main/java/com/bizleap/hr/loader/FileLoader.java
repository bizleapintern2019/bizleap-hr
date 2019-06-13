package com.bizleap.hr.loader;

public interface FileLoader {
	void start(String fileName);
	void finish();
	String getLine();
	boolean hasNext();
}

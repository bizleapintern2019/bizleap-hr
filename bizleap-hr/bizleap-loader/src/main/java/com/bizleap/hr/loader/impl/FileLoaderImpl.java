package com.bizleap.hr.loader.impl;

import com.bizleap.hr.loader.FileLoader;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileLoaderImpl implements FileLoader {

	private BufferedReader dataReader;
	private String dataLine = "";
	private int lineNumber;
	
	public FileLoaderImpl() {	
	}
	
	public void start(String filePath) throws Exception {
		lineNumber=0;
		dataReader = new BufferedReader(new FileReader(filePath));
	}

	public void finish() throws Exception {
		if(dataReader!= null)
			dataReader.close();
	}

	public boolean hasMore() throws Exception {
		lineNumber++;
		return (dataLine=dataReader.readLine())!=null;
	}
	
	public String getLine() throws Exception {
		return dataLine;
	}

	public int getLineNumber() {
		return lineNumber;
	}

}
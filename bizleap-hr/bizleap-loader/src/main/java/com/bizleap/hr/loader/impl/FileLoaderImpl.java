package com.bizleap.hr.loader.impl;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bizleap.hr.loader.FileLoader;

@Service
public class FileLoaderImpl implements FileLoader {
	
	private BufferedReader dataReader;
	private String dataLine = "";
	private int lineNumber;
	private Logger logger = Logger.getLogger(FileLoaderImpl.class);
	
	public FileLoaderImpl() {	
	}
	
	public void start(String filePath) throws Exception {
		lineNumber = 0;
		dataReader = new BufferedReader(new FileReader(filePath));
	}

	public void finish() throws Exception {
		if(dataReader!= null)
			dataReader.close();
	}

	public boolean hasMore() throws Exception {
		if((dataLine=dataReader.readLine())!=null){
			if(dataLine.startsWith("#")) {
				 dataLine=dataReader.readLine();
				 return true;
			}
			lineNumber++;
			return true;
		}
		return false;
	}
	
	public String getLine() {
		logger.info("Processing: "+dataLine);
		return dataLine;
	}

	public int getLineNumber() {
		return lineNumber;
	}
}
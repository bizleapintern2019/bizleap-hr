package com.bizleap.hr.loader.impl;

import com.bizleap.hr.loader.FileLoader;


import java.io.BufferedReader;
import java.io.FileReader;

public class FileLoaderImpl implements FileLoader {
	private String line=null;
	private int count;
	private BufferedReader bufferedReader;

	public void start(String filePath) throws Exception {
		count =0;
		bufferedReader = new BufferedReader(new FileReader(filePath));
		System.out.println("Data is ready to be read.");
	}

	public void finish() throws Exception {
		if(bufferedReader != null)
			bufferedReader.close();
	}

	public boolean hasNext() throws Exception {
		if((line = bufferedReader.readLine()) != null){
			count++;
			return true;
		}
		return false;
	}

	public String getLine() {
		return line;
	}

	public int getLineNumber() {
		return count;
	}
}
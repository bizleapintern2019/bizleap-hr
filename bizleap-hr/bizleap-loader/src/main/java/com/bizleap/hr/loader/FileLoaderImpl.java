package com.bizleap.hr.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileLoaderImpl implements FileLoader {
	static int count;
	BufferedReader reader;
	private String eachLine;

	public void start(String fileReader) throws Exception {
		count=0;
		reader = new BufferedReader(new FileReader(fileReader));
	}

	public boolean hasNext() throws IOException {
		if ((eachLine = reader.readLine()) != null) {
			count++;
			return true;
		}
		return false;
	}

	public String getLine() {
		return eachLine;
	}

	public void stop() throws Exception {
		if(reader!=null)
			reader.close();
	}
	
	public int getLineNumber() {
		return count;
	}
}
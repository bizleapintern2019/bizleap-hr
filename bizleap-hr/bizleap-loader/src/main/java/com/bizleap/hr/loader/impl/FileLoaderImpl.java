package com.bizleap.hr.loader.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import com.bizleap.hr.loader.FileLoader;

public class FileLoaderImpl implements FileLoader {
	
	private BufferedReader bufferedReader;
	private String line = null;
	private int lineCount = 0;

	public void start(String fileReader) throws Exception {
		lineCount = 0;
		bufferedReader = new BufferedReader(new FileReader(fileReader));
	}

	public boolean hasNext() throws IOException {

		if ((line = bufferedReader.readLine()) != null) {
			if(line.startsWith("#")){
				line = bufferedReader.readLine();
				return true;
			}
			lineCount++;
			return true;
			}
		return false;
	}

	public String getLine() {
		return line;
	}

	public void stop() throws Exception {
		if (bufferedReader != null)
			bufferedReader.close();
	}

	public int getLineNumber() {
		return lineCount;
	}
}
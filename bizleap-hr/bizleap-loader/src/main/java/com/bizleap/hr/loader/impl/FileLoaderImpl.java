package com.bizleap.hr.loader.impl;

import com.bizleap.hr.loader.FileLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileLoaderImpl implements FileLoader {
	private BufferedReader bufferedReader;
	private String line = null;
	private int lineCount = 0;
	private boolean hasError = false;

	public boolean hasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	public void start(String fileReader) throws Exception {
		lineCount = 1;
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
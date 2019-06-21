package com.bizleap.hr.loader.impl;

import com.bizleap.hr.loader.FileLoader;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.stereotype.Service;

@Service
public class FileLoaderImpl implements FileLoader {
	
	private BufferedReader bufferedReader;
	private String line = null;
	private int lineCount = 0;

	public void start(String fileReader) throws Exception {
		lineCount = 0;
		bufferedReader = new BufferedReader(new FileReader(fileReader));
	}

	public boolean hasNext() throws Exception {

		if ((line = bufferedReader.readLine()) != null) {
			if (line.startsWith("#")) {
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

	public int getLineNumber() {
		return lineCount;
	}
	
	public void stop() throws Exception {
		if (bufferedReader != null)
			bufferedReader.close();
	}
}
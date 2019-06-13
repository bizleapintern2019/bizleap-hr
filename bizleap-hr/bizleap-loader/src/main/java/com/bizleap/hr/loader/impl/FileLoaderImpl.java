package com.bizleap.hr.loader.impl;

import com.bizleap.hr.loader.FileLoader;

import java.io.*;

public class FileLoaderImpl implements FileLoader {
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private String line = null;

    public void start(String fileName) {
        try {
            fileReader = new FileReader(getFileFromResources(fileName));
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void finish() {
        try {
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLine() {
        return line;
    }

    public boolean hasNext() {
        try {
            line = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line != null;
    }

    private File getFileFromResources(String fileName) {
        return new File(
                getClass().getClassLoader().getResource(fileName).getFile()
        );
    }
}
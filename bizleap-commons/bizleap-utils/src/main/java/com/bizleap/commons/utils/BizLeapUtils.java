package com.bizleap.commons.utils;

import org.apache.commons.lang3.StringUtils;

public class BizLeapUtils {
	
	public static String makePath(String path,String fileName) {
		if(StringUtils.isEmpty(fileName)) {
			return path;
		}
		if(StringUtils.isEmpty(path)) {
			return fileName;
		}
		if(path.endsWith("/")) {
			return path + fileName;
		}
		return path + "/" +fileName;
	}
}	
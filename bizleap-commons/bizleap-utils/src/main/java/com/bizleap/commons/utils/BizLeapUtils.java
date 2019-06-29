package com.bizleap.commons.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

public class BizLeapUtils {
	
	private Logger logger = Logger.getLogger(BizLeapUtils.class);
	public String makePath(String path,String fileName) {
		if(StringUtils.isEmpty(fileName)) {
			logger.info("path found : "+path);
			return path;
		}
		if(StringUtils.isEmpty(path)) {
			logger.info("file found : "+fileName);
			return fileName;
		}
		if(path.endsWith("\\")) {
			logger.info("pathFile found : "+path + fileName);
			return path + fileName;
		}
		return path + "\\" +fileName;
	}
}

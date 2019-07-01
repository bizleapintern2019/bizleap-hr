package com.bizleap.commons.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import com.bizleap.commons.domain.entity.AbstractEntity;

public class BizLeapUtils {
	
	private static Logger logger = Logger.getLogger(BizLeapUtils.class);
	private AbstractEntity entity;
	
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

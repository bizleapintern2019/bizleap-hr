package com.bizleap.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Parser {
	public static JSONObject parseJSon(String input) {
		JSONObject json = null;
		try {
			json = (JSONObject) new JSONParser().parse(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	public static Date parseDate(String dateStr) throws ParseException {

		if (StringUtils.isEmpty(dateStr))
			return null;
		Date date = null;
		SimpleDateFormat dateFormat;

		if (dateStr.contains("-"))
			dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		else if (dateStr.contains("."))
			dateFormat = new SimpleDateFormat("dd.MM.yyyy");

		else
			dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		if (dateStr != null && !"null".equals(dateStr)) {
			date = dateFormat.parse(dateStr);
		}

		return date;
	}

	public static String dateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
		return dateFormat.format(date);
	}
}

package com.qa.ebay.helper;

import java.io.FileInputStream;
import java.io.IOException;

public class ContentReader {

	public static FileInputStream getPropertyFile(String site, String pageName) throws IOException {

		String path = System.getProperty("user.dir");
		if (site == null) {
			path = path + "/src/main/java/com/qa/ebay/resource/" + pageName + "_us.properties";
		}
		path = path + "/src/main/java/com/qa/ebay/resource/" + pageName + "_" + site + ".properties";

		System.out.println(path);
		FileInputStream file = new FileInputStream(path);
		return file;

	}
}
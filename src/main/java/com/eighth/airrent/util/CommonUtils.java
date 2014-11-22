package com.eighth.airrent.util;

import java.util.UUID;

public class CommonUtils {
	
	public static String genUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}

}

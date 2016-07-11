package com.softcreate.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate {

	public static String getCurrentTime() {
		// TODO Auto-generated method stub
		Date dt = new java.util.Date(System.currentTimeMillis());  
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");  
        String time = fmt.format(dt);
        return time;
	}
}

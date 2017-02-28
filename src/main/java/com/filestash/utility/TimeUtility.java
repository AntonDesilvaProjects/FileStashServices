package com.filestash.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtility {
	//Returns time in the format: 02/17/2017 6:56:03 PM
	public static String getReadableFormat(LocalDateTime time)
	{
		DateTimeFormatter readableTime = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
		return time.format(readableTime);
	}
}

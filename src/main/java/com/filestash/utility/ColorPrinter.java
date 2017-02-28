package com.filestash.utility;

/*
 * ColorPrinter Utility that uses ANSI color codes. 
 * Does not work in Eclipse console.
 */
public class ColorPrinter {

	public static final String ANSI_RESET = "\u001B[0m"; //Resets the console colors
	public static final String ANSI_GREEN = "\u001B[31m";
	
	public static void write(String label, Object msg_Obj)
	{
		System.out.print(ANSI_RESET);
		System.out.println(ANSI_GREEN + label + " : " + msg_Obj);
		System.out.print(ANSI_RESET);
	}
}

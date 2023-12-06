package com.sos.testCases;

import java.util.Arrays;

public class TestClass {
	public static void main(String[] args) {
		String a1 = "MilindGM";
		System.out.println(extraEnd(a1));}
	      static String extraEnd(String str) {
			String st = str.substring(str.length() - 2, str.length());
			return st + st + st;
		}

}
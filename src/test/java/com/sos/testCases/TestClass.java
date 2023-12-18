package com.sos.testCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class TestClass {
	 public static void main(String[] args) {
	        String pattern = "abba";
	        String s = "cat, dog, dog, cat";
	        System.out.println(wordPattern(pattern, s));

	    }

	    public static boolean wordPattern(String pattern, String s) {
	        String[] arr = s.split(",");
	        if (pattern.length() != arr.length) {
	            return false;
	        }
	        HashMap<Character, String> hm = new HashMap<Character, String>();

	        for (int i = 0; i < pattern.length(); i++) {
	            char ch = pattern.charAt(i);
	            boolean containsKey = hm.containsKey(ch);
	            if (hm.containsValue(arr[i]) && !containsKey) {
	                return false;
	            }
	            if (containsKey && !hm.get(ch).equals(arr[i])) {
	                return false;
	            } else {
	                hm.put(ch, arr[i]);
	            }
	        }
	            return true;
	        }												


}

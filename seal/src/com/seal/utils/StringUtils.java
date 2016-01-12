package com.seal.utils;

public class StringUtils {
	
	/**
	 * 将目标字符串首字母变为大写
	 * @param str 目标字符串
	 * @return 首字母变为大写的字符串
	 */
	public static String firstChar2UpperCase(String str){
		//abcd-->Abcd
		//abcd-->ABCD-->Abcd
		return str.toUpperCase().substring(0, 1)+str.substring(1);
	}
	
	
	/**
	 * user_name-->UserName
	 * @param str 目标字符串
	 * @return 
	 */
	/*public static String column2method(String str){
		String result = "";
		//user_name-->UserName
		if(str == null || "" == str){
			return result;
		}
		String[] arr = str.split("_");
		
		for(int i=0 ; i <arr.length;i++){
			String s = arr[i];
			result += s.toUpperCase().substring(0, 1)+s.substring(1);
		}
		
		return result;
	}
	
	*//**
	 * user_name-->userName
	 * @param str 目标字符串
	 * @return 
	 *//*
	public static String column2property(String str){
		String result = "";
		//user_name-->UserName
		if(str == null || "" == str){
			return result;
		}
		String[] arr = str.split("_");
		
		for(int i=0 ; i <arr.length;i++){
			String s = arr[i];
			result += s.toUpperCase().substring(0, 1)+s.substring(1);
		}
		
		return result.toLowerCase().substring(0, 1)+result.substring(1);
	}
	
	public static void main(String[] args) {
		System.out.println(column2property("user_name"));
	}
*/}

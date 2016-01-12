package com.seal.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/** 
 * @author Albert
 *
 * @version 创建时间：2015年12月30日 下午2:39:08 
 */
public class ReflectUtils {

	
	/**
	 * 调用obj对象对应属性fieldName的get方法
	 * @param fieldName
	 * @param obj
	 * @return
	 */
	public static Object invokeGet(String fieldName, Object obj){
		try {
			Class c = obj.getClass();
			Method m = c.getDeclaredMethod("get"+StringUtils.firstChar2UpperCase(fieldName), null);
			return m.invoke(obj, null);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 调用obj对象对应属性fieldName的set方法
	 * @param obj
	 * @param columnName
	 * @param columnValue
	 */
	public static void invokeSet(Object obj, String columnName, Object columnValue){
		try {
			if(columnValue != null){
				Method m = obj.getClass().getDeclaredMethod("set"+StringUtils.firstChar2UpperCase(columnName),columnValue.getClass());
				
				m.invoke(obj, columnValue);
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}

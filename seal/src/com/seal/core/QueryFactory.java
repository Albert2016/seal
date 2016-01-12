package com.seal.core;
/** 
 * 创建Query对象的工厂类
 * 
 * @author Albert
 *
 * @version 创建时间：2015年12月30日 下午2:15:27 
 */
public class QueryFactory {

	private static Query prototypeObj;//
	
	static{
		
		try {
			Class c = Class.forName(DBManager.getConf().getQueryClass());////加载指定的query类
			
			prototypeObj = (Query) c.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		//加载po包下面所有的类，便于重用，提高效率！
		TableContext.loadPOTables();
	}
	
	private QueryFactory(){}
	
	public static Query createQuery(){
		try {
			return (Query) prototypeObj.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
}

package com.seal.core;

/**
 * @author Albert
 *
 * @version 创建时间：2015年12月28日 下午5:23:33
 */
public interface TypeConvertor {
	/**
	 * 将数据库数据类型转化成java的数据类型
	 * 
	 * @param columnType
	 *            数据库字段的数据类型
	 * @return java的数据类型
	 */
	public String databaseType2JavaType(String columnType);

	/**
	 * 将java数据类型转化成数据库数据类型
	 * 
	 * @param javaType
	 * @return 数据库类型
	 */
	public String javaType2databaseType(String javaType);
}

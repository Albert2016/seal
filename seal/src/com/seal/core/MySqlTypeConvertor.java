package com.seal.core;


/**
 * @author Albert
 *
 * @version 创建时间：2015年12月28日 下午5:54:39
 */
public class MySqlTypeConvertor implements TypeConvertor {

	@Override
	public String databaseType2JavaType(String columnType) {
		if ("varchar".equalsIgnoreCase(columnType)
				|| "char".equalsIgnoreCase(columnType)
				|| "text".equalsIgnoreCase(columnType)
				|| "tinytext".equalsIgnoreCase(columnType)) {
			return "String";
		} else if ("int".equalsIgnoreCase(columnType)
				|| "tinyint".equalsIgnoreCase(columnType)
				|| "smallint".equalsIgnoreCase(columnType)
				|| "integer".equalsIgnoreCase(columnType)) {
			return "Integer";
		} else if ("bigint".equalsIgnoreCase(columnType)) {
			return "Long";
		} else if ("double".equalsIgnoreCase(columnType)
				|| "float".equalsIgnoreCase(columnType)) {
			return "Double";
		} else if ("clob".equalsIgnoreCase(columnType)) {
			return "java.sql.CLob";
		} else if ("blob".equalsIgnoreCase(columnType)
				|| "longblob".equalsIgnoreCase(columnType)) {
			return "java.sql.BLob";
		} else if ("date".equalsIgnoreCase(columnType)) {
			return "java.sql.Date";
		} else if ("time".equalsIgnoreCase(columnType)) {
			return "java.sql.Time";
		} else if ("timestamp".equalsIgnoreCase(columnType)
				|| "datetime".equalsIgnoreCase(columnType)) {
			return "java.sql.Timestamp";
		} else if ("boolean".equalsIgnoreCase(columnType)
				|| "bit".equalsIgnoreCase(columnType)) {
			return "Boolean";
		} else if ("decimal".equalsIgnoreCase(columnType)
				|| "numeric".equalsIgnoreCase(columnType)) {
			return "java.math.BigDecimal";

		}else if ("binary".equalsIgnoreCase(columnType)
				||"varbinary".equalsIgnoreCase(columnType)
				||"longvarbinary".equalsIgnoreCase(columnType)) {
			return "byte[]";
		}

		return null;
	}

	@Override
	public String javaType2databaseType(String javaType) {
		return null;
	}

}

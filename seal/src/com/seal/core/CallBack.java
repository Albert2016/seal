package com.seal.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/** 
 * @author Albert
 *
 * @version 创建时间：2015年12月28日 下午4:17:23 
 */
public interface CallBack {
	public Object doExecute(Connection connection, PreparedStatement ps, ResultSet rs);
}

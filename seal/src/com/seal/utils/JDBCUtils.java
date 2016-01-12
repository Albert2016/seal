package com.seal.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/** 
 * @author Albert
 *
 * @version 创建时间：2015年12月28日 下午5:50:09 
 */
public class JDBCUtils {

	/**
	 * 给SQL设参
	 * @param ps
	 * @param params
	 */
	public static void handleParams(PreparedStatement ps, Object[] params){
		if(params != null){
			for(int i=0; i<params.length;i++){
				try {
					ps.setObject(i+1, params[i]);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

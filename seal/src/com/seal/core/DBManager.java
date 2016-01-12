package com.seal.core;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.seal.bean.Configuration;
import com.seal.pool.DBConnPool;


/** 
 * @author Albert
 *
 * @version 创建时间：2015年12月28日 下午4:29:19 
 */
public class DBManager {
	/**
	 * 配置信息
	 */
	private static Configuration conf;
	
	/**
	 * 连接池对象
	 */
	private static DBConnPool pool;
	
	static {
		Properties pros = new Properties();
		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("seal.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		conf  = new Configuration();
		conf.setDriver(pros.getProperty("driver"));
		conf.setPoPackage(pros.getProperty("poPackage"));
		conf.setPwd(pros.getProperty("pwd"));
		conf.setSrcPath(pros.getProperty("srcPath"));
		conf.setUrl(pros.getProperty("url"));
		conf.setUser(pros.getProperty("user"));
		conf.setUsingDB(pros.getProperty("usingDB"));
		conf.setQueryClass(pros.getProperty("queryClass"));
		conf.setPoolMaxSize(Integer.parseInt(pros.getProperty("poolMaxSize")));
		conf.setPoolMinSize(Integer.parseInt(pros.getProperty("poolMinSize")));
		
	}

	/**
	 * 获得Connection对象
	 * @return
	 */
	public static Connection getConn() {
		if(pool == null){
			pool = new DBConnPool();
		}
		return pool.getConnection();
	}
	
	/**
	 * 连接池关闭
	 * @param connection
	 */
	public static void close(Connection connection){
		pool.close(connection);
	}
	
	/**
	 * 关闭传入的Statement、Connection对象
	 * @param ps
	 * @param conn
	 */
	public static void close(Statement ps, Connection connection){
		try {
			if(ps != null){
				ps.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pool.close(connection);
	}
	public static void close(ResultSet rs, Statement s, Connection conn){
		try {
			if(rs != null){
				rs.close();
			}
			if(s != null){
				s.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pool.close(conn);
	}

	/**
	 * 返回Configuration对象
	 * @return
	 */
	public static Configuration getConf() {
		return conf;
	}
	
	/**
	 * 创建新的Connection对象
	 * @return
	 */
	public static Connection createConn() {
		try {
			Class.forName(conf.getDriver());
			return DriverManager.getConnection(conf.getUrl(), conf.getUser(), conf.getPwd());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}

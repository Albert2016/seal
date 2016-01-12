package com.seal.core;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.seal.bean.ColumnInfo;
import com.seal.bean.TableInfo;
import com.seal.utils.JavaFileUtils;
import com.seal.utils.StringUtils;

/** 
 * 负责获取管理数据库所有表结构和类结构的关系，并可以根据表结构生成类结构。
 * 
 * @author Albert
 *
 * @version 创建时间：2015年12月29日 上午10:10:17 
 */
public class TableContext {
	/**
	 * 表名为key，表信息对象为value
	 */
	public static Map<String, TableInfo> tables = new HashMap<String, TableInfo>();
	/**
	 * 将po的class对象和表信息对象关联起来，便于重用！
	 */
	public static Map<Class, TableInfo> poClassTableMap = new HashMap<Class, TableInfo>();
	
	private TableContext(){}
	
	
	static{
		
		try{
			Connection conn = DBManager.getConn();
			DatabaseMetaData dbmd = conn.getMetaData();
			
			ResultSet  tableRet = dbmd.getTables(null, "%","%", new String[]{"TABLE"});
			
			while(tableRet.next()){
				String tableName = (String) tableRet.getObject("TABLE_NAME");
				TableInfo ti = new TableInfo(tableName, new ArrayList<ColumnInfo>(),
						new HashMap<String, ColumnInfo>());
						tables.put(tableName, ti);
				
				ResultSet columnSet = dbmd.getColumns(null, "%", tableName, "%");  //查询表中的所有字段
				
				while(columnSet.next()){
					ColumnInfo ci = new ColumnInfo(columnSet.getString("COLUMN_NAME"),
							columnSet.getString("TYPE_NAME"),0);
					ti.getColumns().put(columnSet.getString("COLUMN_NAME"), ci);
				}
				
				ResultSet set = dbmd.getPrimaryKeys(null, "%", tableName);
				while(set.next()){
					ColumnInfo ci2 = ti.getColumns().get(set.getObject("COLUMN_NAME"));
					ci2.setKeyType(1);//设置为主键类型
					ti.getPrikeys().add(ci2);
				}
				
				if(ti.getPrikeys().size() > 0){ //取唯一主键。。方便使用。如果是联合主键。则为空！
					ti.setOnlyPrimaryKey(ti.getPrikeys().get(0));
				}
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据表结构，更新配置的po包下面的java类
	 * 实现了从表结构转化到类结构
	 */
	public static void updateJavaPOFile(){
		Map<String , TableInfo> map = TableContext.tables;
		for(TableInfo t: map.values()){
			JavaFileUtils.createJavaPOFile(t, new MySqlTypeConvertor());
		}
	}
	
	/**
	 * 加载po包下面的类
	 */
	public static void loadPOTables(){
		for(TableInfo tableInfo : tables.values()){
			try {
				Class c = Class.forName(DBManager.getConf().getPoPackage()+"."+StringUtils.firstChar2UpperCase(tableInfo.getTname()));
				poClassTableMap.put(c, tableInfo);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
	}
}

package com.seal.bean;

import java.util.List;
import java.util.Map;


/**
 * 存储表结构的信息
 * 
 * @author Albert
 *
 * @version 创建时间：2015年12月28日 下午5:29:15
 */
public class TableInfo {
	/**
	 * 表名
	 */
	private String tname;
	/**
	 * 所有字段的信息
	 */
	private Map<String, ColumnInfo> columns;
	/**
	 * 唯一主键(目前我们只能处理表中有且只有一个主键的情况)
	 */
	private ColumnInfo onlyPrimaryKey;
	/**
	 * 如果联合主键，则在这里存储
	 */
	private List<ColumnInfo> prikeys;

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public Map<String, ColumnInfo> getColumns() {
		return columns;
	}

	public void setColumns(Map<String, ColumnInfo> columns) {
		this.columns = columns;
	}

	public ColumnInfo getOnlyPrimaryKey() {
		return onlyPrimaryKey;
	}

	public void setOnlyPrimaryKey(ColumnInfo onlyPrimaryKey) {
		this.onlyPrimaryKey = onlyPrimaryKey;
	}

	public List<ColumnInfo> getPrikeys() {
		return prikeys;
	}

	public void setPrikeys(List<ColumnInfo> prikeys) {
		this.prikeys = prikeys;
	}

	public TableInfo(String tname, Map<String, ColumnInfo> columns,
			ColumnInfo onlyPrimaryKey) {
		super();
		this.tname = tname;
		this.columns = columns;
		this.onlyPrimaryKey = onlyPrimaryKey;
	}
	public TableInfo(String tname,List<ColumnInfo> priKeys, Map<String, ColumnInfo> columns
			) {
		super();
		this.tname = tname;
		this.columns = columns;
		this.prikeys = priKeys;
	}
	public TableInfo() {
	}

}

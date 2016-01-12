package com.seal.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.seal.bean.ColumnInfo;
import com.seal.bean.JavaFieldGetSet;
import com.seal.bean.TableInfo;
import com.seal.core.DBManager;
import com.seal.core.TypeConvertor;

/**
 * @author Albert
 *
 * @version 创建时间：2015年12月29日 下午5:41:50
 */
public class JavaFileUtils {

	public static void createJavaPOFile(TableInfo t,
			TypeConvertor convertor) {
		 String src = createJavaSrc(t, convertor);
		 
		 String srcPath = DBManager.getConf().getSrcPath()+"\\";
		 String packagePath = DBManager.getConf().getPoPackage().replaceAll("\\.", "/");
		
		 File f = new File(srcPath + packagePath);
		 
		 if(!f.exists()){
			 f.mkdirs();
		 }
		 
		 BufferedWriter bw = null;
		 
		 try {
			bw = new BufferedWriter(new FileWriter(f.getAbsoluteFile()+"/"+StringUtils.firstChar2UpperCase(t.getTname())+".java"));
		
			bw.write(src);
			System.out.println("建立表"+t.getTname()+"对应的java类："+StringUtils.firstChar2UpperCase(t.getTname())+".java");
	
		 } catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(bw!=null){
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		 
		 
	}

	/**
	 * 根据字段信息生成java属性信息。如：varchar username-->private String
	 * username;以及相应的set和get方法源码
	 * 
	 * @param column
	 *            字段信息
	 * @param convertor
	 *            类型转化器
	 * @return java属性和set/get方法源码
	 */
	public static JavaFieldGetSet createFieldGetSetSRC(ColumnInfo column,
			TypeConvertor convertor) {
		JavaFieldGetSet jfgs = new JavaFieldGetSet();

		String javaFieldType = convertor.databaseType2JavaType(column.getDataType());
		jfgs.setFieldInfo("\tprivate " + javaFieldType + " " + column.getName() + ";\n");

		// public String getUsername(){return username;}
		// 生成get方法的源代码
		StringBuilder getSrc = new StringBuilder();
		getSrc.append("\tpublic " + javaFieldType + " get" + StringUtils.firstChar2UpperCase(column.getName()) + "(){\n");
		getSrc.append("\t\treturn " + StringUtils.firstChar2UpperCase(column.getName()) + ";\n");
		getSrc.append("\t}\n");
		jfgs.setGetInfo(getSrc.toString());

		// public void setUsername(String username){this.username=username;}
		// 生成set方法的源代码
		StringBuilder setSrc = new StringBuilder();
		setSrc.append("\tpublic void set" + StringUtils.firstChar2UpperCase(column.getName()) + "(");
		setSrc.append(javaFieldType + " " + StringUtils.firstChar2UpperCase(column.getName()) + "){\n");
		setSrc.append("\t\tthis." + StringUtils.firstChar2UpperCase(column.getName()) + "=" + StringUtils.firstChar2UpperCase(column.getName()) + ";\n");
		setSrc.append("\t}\n");
		jfgs.setSetInfo(setSrc.toString());
		return jfgs;
	}

	public static String createJavaSrc(TableInfo tableInfo, TypeConvertor convertor) {
		Map<String, ColumnInfo> columns = tableInfo.getColumns();
		List<JavaFieldGetSet> javaFields = new ArrayList<JavaFieldGetSet>();
		for (ColumnInfo c : columns.values()) {
			 javaFields.add(createFieldGetSetSRC(c, convertor));
		}
		
		StringBuilder src = new StringBuilder();
		
		//生成package语句
		src.append("package "+DBManager.getConf().getPoPackage()+";\n\n");
		//生成import语句
		src.append("import java.sql.*;\n");
		src.append("import java.util.*;\n\n");
		//生成类声明语句
		src.append("public class "+StringUtils.firstChar2UpperCase(tableInfo.getTname())+" {\n\n");
		
		//生成属性列表
		for(JavaFieldGetSet f:javaFields){
			src.append(f.getFieldInfo());
		}
		src.append("\n\n");
		//生成get方法列表
		for(JavaFieldGetSet f:javaFields){
			src.append(f.getGetInfo());
		}
		//生成set方法列表
		for(JavaFieldGetSet f:javaFields){
			src.append(f.getSetInfo());
		}
		
		//生成类结束
		src.append("}\n");
		return src.toString();
	}
}

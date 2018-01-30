package mq;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ParseFolder {
	ParseFile pasFile=new ParseFile();
	List<String> filesPath=new ArrayList<String>();
	public void parseString(String str,String mqip) throws InterfaceException {
		//String str1="D:\\MSS_VOBC_TRAINRUN_INFO";
		File file=new File(str);
		//判断是文件夹还是文件
		
			if (file.isDirectory()) {
				// 如果是文件夹，递归获取文件夹下的所有文件
				getFiles(file.getAbsolutePath());
				System.out.println("当前目录下共有" + filesPath.size() + "个文件。");
				for (String pathStr : filesPath) {
					try {
						pasFile.readFile(pathStr, mqip);
					} catch (IOException e) {
						String errorStr=e.getMessage().split(",")[0];
						
						//抛出自定义异常，异常内容携带错误报文所在的文件，暂不定义行数
						throw new InterfaceException(errorStr.replaceAll("class ", "")+","+pathStr);
					}
				}
			} else {
				try {
					// 如果是文件，直接解析
					pasFile.readFile(str, mqip);
				}catch(IOException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
					//抛出自定义异常，异常内容携带错误报文所在的文件，暂不定义行数
					throw new InterfaceException(str);
				}
			}
		
	}
	public void getFiles(String pathString){
		File file=new File(pathString);
		File[] files=file.listFiles();
		//List<String> filesPath=new ArrayList<String>();
		for(int i=0;i<files.length;i++){
			if(files[i].isDirectory()){
				getFiles(files[i].getAbsolutePath());
			};
			
			filesPath.add(files[i].getAbsolutePath());
			
		}
		
	}
}

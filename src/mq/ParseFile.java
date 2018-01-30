package mq;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import msgVerify.AlarmMessage;
import msgVerify.StationMessage;
import msgVerify.StringToJson;

public class ParseFile {
	
	public void readFile(String filePath,String mqip) throws IOException {
		SenderFun senderFun=new SenderFun();
		File file=new File(filePath);
		StringToJson stj=new StringToJson();
		 String temp=null;
		 //通过路径分析要发送的队列
		 Pattern p=Pattern.compile("MSS_.*?_INFO");
		 Matcher matcher = p.matcher(filePath);
		 if(!matcher.find()){
			 System.out.println("路径有误");
		 }else{
			 //System.out.println(matcher.group());
			 try {
				BufferedReader br=new BufferedReader(new FileReader(file));
				while((temp=br.readLine())!=null){
					if(temp.contains("END")){
						System.out.println(filePath+"文件读取完成！");
					}else {
						String[] msg=temp.split("\\|");
						System.out.println(matcher.group()+"  "+msg[1]);
						if(matcher.group().contains("ALARM")){
							stj.jsonStrToObject(msg[1], AlarmMessage.class);
						}else if(matcher.group().contains("STATION")){
							stj.jsonStrToObject(msg[1], StationMessage.class);
						}
						
							senderFun.sendByFile(matcher.group(), 0, msg[1], mqip);
						
							
						
						
						}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		 }
		
	}
}

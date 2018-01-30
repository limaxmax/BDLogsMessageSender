package msgVerify;
import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class StringToJson {
	
	//鏍规嵁闃熷垪鍚嶇О鍒ゆ柇浼犲叆鐨勫瓧绗︿覆鏄惁鏈夐棶棰�
	public <T> T jsonStrToObject(String jsonStr,Class<T> klass) throws  IOException  {
		
		AlarmMessage alarmMessage=new AlarmMessage();
		ObjectMapper mapper=new ObjectMapper();
		return mapper.readValue(jsonStr, klass);
	}
	@Test
	public void jsonStrToObjectTest(){
		String jsonStr="{\"MsgHead\":{\"SystemLabel\":\"ATS\",\"SystemNo\":8,\"LocationNo\":2147483647,\"LineLabel\":\"TJ6\",\"MsgId\":2147483647,\"Stamp\":4294967295},\"MsgBody\":{\"Devices\":[{\"Stamp\":1510360936,\"Status\":[1],\"AlarmStatus\":1,\"AlarmLevel\":3,\"DeviceNo\":\"TJ6_ATS_000_999_9999\"}]}}";
		//JsonValidator jl=new JsonValidator();
		try {
			System.out.println(this.jsonStrToObject(jsonStr,AlarmMessage.class));
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			String errorStr=e.getMessage().split(",")[0];
			System.out.println(errorStr);
			System.out.println(errorStr.replaceAll("class ", ""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
}

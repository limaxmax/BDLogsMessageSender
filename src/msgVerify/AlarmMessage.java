package msgVerify;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

public class AlarmMessage extends SuperMessage {
	public AlarmMsgBody MsgBody;
	public String toString(){
		return "AlarmMessage [MsgHead="+MsgHead+"MsgBody=" + MsgBody +  "]";
	}

}
class AlarmMsgBody{
	public List<AlarmDevice> Devices;
	@Override
	public String toString() {
		return "MsgBody [Devices=" + Devices + "]";
	}		
}
class AlarmDevice{
	public byte[] Status;
	public long Stamp;
	public int AlarmStatus;
	public int AlarmLevel;
	public String DeviceNo;
	@Override
	public String toString() {
		return "AlarmDevice [Status=" + Arrays.toString(Status) + ", Stamp=" + Stamp + ", AlarmStatus=" + AlarmStatus
				+ ", AlarmLevel=" + AlarmLevel + ", DeviceNo=" + DeviceNo + "]";
	}		
}
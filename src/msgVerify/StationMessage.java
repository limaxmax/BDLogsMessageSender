package msgVerify;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StationMessage extends SuperMessage {
	
	public StationMsgBody MsgBody;

}
class StationMsgBody {
	public StationData StationData;
}
class StationData {
	public List<AtsStationData> listASD;
	public List<SpeedData> listSD;
	public List<AtsTrainTraceStatus> atts;
}
class SpeedData{
	public int TsrSpeed;
	public String SectionId;
}
class AtsStationData{
	public int Status;
	public String DeviceName;
	public int StatusType;
	public int DeviceType;

}
class AtsTrainTraceStatus{
	public int StationId;
	public List<AtsTrainInfo> Train;
	public String DeviceNo;
}
class AtsTrainInfo{
	public String DeviceNo;
	public String serverNO;
	public int driverNO;
	public int property;
	public long Stamp;
	public String GroupId;
	public String TrainId;
	public String TableNo;
	public String destination;
	public String TrainType;
	public int AtpFlag;
	public int OnTime;
	public int SkipStopFlag;
	public long BlockMode;
	public int DrivingMode;
	public int Dir;
	public String PhySection;
	public String LogicSection;
	public int TrainBuckle;
	public int TrainStay;
	public int TrainDoor;
	public int TrainConflict;
	public int Delete;
	public int TccWindowOffset;
	
}
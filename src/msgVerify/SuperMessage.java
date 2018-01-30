package msgVerify;

public class SuperMessage {
	public MsgHead MsgHead;
	
	@Override
	public String toString() {
		return "SuperMessage [MsgHead=" + MsgHead.toString() +  "]";
	}
}

class MsgHead{
	public String SystemLabel;	
	public int SystemNo;	
	public int LocationNo;	
	public int MsgId;
	public long Stamp;
	public String LineLabel;
	@Override
	public String toString() {
		return "MsgHead [SystemLabel=" + SystemLabel + ", SystemNo=" + SystemNo + ", LocationNo=" + LocationNo
				+ ", MsgId=" + MsgId + ", Stamp=" + Stamp + ", LineLabel=" + LineLabel + "]";
	}		
}

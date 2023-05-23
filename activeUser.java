package toolsProject;

public class activeUser {
	private String acitveUserName;
	private int activeUserId;
	public void setAcitveUserName(String acitveUserName) {
		this.acitveUserName = acitveUserName;
	}
	public void setActiveUserId(int activeUserId) {
		this.activeUserId = activeUserId;
	}
	public String getAcitveUserName() {
		return acitveUserName;
	}
	public int getActiveUserId() {
		return activeUserId;
	}
	public activeUser (String name,int id) {
		this.acitveUserName = name;
		this.activeUserId = id;
	}
}

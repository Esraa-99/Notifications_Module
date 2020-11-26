import java.util.ArrayList;

public class Notification {
	private ArrayList <Channel> targets;
	private NotificationTemplate content;
	public void setTargets(ArrayList<Channel> targets) {
		this.targets = targets;
	}
	public void setContent(NotificationTemplate content) {
		this.content = content;
	}
	public ArrayList<Channel> getTargets() {
		return targets;
	}
	public NotificationTemplate getContent() {
		return content;
	}
	
}

package Notification_Module.Template;

public abstract class Source {

	String source;

	Source() {
		source = "";
	}

	Source(String source) {
		this.source = source;
	}
   
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public abstract NotificationTemplate readSource(String target);

	public abstract boolean writeToSource(NotificationTemplate template);

	public abstract boolean updateSource(String target, NotificationTemplate template);

	public abstract boolean deleteFromSource(String target);
}
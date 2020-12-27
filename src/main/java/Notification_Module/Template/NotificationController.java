package Notification_Module.Template;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NotificationController {
	private String[] values;
	private String subject;
	private Channel channel;
	public String[] getValues() {
		return values;
	}
	public void setValues(String[] values) {
		this.values = values;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
}

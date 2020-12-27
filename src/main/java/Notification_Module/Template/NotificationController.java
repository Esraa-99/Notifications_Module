package Notification_Module.Template;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NotificationController {
	private String[] values;
	private String subject;
	private String destination;
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
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
}

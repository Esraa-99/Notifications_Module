package Controllers;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jettison.json.JSONObject;

@XmlRootElement
public class NotificationController {
	private String[] values;
	private String subject;
	private JSONObject channel;
	public JSONObject getChannel() {
		return channel;
	}
	
	public void setChannel(JSONObject channel) {
		this.channel = channel;
	}

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
}

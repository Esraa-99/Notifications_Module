package Notification_Module.Template;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Email implements Channel {
	private String email_address;

	@Override
	public void send(NotificationTemplate notification) {
		// TODO Auto-generated method stub

	}
	public String getEmail_address() {
		return email_address;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

}

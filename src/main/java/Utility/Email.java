package Utility;

import javax.xml.bind.annotation.XmlRootElement;

import Controllers.NotificationTemplate;

@XmlRootElement
public class Email implements Channel {
	private String email_address;

	@Override
	public void send(NotificationTemplate notification) {
		// TODO Auto-generated method stub

	}
	public String getDestination() {
		return email_address;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

}

package Utility;

import javax.xml.bind.annotation.XmlRootElement;

import Controllers.NotificationTemplate;

@XmlRootElement
public class SMS implements Channel {
	
	private String phone_no;

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	@Override
	public void send(NotificationTemplate notification) {
		// TODO Auto-generated method stub

	}
}
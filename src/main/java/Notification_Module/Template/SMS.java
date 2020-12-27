/**
 * 
 */
package Notification_Module.Template;

/**
 * @author www
 *
 */
public class SMS implements Channel {

  /**
	 * @return the sms
	 */
	public String getSms() {
		return sms;
	}

	/**
	 * @param sms the sms to set
	 */
	public void setSms(String sms) {
		this.sms = sms;
	}

private String phone_no,sms ;
	
	/**
 * @return the phone_no
 */
  
public String getPhone_no() {
	return phone_no;
}

/**
 * @param phone_no the phone_no to set
 */
public void setPhone_no(String phone_no) {
	this.phone_no = phone_no;
}

	@Override
	public void send(NotificationTemplate notification) {
		// TODO Auto-generated method stub
		
	}


	

}

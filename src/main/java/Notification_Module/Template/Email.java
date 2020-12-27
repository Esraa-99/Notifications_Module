/**
 * 
 */
package Notification_Module.Template;

/**
 * @author www
 *
 */
public class Email implements Channel {
private String email_address ;
	@Override
	
	public void send(NotificationTemplate notification) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @return the email_address
	 */
	public String getEmail_address() {
		return email_address;
	}
	/**
	 * @param email_address the email_address to set
	 */
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	

}

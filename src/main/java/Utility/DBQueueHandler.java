package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Controllers.NotificationTemplate;

public class DBQueueHandler implements QueueHandler {
	public void queing(NotificationTemplate template, Channel channel, Source databaseCon) {
		Connection Con = null;
		Statement Stmt = null;
		SMS sms= new SMS ();
		Email email = new Email();
		
		try {
			Con = DriverManager.getConnection(databaseCon.getSource());
			String notiCreationDate = java.time.LocalDate.now().toString();
			Stmt = Con.createStatement();
			if (channel instanceof SMS) {
				Stmt.executeUpdate("INSERT INTO SMS (phoneNo) VALUES('" + sms.getDestination() + "')");
			} else
				Stmt.executeUpdate("INSERT INTO Email (emailAddress) VALUES('" + email.getDestination() + "')");
			Stmt.executeUpdate(
					"INSERT INTO Notifications (subject,content,date,destination) VALUES('" + template.getSubject() + "',"
							+ "'" + template.getContent()  + "', '" + notiCreationDate + "', '" + channel.getDestination() + "')");
			
			Con.close();
			Stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList <NotificationTemplate> dequeing(Source databaseCon) {
		Connection Con = null;
		Statement Stmt = null;
		ResultSet RS=null; 
		SMS sms= new SMS ();
		Email email = new Email();
		/*ArrayList <NotificationTemplate> notificationsbySMS = new ArrayList <NotificationTemplate>();
		ArrayList <NotificationTemplate> notificationsbyEmail = new ArrayList <NotificationTemplate>();
		*/
		ArrayList <NotificationTemplate> notifications = new ArrayList <NotificationTemplate>();
		try {
		Con = DriverManager.getConnection(databaseCon.getSource());
		RS=Stmt.executeQuery("SELECT * FROM notifications");
		int numberOfnoti= Integer.parseInt(RS.getString("notificationID"));
		String subject = RS.getString("subject");
		String content = RS.getString("content");
		String destination = RS.getString("destination");
		for (int i=1; i<numberOfnoti; i++) {
			NotificationTemplate notification= new NotificationTemplate();
			notification.setSubject(subject);
			notification.setContent(content);
			notifications.add(notification);
			if (destination=="SMS") {                   		//For the one who will make the send function idk if this will help or not              
			RS=Stmt.executeQuery("SELECT * FROM SMS");
			String phoneNo=RS.getString("phoneNO");
			sms.setPhone_no(phoneNo);
			sms.send(notification);
			/*NotificationTemplate notificationbySMS= new NotificationTemplate();
			notificationbySMS.setSubject(subject);
			notificationbySMS.setContent(content);
			notifications.add(notificationbySMS);*/
			}
			if (destination=="Email") {
				RS=Stmt.executeQuery("SELECT * FROM Email");
				String emailAddress=RS.getString("emailAddress");
				email.setEmail_address(emailAddress);
				email.send(notification);
				}
			
			}
		RS.close();
		Con.close();
		Stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return notifications;
	}

}

package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import Controllers.NotificationTemplate;

public class DBQueueHandler implements QueueHandler {
	public void queing(NotificationTemplate template, Channel channel, Source databaseCon) {
		Connection Con = null;
		Statement Stmt = null;
		String typeOfChannel;
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
	public ArrayList <NotificationTemplate> dequeing(Channel channel, Source databaseCon) {
		Connection Con = null;
		Statement Stmt = null;
		ResultSet RS=null; 
		ArrayList <NotificationTemplate> notifications = new ArrayList <>();
		try {
		Con = DriverManager.getConnection(databaseCon.getSource());
		RS=Stmt.executeQuery("SELECT * FROM notifications");
		int numberOfnoti= Integer.parseInt(RS.getString("notificationID"));
		String subject = RS.getString("subject");
		String content = RS.getString("content");
		String destination = RS.getString("destination");
		if (RS.next()) {
		for (int i=1; i<numberOfnoti; i++) {
			NotificationTemplate notification;
			notification.setSubject(subject);
			notification.setContent(content);
			channel.setDestination(destination);
			notifications.add(notification);
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

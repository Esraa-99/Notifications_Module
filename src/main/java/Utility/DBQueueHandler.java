package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import Controllers.NotificationTemplate;

public class DBQueueHandler implements QueueHandler {
	private Source databaseCon;

	public DBQueueHandler(Source databaseCon) {
		this.databaseCon = databaseCon;
	}

	public void queing(NotificationTemplate template, Channel channel) {
		Connection Con = null;
		Statement Stmt = null;
		SMS sms = new SMS();
		Email email = new Email();
		try {
			Con = DriverManager.getConnection(databaseCon.getSource());
			Stmt = Con.createStatement();
			Stmt.executeUpdate("INSERT INTO Notifications (subject,content) VALUES('" + template.getSubject() + "',"
					+ "'" + template.getContent() + "')");
			if (channel instanceof SMS) {
				Stmt.executeUpdate("INSERT INTO SMS (phoneNo) VALUES('" + sms.getDestination() + "')");
			} else {
				Stmt.executeUpdate("INSERT INTO Email (emailAddress) VALUES('" + email.getDestination() + "')");
			}
			Con.close();
			Stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean dequeueSMS(int notificationID) {
		boolean status = false;
		try {
			Connection con = DriverManager.getConnection(databaseCon.getSource());
			Statement sms_stmt = con.createStatement();
			ResultSet sms_rs = sms_stmt
					.executeQuery("SELECT * FROM SMS WHERE notificationID = '" + notificationID + "'");
			status = sms_rs.next();
			if (status) {
				String destination = sms_rs.getString("phoneNo");
				Statement notification_stmt = con.createStatement();
				ResultSet notification_rs = notification_stmt
						.executeQuery("SELECT * FROM Notifications WHERE notificationID = '" + notificationID + "'");
				status = status && notification_rs.next();
				if (status) {
					NotificationTemplate notification = new NotificationTemplate();
					SMS sms = new SMS();
					String subject = notification_rs.getString("subject");
					String content = notification_rs.getString("content");
					notification.setSubject(subject);
					notification.setContent(content);
					sms.setPhone_no(destination);
					sms.send(notification);
					sms_stmt.executeUpdate("DELETE FROM SMS WHERE notificationID = '" + notificationID + "'");
					notification_stmt
							.executeUpdate("DELETE FROM Notifications WHERE notificationID = '" + notificationID + "'");
				}
				notification_stmt.close();
				notification_rs.close();
			}
			sms_rs.close();
			sms_stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean dequeueEmail(int notificationID) {
		boolean status = false;
		try {
			Connection con = DriverManager.getConnection(databaseCon.getSource());
			Statement email_stmt = con.createStatement();
			ResultSet email_rs = email_stmt
					.executeQuery("SELECT * FROM Email WHERE notificationID = '" + notificationID + "'");
			status = email_rs.next();
			if (status) {
				String destination = email_rs.getString("emailAddress");
				Statement notification_stmt = con.createStatement();
				ResultSet notification_rs = notification_stmt
						.executeQuery("SELECT * FROM Notifications WHERE notificationID = '" + notificationID + "'");
				status = status && notification_rs.next();
				if (status) {
					NotificationTemplate notification = new NotificationTemplate();
					SMS sms = new SMS();
					String subject = notification_rs.getString("subject");
					String content = notification_rs.getString("content");
					notification.setSubject(subject);
					notification.setContent(content);
					sms.setPhone_no(destination);
					sms.send(notification);
					email_stmt.executeUpdate("DELETE FROM Email WHERE notificationID = '" + notificationID + "'");
					notification_stmt
							.executeUpdate("DELETE FROM Notifications WHERE notificationID = '" + notificationID + "'");
				}
				notification_stmt.close();
				notification_rs.close();
			}
			email_rs.close();
			email_stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

}

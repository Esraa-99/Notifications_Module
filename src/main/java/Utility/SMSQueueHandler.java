package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.codehaus.jettison.json.JSONObject;

import Controllers.NotificationController;
import Controllers.NotificationTemplate;

public class SMSQueueHandler implements QueueHandler {

	private Source databaseCon;

	public SMSQueueHandler(Source databaseCon) {
		this.databaseCon = databaseCon;
	}

	@Override
	public void queue(NotificationController notification) {
		NotificationTemplate template = databaseCon.readSource(notification.getSubject());
		if (template != null) {
			template.insertValues(notification.getValues());
			JSONObject channel = notification.getChannel();
			SMS sms = new SMS();
			try {
				sms.setPhone_no(channel.getString("phoneNo"));
				Connection Con = DriverManager.getConnection(databaseCon.getSource());
				Statement Stmt = Con.createStatement();
				Stmt.executeUpdate("INSERT INTO Notifications (subject,content) VALUES('" + template.getSubject() + "',"
						+ "'" + template.getContent() + "')");
				Stmt.executeUpdate("INSERT INTO SMS (phoneNo) VALUES('" + sms.getDestination() + "')");
				Con.close();
				Stmt.close();
			} catch (Exception e) {
				System.out.print(e);
			}
		}
	}

	@Override
	public boolean dequeue(int notificationID) {
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
			System.out.print(e);
		}
		return status;
	}

}

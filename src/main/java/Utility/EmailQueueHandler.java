package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.codehaus.jettison.json.JSONObject;

import Controllers.NotificationController;
import Controllers.NotificationTemplate;

public class EmailQueueHandler implements QueueHandler {

	private Source databaseCon;

	public EmailQueueHandler(Source databaseCon) {
		this.databaseCon = databaseCon;
	}

	@Override
	public void queue(NotificationController notification) {
		NotificationTemplate template = databaseCon.readSource(notification.getSubject());
		if (template != null) {
			template.insertValues(notification.getValues());
			JSONObject channel = notification.getChannel();
			Email email = new Email();
			try {
				email.setEmail_address(channel.getString("email"));
				Connection Con = DriverManager.getConnection(databaseCon.getSource());
				Statement Stmt = Con.createStatement();
				Stmt.executeUpdate("INSERT INTO Notifications (subject,content) VALUES('" + template.getSubject() + "',"
						+ "'" + template.getContent() + "')");
				ResultSet rs = Stmt.executeQuery("SELECT * FROM Notifications WHERE subject = '" + template.getSubject() + "'");
				rs.next();
				Stmt.executeUpdate("INSERT INTO Email (emailAddress, notificationID) VALUES('" + email.getDestination() + "', '" + rs.getString("notificationID") + "')");
				rs.close();
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
			System.out.print(e);
		}
		return status;
	}

}

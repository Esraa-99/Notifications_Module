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
		try {
			Con = DriverManager.getConnection(databaseCon.getSource());
			String notiCreationDate = java.time.LocalDate.now().toString();
			Stmt = Con.createStatement();
			if (channel instanceof SMS) {
				typeOfChannel = "SMS";
			} else
				typeOfChannel = "Email";
			Stmt.executeUpdate(
					"INSERT INTO Notifications (subject,content,channel,date,destination) VALUES('" + template.getSubject() + "',"
							+ "'" + template.getContent() + "', '" + typeOfChannel + "', '" + notiCreationDate + "', '" + channel.getDestination() + "')");
			Con.close();
			Stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

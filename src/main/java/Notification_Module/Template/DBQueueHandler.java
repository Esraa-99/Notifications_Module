package Notification_Module.Template;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBQueueHandler implements QueueHandler {
	public void queing(NotificationTemplate template, Channel channel,	Source databaseCon) {
		Connection Con = null;
		Statement Stmt = null;
		ResultSet RS = null;
		String typeOfChannel;
		try {
			Con = DriverManager.getConnection(databaseCon.getSource());
			String notiCreationDate = java.time.LocalDate.now().toString();
			Stmt = Con.createStatement();
			RS = Stmt.executeQuery("SELECT * FROM Notifications");
			if (channel instanceof SMS ) {
				typeOfChannel="SMS";
			}
			else 
				typeOfChannel="Email";
			do {
				Stmt.executeUpdate("INSERT INTO Notifications (subject,content,channel,date) " + "VALUES('"
						+ template.getSubject() + "'," + "'" +  template.getContent()  + "'," + "'" + typeOfChannel
						+ ",'" + "'" + notiCreationDate + "')");
			} while (RS.next());
			RS.close();
			Con.close();
			Stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
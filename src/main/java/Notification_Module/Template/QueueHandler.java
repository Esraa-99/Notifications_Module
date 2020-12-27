package Notification_Module.Template;

import java.sql.*;

public class QueueHandler {
	public void queing(NotificationTemplate template, Channel channel) throws ClassNotFoundException {
		String url = "jdbc:mysql://localhost:3306/notificationmodule";
		String user = "root";
		String password = "route";
		Connection Con = null;
		Statement Stmt = null;
		ResultSet RS = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Con = DriverManager.getConnection(url, user, password);
			String notiCreationDate = java.time.LocalDate.now().toString();
			Stmt = Con.createStatement();
			RS = Stmt.executeQuery("SELECT * FROM Notifications");
			do {
				Stmt.executeUpdate("INSERT INTO Notifications (subject,content,channel,date) " + "VALUES('"
						+ template.getSubject() + "'," + "'" + channel.getChannel() + "'," + "'" + notiCreationDate
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

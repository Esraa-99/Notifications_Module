package app;

import java.sql.*;
import Utility.*;

public class Console {
	public static void main(String args[]) {
		Source source = new DBSource("jdbc:mysql://localhost:3306/NotificationModule", "mohanad", "7526819");
		DBQueueHandler queueHandler = new DBQueueHandler(source);
		try {
			Connection con = DriverManager.getConnection(source.getSource());
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SMS");
			
			while (rs.next()) {
				int notificationID = Integer.parseInt(rs.getString("notificationID"));
				boolean dequeued = queueHandler.dequeueSMS(notificationID);
				String status = (dequeued) ? "success" : "failed";
				System.out.println("NotificationID: " + notificationID);
				System.out.println("Channel: SMS");
				System.out.println("Status: " + status);
			}
			
			rs = stmt.executeQuery("SELECT * FROM Email");
			
			while (rs.next()) {
				int notificationID = Integer.parseInt(rs.getString("notificationID"));
				boolean dequeued = queueHandler.dequeueEmail(notificationID);
				String status = (dequeued) ? "success" : "failed";
				System.out.println("NotificationID: " + notificationID);
				System.out.println("Channel: Email");
				System.out.println("Status: " + status);
			}
			
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

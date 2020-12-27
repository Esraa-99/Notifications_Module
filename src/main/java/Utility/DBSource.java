package Utility;

import java.sql.*;

import Controllers.NotificationTemplate;

public class DBSource extends Source {

	String username;
	String password;

	public DBSource(String url) {
		super(url);
		username = "root";
		password = "root";
	}

	public DBSource(String url, String username, String password) {
		super(url);
		this.username = username;
		this.password = password;
	}

	@Override
	public String getSource() {
		return super.getSource() + "?" + "user=" + username + "&password=" + password;
	}

	@Override
	public NotificationTemplate readSource(String target) {
		try {
			NotificationTemplate newTemplate = null;
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(source, username, password);
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM Templates WHERE subject='" + target + "'";
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				newTemplate = new NotificationTemplate();
				newTemplate.setPlaceholder(rs.getString("placeholder"));
				newTemplate.setSubject(rs.getString("subject"));
				newTemplate.setContent(rs.getString("content"));
			}
			rs.close();
			con.close();
			stmt.close();
			return newTemplate;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean writeToSource(NotificationTemplate template) {
		boolean result = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(source, username, password);
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM Templates WHERE subject='" + template.getSubject() + "'";
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.next()) {
				String placeholder = template.getPlaceholder();
				String subject = template.getSubject();
				String content = template.getContent();
				query = "INSERT INTO Templates (placeholder, subject, content) VALUES ('" + placeholder + "', '"
						+ subject + "', '" + content + "')";
				if (stmt.executeUpdate(query) > 0)
					result = true;
			}
			rs.close();
			con.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	@Override
	public boolean updateSource(String target, NotificationTemplate template) {
		boolean result = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(source, username, password);
			Statement stmt = con.createStatement();
			String placeholder = template.getPlaceholder();
			String subject = template.getSubject();
			String content = template.getContent();
			String query = "UPDATE Templates SET content='" + content + "', placeholder='" + placeholder
					+ "', subject='" + subject + "' WHERE subject='" + target + "'";
			if (stmt.executeUpdate(query) > 0)
				result = true;
			con.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	@Override
	public boolean deleteFromSource(String target) {
		boolean result = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(source, username, password);
			Statement stmt = con.createStatement();
			String query = "DELETE FROM Templates WHERE subject='" + target + "'";
			if (stmt.executeUpdate(query) > 0)
				result = true;
			con.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

}
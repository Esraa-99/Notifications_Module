package Notification_Module.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import Controllers.*;
import Utility.*;

@Path("/notification")
public class Notification {

	Source source = new DBSource("jdbc:mysql://localhost:3306/NotificationModule", "mohanad", "7526819");

	@POST
	@Path("/{channel}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void send(JSONObject notificationInfo, @PathParam("channel") String channel) {
		NotificationController notification = new NotificationController();
		try {
			notification.setChannel(notificationInfo.getJSONObject("channel"));
			notification.setSubject(notificationInfo.getString("subject"));
			notification.setValues(toStringArray(notificationInfo.getJSONArray("values")));
			QueueHandler queue = null;
			if (channel.equalsIgnoreCase("email")) {
				queue = new EmailQueueHandler(source);
			} else if (channel.equalsIgnoreCase("sms")) {
				queue = new SMSQueueHandler(source);
			}
			queue.queue(notification);
		} catch (JSONException e) {
			System.out.print(e);
		}
	}

	public static String[] toStringArray(JSONArray array) {
		if (array == null)
			return null;
		String[] arr = new String[array.length()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = array.optString(i);
		}
		return arr;
	}
}

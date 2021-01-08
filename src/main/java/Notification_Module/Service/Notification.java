package Notification_Module.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import Controllers.*;
import Utility.*;

@Path("/notification")
public class Notification {

	Source source = new DBSource("jdbc:mysql://localhost:3306/NotificationModule", "mohanad", "7526819");

	@POST
	@Path("/{channel}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void send(NotificationController notification, @PathParam("channel") String channel) {
		QueueHandler queue = null;
		if (channel.equalsIgnoreCase("email")) {
			queue = new EmailQueueHandler(source);
		} else if (channel.equalsIgnoreCase("sms")) {
			queue = new SMSQueueHandler(source);
			queue.queue(notification);
		}
	}
}

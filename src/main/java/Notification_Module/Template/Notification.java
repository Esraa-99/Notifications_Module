package Notification_Module.Template;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Notification")
public class Notification {

	Source source = new DBSource("jdbc:mysql://localhost:3306/NotificationModule", "mohanad", "7526819");

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public void send(NotificationController notification) {
		NotificationTemplate template = source.readSource(notification.getSubject());
		template.insertValues(notification.getValues());
		QueueHandler queue = new DBQueueHandler();
		queue.queing(template, notification.getChannel(), source);
	}
}

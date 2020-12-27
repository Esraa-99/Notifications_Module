package Notification_Module.Template;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Notification")
public class Notification {

	Source source = new DBSource("jdbc:mysql://localhost:3306/NotificationModule", "mohanad", "7526819");

	@POST
	@Path("/{channel}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void send(NotificationController notification, @PathParam("channel") String channel) {
		NotificationTemplate template = source.readSource(notification.getSubject());
		template.insertValues(notification.getValues());
		QueueHandler queue = new DBQueueHandler();
		Channel notificationChannel = null;
		if(channel.equalsIgnoreCase("email")) {
			notificationChannel = (Email) new Email();
			((Email) notificationChannel).setEmail_address(notification.getDestination());
		}
		else if(channel.equalsIgnoreCase("sms")) {
			notificationChannel = (SMS) new SMS();
			((SMS) notificationChannel).setPhone_no(notification.getDestination());
		}
		queue.queing(template, notificationChannel, source);
	}
}

package Notification_Module.Template;


public interface QueueHandler {
	
	public void queing(NotificationTemplate template, Channel channel, Source databaseCon);
}

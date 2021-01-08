package Utility;

import Controllers.NotificationTemplate;

public interface QueueHandler {

	public void queing(NotificationTemplate template, Channel channel);

	public boolean dequeueSMS(int notificationID);

	public boolean dequeueEmail(int notificationID);
}

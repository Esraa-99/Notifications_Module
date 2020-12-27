package Utility;

import Controllers.NotificationTemplate;

public interface Channel {
	public String getDestination();
	public void send(NotificationTemplate notification);
}

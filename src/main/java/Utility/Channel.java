package Utility;

import Controllers.NotificationTemplate;

public interface Channel {
	public void send(NotificationTemplate notification);
}

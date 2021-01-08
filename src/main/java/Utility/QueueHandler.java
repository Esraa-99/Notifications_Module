package Utility;

import Controllers.NotificationController;

public interface QueueHandler {
	public void queue(NotificationController notification);

	public boolean dequeue(int notificationID);
}

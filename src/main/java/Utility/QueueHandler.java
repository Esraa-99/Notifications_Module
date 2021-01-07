package Utility;

import Controllers.NotificationTemplate;

public interface QueueHandler {
	
	public void queing(NotificationTemplate template, Channel channel, Source databaseCon);
	public ArrayList <NotificationTemplate> dequeing(Channel channel, Source databaseCon);

}

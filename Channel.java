
public interface  Channel {
	String destination="";

	public default void setDestination(String destination) {
	}

	public default String getDestination() {
		return destination;
	}
	public default boolean verifyDestination() {
		return false;
	}
}
	

public interface Channel {
    String destination = new String();

    public default void setDestination(String destination) {
        if (verifyDestination(destination)) {
            setDestination(destination);
        }
    }

    public default String getDestination() {
        return destination;
    }

    public boolean verifyDestination(String destination);
}
	
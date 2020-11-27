
public class Sms implements Channel {
    @Override
    public boolean verifyDestination(String number) {
        try {
            int phoneNumber = Integer.parseInt(number);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}

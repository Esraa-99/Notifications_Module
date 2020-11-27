
public class Email implements Channel{

    @Override
    public boolean verifyDestination(String email) {
        return email.contains("@");
    }
}

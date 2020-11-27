import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        NotificationModule module = new NotificationModule();
        String placeholder = "{mohanadayman}";
        String subject = "{mohanadayman}";
        String content = "{mohanadayman} hello {mohanadayman} my name is {mohanadayman}";
        ArrayList<String> values = new ArrayList<>();
        values.add("mohanad");
        values.add("mohanad");

        module.createTemplate(subject, content, placeholder);
        module.fillTemplate(subject, values);
        System.out.println(module.filledTemplates.get(0).getContent());
    }
}

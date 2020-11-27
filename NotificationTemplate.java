import java.util.ArrayList;

public class NotificationTemplate {

    String subject;
    String content;
    String placeholder;
    ArrayList<Integer> placeholderIndices;

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholderIndices(ArrayList<Integer> placeholderIndices) {
        this.placeholderIndices = placeholderIndices;
    }

    public ArrayList<Integer> getPlaceholderIndices() {
        return placeholderIndices;
    }

    public void insertValues(ArrayList<String> values) {
        if (values.size() != placeholderIndices.size()) {
            if (values.size() < placeholderIndices.size()) {
                for (int i = values.size(); i < placeholderIndices.size(); i++)
                    values.add(null);
            } else {
                for (int i = placeholderIndices.size(); i < values.size(); i++)
                    values.remove(i);
            }
        }
        int k = 0;
        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == placeholder.charAt(0)) {
                String remove = "";
                for (int j = 0; j < placeholder.length(); j++) {
                    remove += content.charAt(i + j);
                }
                if (remove.equals(placeholder)) {
                    String part1 = content.substring(0, i);
                    String part2 = content.substring(i + placeholder.length());
                    content = part1 + values.get(k) + part2;
                    k++;
                }
            }
        }
    }
}

import java.util.ArrayList;

public class TemplateParser {

    private ArrayList<Integer> placeholderIndex = new ArrayList<>();

    public ArrayList<Integer> parse(String content, String placeholder) {
        if (placeholder.length() > 0 && content.length() > 0) {
            for (int i = 0; i < content.length(); i++) {
                if (content.charAt(i) == placeholder.charAt(0)) {
                    boolean flag = true;
                    for (int j = 0; j < placeholder.length(); j++) {
                        if (content.charAt(i + j) != placeholder.charAt(j)) {
                            flag = false;
                        }
                    }
                    if (flag) {
                        placeholderIndex.add(i);
                        i += placeholder.length();
                    }
                }
            }
        }
        return placeholderIndex;
    }

}

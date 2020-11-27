import java.util.ArrayList;

public class TemplateParser {
	
	private ArrayList <Integer> placeholderIndex;
	
	public ArrayList<Integer> parse(String content, String placeholder){
		String[] splitContent = content.split(placeholder);

		int placeholderLength = placeholder.length();
		int index = 0;

		for (String item : splitContent) {
			index += item.length();
			placeholderIndex.add(index);
			if (index + placeholderLength < content.length())
				index += placeholderLength;
		}

		return placeholderIndex;
	}
	
}

package model;

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
		int offset = 0;
		int max = placeholderIndices.size();
		if (values.size() < max)
			max = values.size();
		for (int i = 0; i < max; i++) {
			int end = placeholderIndices.get(i) + offset;
			int start = end + placeholder.length();
			if (end > content.length())
				end = content.length();
			if (start > content.length())
				start = content.length();
			String part1 = content.substring(0, end);
			String part2 = content.substring(start);
			content = part1 + values.get(i) + part2;

			if (values.get(i).length() >= placeholder.length()) {
				offset += values.get(i).length() - placeholder.length();
			} else
				offset -= placeholder.length() - values.get(i).length();
		}
	}
}

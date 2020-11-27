import java.util.ArrayList;

public class NotificationTemplate {
	
	String subject;
	String content;
	String placeholder;
	ArrayList<Integer> placeholderIndices;

	public void setSubject(String subject) {
		this.subject=subject;
	}
	public String getSubject() {
		return subject;
	}
	
	public void setContent(String content) {
		this.content=content;
	}
	public String getContent () {
		return content;
	}
	
	public void setPlaceholder(String placeholder) {
		this.placeholder=placeholder;
	}
	public String getPlaceholder () {
		return placeholder;
	}
	
	public void setPlaceholderIndices(ArrayList<Integer> placeholderIndices) {
		this.placeholderIndices=placeholderIndices;
	}
	public ArrayList<Integer> getPlaceholderIndices() {
		return placeholderIndices;
	}
	
	public void insertValues(ArrayList<String> values) {		
		// if values.size() != placeholderIndices.size()
			// equalize with empty strings
		// for loop, starting 0, ending at placeholderIndices.size()
		// if value is not empty string
			// replace placeholder with value at same index
	}
}

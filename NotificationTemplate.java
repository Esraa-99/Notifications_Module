import java.util.ArrayList;

public class NotificationTemplate {
	private String subject;
	private String content;
	private String placeholder;
	private ArrayList <String> values;
	TemplateParser parser;
	private ArrayList <NotificationTemplate> addedTemplates; //when function create is done n7ut feha l added
	NotificationTemplate (){
		
	}
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
	
	public void setValues(ArrayList<String> values) {
		this.values = values;
	}
	public ArrayList<String> getValues() {
		return values;
	}
	public void setParser(TemplateParser parser) {
		this.parser = parser;
	}
	public TemplateParser getParser() {
		return parser;
	}
	public void setAddedTemplates(ArrayList <NotificationTemplate> AddedTemplates) {
		this.addedTemplates=addedTemplates;
	}
	public ArrayList <NotificationTemplate> getAddedTemplates(){
		return addedTemplates;
	}
	
	

}

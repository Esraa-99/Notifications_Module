import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class NotificationModule {
	
	ArrayList <Notification>  notifications;
	ArrayList <NotificationTemplate> templates;
	ArrayList <NotificationTemplate> filledTemplates;
	private Scanner scanner;
	
	public void storeTemplates(String path) throws IOException {
		File folder = new File(path);
		if(folder.isDirectory()) {
			for (int i=0; i<templates.size(); i++ ){                    // iterate over templates
				if (!folder.exists()){
					String fileNum = "file"+Integer.toString(i); 
					File templateFile = new File(fileNum);                 // create file by number
					scanner = new Scanner(templateFile);   
					if (scanner.hasNextLine()) {
						BufferedWriter writer = new BufferedWriter(new FileWriter(fileNum));
						//templates.get(i).setSubject(scanner.nextLine());
						writer.write(templates.get(i).getSubject());
						writer.close();
							}
					while (scanner.hasNextLine()) {
						BufferedWriter writer = new BufferedWriter(new FileWriter(fileNum));
						writer.write(templates.get(i).getContent());
						writer.close();
						}
					}														
					                                                      
				}
			}	
		}
	public void readTemplates(String path) throws FileNotFoundException {
				File template = new File(path);   
				scanner = new Scanner(template);   // iterate over files                                        
				//ArrayList <NotificationTemplate> storedTemplates = new ArrayList<>();          // create new template object
				if (scanner.hasNextLine()) {
					System.out.println(scanner.nextLine());
				}
				while (scanner.hasNextLine()){ 
					System.out.println(scanner.nextLine());
				}
				scanner.close();															                                                     
			}
					         
	public void createTemplate(String subject, String content, String placeholder)  {
		
		TemplateParser parser = new TemplateParser();
		ArrayList<Integer> parsedContent = parser.parse(content, placeholder);
		parsedContent.add(1);
		NotificationTemplate newTemplate = new NotificationTemplate();
		
		newTemplate.setContent(content);
		newTemplate.setPlaceholder(parsedContent);
		newTemplate.setSubject(subject);
		
		templates.add(newTemplate);
		
	}
	
	public void updateTemplate(String subject, String newContent, String newPlaceholder) {
		// search for template by subject
		// get old template file index
		// delete old template file
		// update content in arraylist
		// update placeholder in arraylist
		// save new template file
	}
	
	public void deleteTemplate(String subject) {
		// search for template by subject
		// delete from arraylist
		// delete template file
	}
	
	public void fillTemplate(String subject, ArrayList<String> values) {  
		NotificationTemplate newTemplate = new NotificationTemplate();           // search for template by subject in templates
		for (int i=0; i<templates.size(); i++) {
			if (templates.get(i).getSubject().equalsIgnoreCase(subject)) {
				newTemplate.setSubject(templates.get(i).getSubject());              // create copy of template (newTemplate)
				newTemplate.setPlaceholder(templates.get(i).getPlaceholder());
				newTemplate.setContent(templates.get(i).getContent());
				newTemplate.insertValues(values);                                 
			}                  
			filledTemplates.add(newTemplate);                                       // add newTemplate to filledTemplates
		}
	}
	public NotificationTemplate findTemplate(String subject) {
		return null;
		// search for template by subject in arraylist
		// return null if not found
	}
	
	
}
import java.io.File;
import java.util.ArrayList;


public class NotificationModule {

    ArrayList<NotificationTemplate> templates;
    ArrayList<NotificationTemplate> filledTemplates;

    NotificationModule() {
        templates = new ArrayList<>();
        filledTemplates = new ArrayList<>();
    }

    public void storeTemplates(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            // iterate over templates
            // if file doesn't already exist
            // create file by number
            // read subject, content
            // write to file
        }
    }

    public void readTemplates(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            // iterate over files
            // create new template object
            // read first line, set as subject
            // read rest of file, set as content
            // add object to templates arraylist
        }
    }

    public void createTemplate(String subject, String content, String placeholder) {

        TemplateParser parser = new TemplateParser();
        ArrayList<Integer> parsedContent = parser.parse(content, placeholder);

        NotificationTemplate newTemplate = new NotificationTemplate();

        newTemplate.setContent(content);
        newTemplate.setPlaceholderIndices(parsedContent);
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
        // search for template by subject in templates
        // create copy of template (newTemplate)
        // newTemplate.insertValues(values)
        // add newTemplate to filledTemplates
    }

    public NotificationTemplate findTemplate(String subject) {
        // search for template by subject in arraylist
        // return null if not found
        return null;
    }
}
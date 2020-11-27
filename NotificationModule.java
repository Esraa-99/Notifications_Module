import java.io.File;
import java.util.ArrayList;


public class NotificationModule {

    ArrayList<NotificationTemplate> templates;
    ArrayList<NotificationTemplate> filledTemplates;

    NotificationModule() {
        templates = new ArrayList<>();
        filledTemplates = new ArrayList<>();
    }

    public void storeTemplate(Source source, String subject) {
        // search template by subject in arraylist
        // store placeholder, subject and content in one variable
        // call source.writeToSource()
    }

    public void readTemplate(Source source) {
        // call source.readSource()
        // create new template object
        // set first line as placeholder
        // set second line as subject
        // set remaining as content
        // add object to templates arraylist
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

    public void updateTemplate(Source source, String newContent, String newPlaceholder) {
        // read source
        if (!source.readSource().equals(null)) {
            // search for subject in arraylist
            // update content in arraylist
            // update placeholder in arraylist
            // call source.writeToSource() using newPlaceholder + subject + newContent
        }
    }

    public void deleteTemplate(Source source) {
        // read sources & search for subject
        // search for template by subject
        // delete from arraylist
        // call source.deleteSource()
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
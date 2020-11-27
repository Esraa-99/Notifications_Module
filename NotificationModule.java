import java.io.File;
import java.util.ArrayList;


public class NotificationModule {

    ArrayList<NotificationTemplate> templates;
    ArrayList<NotificationTemplate> filledTemplates;

    NotificationModule() {
        templates = new ArrayList<>();
        filledTemplates = new ArrayList<>();
    }

    public boolean createTemplate(String subject, String content, String placeholder) {

        TemplateParser parser = new TemplateParser();
        ArrayList<Integer> parsedContent = parser.parse(content, placeholder);

        if (!parsedContent.equals(null)) {
            NotificationTemplate newTemplate = new NotificationTemplate();
            newTemplate.setContent(content);
            newTemplate.setPlaceholderIndices(parsedContent);
            newTemplate.setSubject(subject);
            templates.add(newTemplate);
            return true;
        } else return false;

    }

    public boolean readTemplate(Source source) {
        // call source.readSource()
        if (!source.readSource().equals(null)) {
            // create new template object
            // set first line as placeholder
            // set second line as subject
            // set remaining as content
            // add object to templates arraylist
            return true;
        } else return false;
    }

    public boolean updateTemplate(Source source, String newContent, String newPlaceholder) {
        // read source
        if (!source.readSource().equals(null)) {
            // search for subject in arraylist
            // update content in arraylist
            // update placeholder in arraylist
            // call source.writeToSource() using newPlaceholder + subject + newContent
            return true;
        } else return false;
    }

    public boolean deleteTemplate(Source source) {
        // read sources & search for subject
        // search for template by subject
        // delete from arraylist
        // call source.deleteSource()
        return source.deleteSource();
    }

    public boolean storeTemplate(Source source, String subject) {
        // search template by subject in arraylist
        // store placeholder, subject and content in one variable
        // call source.writeToSource()
        if(!source.writeToSource("PLACE CONTENT HERE")) return false;
        else return true;
    }

    public void fillTemplate(String subject, ArrayList<String> values) {
        // search for template by subject in templates
        // create copy of template (newTemplate)
        // newTemplate.insertValues(values)
        // add newTemplate to filledTemplates
    }

    public NotificationTemplate findTemplate(String subject) {
        int i = 0;
        boolean found = false;
        while (i < templates.size()) {
            if (subject.equals(templates.get(i).getSubject())) {
                found = true;
                break;
            }
        }
        if (found)
            return templates.get(i);
        else
            return null;
    }
}

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


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
        String data = source.readSource();
        if (!source.readSource().equals(null)) {
            String placeholder = "";
            String subject = "";
            String content = "";
            int i = 0;
            // fetch placeholder
            while (i < data.length()) {
                if (data.charAt(i) == '\n') break;
                placeholder += data.charAt(i);
                i++;
            }
            // fetch subject
            while (i < data.length()) {
                if (data.charAt(i) == '\n') break;
                subject += data.charAt(i);
                i++;
            }
            // fetch content
            while (i < data.length()) {
                content += data.charAt(i);
                i++;
            }
            NotificationTemplate newTemplate = new NotificationTemplate();
            TemplateParser parser = new TemplateParser();
            ArrayList<Integer> parsedContent = parser.parse(content, placeholder);
            newTemplate.setPlaceholder(placeholder);
            newTemplate.setSubject(subject);
            newTemplate.setContent(content);
            newTemplate.setPlaceholderIndices(parsedContent);
            templates.add(newTemplate);
            return true;
        } else return false;
    }

    public boolean updateTemplate(Source source, String newContent, String newPlaceholder) {
        String data = source.readSource();
        boolean flag = false;
        if (!data.equals(null)) {
            int i = data.indexOf('\n') + 1;
            if (i >= 0) {
                String subject = "";
                for (; i < data.length(); i++) {
                    if (data.charAt(i) == '\n') break;
                    subject += data.charAt(i);
                }
                NotificationTemplate matchingTemplate = findTemplate(subject);
                matchingTemplate.setContent(newContent);
                matchingTemplate.setPlaceholder(newPlaceholder);
                data = newPlaceholder + '\n';
                data += subject + '\n';
                data += newContent;
                flag = source.writeToSource(data);
            }
        }
        return flag;
    }

    public boolean deleteTemplate(Source source) {
        boolean flag = false;
        String data = source.readSource();
        if (!data.equals(null)) {
            int i = data.indexOf('\n') + 1;
            if (i >= 0) {
                String subject = "";
                for (; i < data.length(); i++) {
                    if (data.charAt(i) == '\n') break;
                    subject += data.charAt(i);
                }
                NotificationTemplate matchingTemplate = findTemplate(subject);
                templates.remove(matchingTemplate);
                flag = source.deleteSource();
            }
        }
        return flag;
    }

    public boolean storeTemplate(Source source, String subject) {
        NotificationTemplate matchingTemplate = findTemplate(subject);
        boolean flag = false;
        if (!matchingTemplate.equals(null)) {
            String data = "";
            data += matchingTemplate.getPlaceholder() + "\n";
            data += matchingTemplate.getSubject() + "\n";
            data += matchingTemplate.getContent();
            flag = source.writeToSource(data);
        }
        return flag;
    }

    public void fillTemplate(String subject, ArrayList<String> values) {
        NotificationTemplate matchingTemplate = findTemplate(subject);
        if (!matchingTemplate.equals(null)) {
            NotificationTemplate newTemplate = new NotificationTemplate();
            newTemplate.setSubject(matchingTemplate.getSubject());
            newTemplate.setPlaceholder(matchingTemplate.getPlaceholder());
            newTemplate.setContent(matchingTemplate.getContent());
            newTemplate.setPlaceholderIndices(matchingTemplate.getPlaceholderIndices());
            newTemplate.insertValues(values);
            filledTemplates.add(newTemplate);
        }
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

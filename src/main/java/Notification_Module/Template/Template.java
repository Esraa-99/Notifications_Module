
package Notification_Module.Template;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/template")
public class Template {

	Source source = new DBSource("jdbc:mysql://localhost:3306/NotificationModule", "mohanad", "7526819");

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String createTemplate(NotificationTemplate template) {
		String placeholder = template.getPlaceholder();
		String subject = template.getSubject();
		String content = template.getContent();
		NotificationTemplate newTemplate = new NotificationTemplate();
		newTemplate.setContent(content);
		newTemplate.setSubject(subject);
		newTemplate.setPlaceholder(placeholder);
		return (source.writeToSource(newTemplate)) ? "true" : "false";
	}

	@GET
	@Path("{target}")
	@Produces(MediaType.APPLICATION_JSON)
	public NotificationTemplate readTemplate(@PathParam("target") String templateName) {
		return source.readSource(templateName);
	}

	@PUT
	@Path("{target}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateTemplate(NotificationTemplate template, @PathParam("target") String target) {
		return (source.updateSource(target, template)) ? "true" : "false";
	}

	@DELETE
	@Path("{target}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteTemplate(@PathParam("target") String target) {
		return (source.deleteFromSource(target)) ? "true" : "false";
	}
}

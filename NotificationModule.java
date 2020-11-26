import java.io.File;
import java.util.ArrayList;


public class NotificationModule {
	ArrayList <Notification>  notifications;
	ArrayList <NotificationTemplate> templates;
	NotificationModule(){
	}
	public void readTemplates(NotificationTemplate template){
		for (int i=0; i<template.getAddedTemplates().size(); i++) {
			System.out.println(template.getAddedTemplates());
		}		
	}
	public void displayTemplate (NotificationTemplate template) {
		for (int i=0; i<notifications.size(); i++) {
			System.out.println(templates.get(i).getSubject()+templates.get(i).getContent());
		}
	}
	

}

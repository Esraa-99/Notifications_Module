CREATE DATABASE NotificationModule;

CREATE TABLE Templates
(
    placeholder varchar(10) NOT NULL,
    subject varchar(100) NOT NULL,
    content TEXT NOT NULL,
    PRIMARY KEY (subject)
);

INSERT INTO Templates (placeholder, subject, content) VALUES ('{x}', 'Test Template', 'Hello, my name is {x}');

CREATE TABLE Notifications(
	notificationID  int NOT NULL AUTO_INCREMENT,
	subject varchar(100) NOT NULL,
    content TEXT NOT NULL,
    date date,
    destination varchar(100) NOT NULL,
    PRIMARY KEY (notificationID)
);

CREATE TABLE SMS(
	phoneNo    varchar(15) ,
	PRIMARY KEY (phoneNo)
);

CREATE TABLE Email(
	emailAddress    varchar(25),
	PRIMARY KEY (emailAddress)
);
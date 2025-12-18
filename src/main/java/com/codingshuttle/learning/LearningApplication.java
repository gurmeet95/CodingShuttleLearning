package com.codingshuttle.learning;

import com.codingshuttle.learning.impl.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearningApplication implements CommandLineRunner {
    // @Autowired  -Because injecting with Constructor
	final NotificationService notificationService; // injecting dependency, but not a good way
	// for production.This is called field dependency injection.
	public LearningApplication( NotificationService notificationService){
		//@Qualifier("email") removed it before NotificationService notificationService
		// because using Conditional property
		this.notificationService=notificationService; // Constructor DI
		// Preferred way
		// because we can make it final.
		// @Qualifier("email) will inject it to email class only

	}





	public static void main(String[] args)  {

		SpringApplication.run(LearningApplication.class, args);


	}
	@Override
	public void run(String... args)throws Exception{

      //notificationService=new EmailNotificationService();
	 //tightly coupled if we want to use sms service we need to change code.
	// to make it loosely coupled we are going to create bean of EmailNotification service.
		//if we create bean of both service we need to make one primary
	 notificationService.send("Hello");
	 // now working with bean loosely coupled.

	}


}

package com.codingshuttle.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearningApplication implements CommandLineRunner {
	@Autowired  //Suppose to inject this with bean
	PaymentWithBean payment;
	@Autowired
	PaymentWithBean payment1;


	public static void main(String[] args)  {

		SpringApplication.run(LearningApplication.class, args);
		//Traditional Java way to call method,but this is tight coupling.
		//Beans are nothing but java objects.
		PaymentService paymentService=new PaymentService();
		paymentService.pay();

	}
	@Override
	public void run(String... args)throws Exception{
		System.out.println(payment.hashCode());
		//These both bean will have same hashcode because default scope of bean is Singleton.
		System.out.println(payment1.hashCode());
		payment.paying(); //cannot call this from main static method
		payment1.paying();


	}
	//CommandLineRunner helps to execute extra piece of code after Spring setup finishes.

}

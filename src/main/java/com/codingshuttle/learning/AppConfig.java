package com.codingshuttle.learning;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
   // @Scope("prototype")
    public PaymentWithBean payment(){
        return new PaymentWithBean();
    }
    //this tell spring that i am manually creating bean you need to manage it.

}

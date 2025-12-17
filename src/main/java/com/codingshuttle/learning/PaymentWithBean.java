package com.codingshuttle.learning;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

//@Component   //commented because creating bean with @Bean
// Tells spring that object of this class you need
// to take care of.
public class PaymentWithBean {
    public void paying(){
        System.out.println("Bean are Paying");
    }


    @PostConstruct
    public void afterBeanConstruct(){
        System.out.println("Before Paying");
    }
    @PreDestroy
    public void beforeDestroy(){
        System.out.println("After paying");

    }
}
/*
@Service
@Controller
@Repository
 All these are extends from @Component and also work same like,
 telling Spring to manage been.
 @RestController coming from SpringWeb.
*/

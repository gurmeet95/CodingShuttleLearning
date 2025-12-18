package com.codingshuttle.learning.impl;

import com.codingshuttle.learning.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
//@Primary  //now will use qualifier
@Qualifier("sms")
@Component
@ConditionalOnProperty(name="notification.type",havingValue="sms")
public class SmsNotificationService implements NotificationService {
    @Override
    public void send(String message) {
        System.out.println("SMS Sending.."+message);
    }
}

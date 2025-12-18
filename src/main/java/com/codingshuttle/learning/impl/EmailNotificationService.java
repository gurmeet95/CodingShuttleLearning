package com.codingshuttle.learning.impl;

import com.codingshuttle.learning.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@Qualifier("emailif")
@ConditionalOnProperty(name="notification.type",havingValue="email")
// property from application.yaml
//now we do not require @qualifier in DI.
public class EmailNotificationService implements NotificationService {
    @Override
    public void send(String message) {
        System.out.println("Sending Email "+message);
    }
}

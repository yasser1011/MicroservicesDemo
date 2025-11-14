package com.yasser.ecommerce.kafka;

import com.yasser.ecommerce.notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationsConsumer {

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotifications(PaymentNotificationRequest paymentConfirmation) throws MessagingException {
        log.info("Consuming payment-topic {}", paymentConfirmation.toString());
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotifications(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Consuming order-topic {}", orderConfirmation.toString());

    }
}
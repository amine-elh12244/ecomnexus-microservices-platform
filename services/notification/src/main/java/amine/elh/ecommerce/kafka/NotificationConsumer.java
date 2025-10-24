package amine.elh.ecommerce.kafka;

import amine.elh.ecommerce.email.EmailService;
import amine.elh.ecommerce.kafka.order.OrderConfirmation;
import amine.elh.ecommerce.kafka.payment.PaymentConfirmation;
import amine.elh.ecommerce.notification.entities.Notification;
import amine.elh.ecommerce.notification.repositories.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static amine.elh.ecommerce.notification.enums.NotificationType.ORDER_CONFIRMATION;
import static amine.elh.ecommerce.notification.enums.NotificationType.PAYMENT_CONFIRMATION;


@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;

    private final EmailService emailService;


    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Payment confirmation: {}", paymentConfirmation);
        notificationRepository.save(
                Notification.builder()
                        .notificationType(PAYMENT_CONFIRMATION)
                        .notificationDateTime(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()

                );

        var customerName = paymentConfirmation.customerFirstname()  + " " + paymentConfirmation.customerLastname();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()

        );

    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Order confirmation: {}", orderConfirmation);
        notificationRepository.save(
                Notification.builder()
                        .notificationType(ORDER_CONFIRMATION)
                        .notificationDateTime(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()

        );

        var customerName = orderConfirmation.customer().firstname()  + " " + orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );

    }
}

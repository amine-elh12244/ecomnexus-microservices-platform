package amine.elh.ecommerce.notification.entities;


import amine.elh.ecommerce.kafka.order.OrderConfirmation;
import amine.elh.ecommerce.kafka.payment.PaymentConfirmation;
import amine.elh.ecommerce.notification.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document
public class Notification {

    @Id
    private String id;

    private NotificationType notificationType;

    private LocalDateTime notificationDateTime;

    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;

}

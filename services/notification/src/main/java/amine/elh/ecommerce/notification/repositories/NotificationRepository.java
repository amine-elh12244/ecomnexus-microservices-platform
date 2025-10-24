package amine.elh.ecommerce.notification.repositories;

import amine.elh.ecommerce.kafka.payment.PaymentConfirmation;
import amine.elh.ecommerce.notification.entities.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
}

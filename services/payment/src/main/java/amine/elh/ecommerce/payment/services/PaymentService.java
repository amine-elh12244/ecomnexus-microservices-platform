package amine.elh.ecommerce.payment.services;


import amine.elh.ecommerce.notification.NotificationProducer;
import amine.elh.ecommerce.notification.PaymentNotificationRequest;
import amine.elh.ecommerce.payment.dtos.PaymentRequest;
import amine.elh.ecommerce.payment.mappers.PaymentMapper;
import amine.elh.ecommerce.payment.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;



    public Integer createPayment(PaymentRequest paymentRequest) {
        var payment = paymentRepository.save(mapper.toPayment(paymentRequest));
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        paymentRequest.orderReference(),
                        paymentRequest.amount(),
                        paymentRequest.paymentMethod(),
                        paymentRequest.customer().firstname(),
                        paymentRequest.customer().lastname(),
                        paymentRequest.customer().email()

                )
        );

        return payment.getId();
    }
}

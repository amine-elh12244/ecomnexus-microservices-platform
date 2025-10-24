package amine.elh.ecommerce.order.clients.payment;

import amine.elh.ecommerce.order.clients.payment.dto.PaymentRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "payment-service",
        url ="${application.config.payment-url}"

)
public interface PaymentClient {

    @PostMapping
    Integer requestOrderPayment(@RequestBody PaymentRequest paymentRequest);
}

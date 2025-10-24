package amine.elh.ecommerce.payment.repositories;

import amine.elh.ecommerce.payment.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}

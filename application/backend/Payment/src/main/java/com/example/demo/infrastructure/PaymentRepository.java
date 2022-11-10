package com.example.demo.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}

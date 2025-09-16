package com.financial.paymentservice;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;  // You need to add this repository to get & update users

    @PostMapping("/process")
    public ResponseEntity<String> processPayment(@RequestBody Payment payment) {
        // Add transaction logic here

        User sender = userRepository.findById(payment.senderId).orElse(null);
        User recipient = userRepository.findById(payment.recipientId).orElse(null);

        if (sender == null || recipient == null) {
            return ResponseEntity.badRequest().body("Sender or recipient not found");
        }
        if (sender.balance.compareTo(payment.amount) < 0) {
            return ResponseEntity.badRequest().body("Insufficient balance");
        }

        sender.balance = sender.balance.subtract(payment.amount);
        recipient.balance = recipient.balance.add(payment.amount);
        userRepository.save(sender);
        userRepository.save(recipient);

        payment.status = "COMPLETED";
        paymentRepository.save(payment);

        return ResponseEntity.ok("Payment successful");
    }
}

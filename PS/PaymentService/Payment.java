package com.financial.paymentservice;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public Long senderId;
    public Long recipientId;
    public BigDecimal amount;
    public String description;
    public String status;
}

package com.financial.userservice;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String email;
    public String firstName;
    public String lastName;
    public BigDecimal balance;

    public User() {
        balance = new BigDecimal("1000.00");
    }
}

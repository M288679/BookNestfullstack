package com.example.bn1.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FinePayment {
    @Id
   
@GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;




private BigDecimal amount;

private LocalDateTime paymentDate;

public FinePayment(Long id, BigDecimal amount, LocalDateTime paymentDate) {
    this.id = id;
    this.amount = amount;
    this.paymentDate = paymentDate;
}

public FinePayment() {
}

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public BigDecimal getAmount() {
    return amount;
}

public void setAmount(BigDecimal amount) {
    this.amount = amount;
}

public LocalDateTime getPaymentDate() {
    return paymentDate;
}

public void setPaymentDate(LocalDateTime paymentDate) {
    this.paymentDate = paymentDate;
}


}

package com.vwallet.vwallet_backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
public class CreditCard {
    @Id
    @GeneratedValue
    private long id;

    private String iban;

    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate expiration;

    private String deposit;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    public CreditCard(String iban, LocalDate expiration, String deposit) {
        this.iban = iban;
        this.expiration = expiration;
        this.deposit = deposit;
    }

    public long getId() {
        return id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", iban='" + iban + '\'' +
                ", expiration=" + expiration +
                ", deposit='" + deposit + '\'' +
                '}';
    }
}

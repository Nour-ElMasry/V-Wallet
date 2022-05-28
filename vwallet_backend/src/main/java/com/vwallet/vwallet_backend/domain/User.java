package com.vwallet.vwallet_backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String password;
    private String phone;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;

        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
        @EqualsAndHashCode.Exclude
        private Set<CreditCard> creditCards = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    @EqualsAndHashCode.Exclude
    private Set<CryptoCurrency> cryptoCurrencies = new HashSet<>();

    public User(String name, String email, String password,String phone, LocalDate dateOfBirth, Address address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public void addCreditCard(CreditCard cc){
        cc.setUser(this);
        creditCards.add(cc);
    }

    public CreditCard getCreditCardById(long id){
        for (CreditCard cc: creditCards) {
            if (cc.getId() == id){
                return cc;
            }
        }
        return null;
    }

    public void addCrypto(CryptoCurrency cc){
        cc.setUser(this);
        cryptoCurrencies.add(cc);
    }

    public CryptoCurrency getCryptoById(long id){
        for (CryptoCurrency cc: cryptoCurrencies) {
            if (cc.getId() == id){
                return cc;
            }
        }
        return null;
    }
}

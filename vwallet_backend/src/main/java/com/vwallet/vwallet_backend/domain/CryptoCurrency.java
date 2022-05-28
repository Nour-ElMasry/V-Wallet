package com.vwallet.vwallet_backend.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class CryptoCurrency {
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String value;
    private String investment;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    public CryptoCurrency(String name, String value, String investment) {
        this.name = name;
        this.investment = investment;
    }

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getInvestment() {
        return investment;
    }

    public void setInvestment(String investment) {
        this.investment = investment;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CryptoCurrency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", investment='" + investment + '\'' +
                '}';
    }
}

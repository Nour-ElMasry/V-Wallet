package com.vwallet.vwallet_backend.services;

import com.vwallet.vwallet_backend.domain.CreditCard;
import com.vwallet.vwallet_backend.domain.CryptoCurrency;
import com.vwallet.vwallet_backend.domain.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void save(User u);
    List<User> findAll();
    User findById(long id);

    Set<CreditCard> getUserCreditCards(long id);
    void addCreditCard(long id, CreditCard cc);
    void removeCreditCard(long id, long ccId);
    void editCreditCard(long id, long ccId, CreditCard cc);

    Set<CryptoCurrency> getUserCryptos(long id);
    void addCrypto(long id, CryptoCurrency cc);
    void removeCrypto(long id, long ccId, double money);
}

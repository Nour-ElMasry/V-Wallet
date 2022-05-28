package com.vwallet.vwallet_backend.repositories;

import com.vwallet.vwallet_backend.domain.CreditCard;
import com.vwallet.vwallet_backend.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Component
public interface UserRepository extends CrudRepository<User, Long>{
    @Transactional
    @Modifying
    @Query("delete from CreditCard c where c.user.id = ?1 AND c.id = ?2")
    void removeCreditCard(long id, long ccId);

    @Transactional
    @Modifying
    @Query("delete from CryptoCurrency c where c.user.id = ?1 AND c.id = ?2")
    void removeCrypto(long id, long ccId);

    @Transactional
    @Modifying
    @Query("update CreditCard c set c.iban = ?3 , c.expiration = ?4 , c.deposit = ?5 where c.user.id = ?1 AND c.id = ?2")
    void editCreditCard(long id, long ccId, String iban, LocalDate expiration, String deposit);

    @Transactional
    @Modifying
    @Query("update CryptoCurrency c set c.name = ?3 , c.investment = ?4 where c.user.id = ?1 AND c.id = ?2")
    void editCrypto(long id, long ccId, String name, String investment);
}

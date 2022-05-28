package com.vwallet.vwallet_backend.services;

import com.vwallet.vwallet_backend.domain.CreditCard;
import com.vwallet.vwallet_backend.domain.CryptoCurrency;
import com.vwallet.vwallet_backend.domain.User;
import com.vwallet.vwallet_backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User u) {
        userRepository.save(u);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Set<CreditCard> getUserCreditCards(long id) {
        return Objects.requireNonNull(userRepository.findById(id).orElse(null)).getCreditCards();
    }

    @Override
    public void addCreditCard(long id, CreditCard cc) {
        User dbUser = findById(id);
        if(cc.getExpiration().isAfter(LocalDate.now())) {
            dbUser.addCreditCard(cc);
            userRepository.save(dbUser);
        }else{
            System.out.println("invalid Date");
        }

    }

    @Override
    public void removeCreditCard(long id, long ccId) {
        userRepository.removeCreditCard(id, ccId);
    }

    @Override
    public void editCreditCard(long id, long ccId,CreditCard cc) {
        userRepository.editCreditCard(id, ccId, cc.getIban(), cc.getExpiration(), cc.getDeposit());
    }

    @Override
    public Set<CryptoCurrency> getUserCryptos(long id) {
        return Objects.requireNonNull(userRepository.findById(id).orElse(null)).getCryptoCurrencies();
    }

    @Override
    public void addCrypto(long id, CryptoCurrency cc) {
        User dbUser = findById(id);
        double totalDeposit = 0;
        double investment = Double.parseDouble(cc.getInvestment());
        Set<CreditCard> creditCards = dbUser.getCreditCards();

        for(CreditCard c : creditCards)  {
            totalDeposit += Double.parseDouble(c.getDeposit());
        }

        if(totalDeposit >= investment){
            for (CreditCard c: creditCards) {
                double deposit = Double.parseDouble(c.getDeposit());

                if(investment == 0) break;

                if(investment > deposit){
                    investment -= deposit;
                    c.setDeposit("0");
                    editCreditCard(id , c.getId(), c);
                }else{
                    c.setDeposit(String.valueOf(deposit - investment));
                    investment = 0;
                    editCreditCard(id , c.getId(), c);
                }
            }
            dbUser.addCrypto(cc);
            userRepository.save(dbUser);
        }else{
            System.out.println("Insufficient funds");
        }
    }

    @Override
    public void removeCrypto(long id, long ccId, double money) {
        User dbUser = findById(id);
        Set<CreditCard> ccs = dbUser.getCreditCards();
        long moneyToSend = (long) (money/ccs.size());
        ccs.forEach(c -> c.setDeposit(String.valueOf(Float.parseFloat(c.getDeposit()) + moneyToSend)));
        userRepository.removeCrypto(id, ccId);
        userRepository.save(dbUser);
    }
}

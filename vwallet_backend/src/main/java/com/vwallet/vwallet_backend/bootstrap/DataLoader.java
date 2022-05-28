package com.vwallet.vwallet_backend.bootstrap;

import com.vwallet.vwallet_backend.domain.Address;
import com.vwallet.vwallet_backend.domain.CreditCard;
import com.vwallet.vwallet_backend.domain.User;
import com.vwallet.vwallet_backend.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
//    private final UserService userService;
//
//    public DataLoader(UserService userService) {
//        this.userService = userService;
//    }

    @Override
    public void run(String... args) throws Exception {
//        User u = new User("Nour", "nour@gmail.com", "Nour1234","0770284515", LocalDate.of(2001, 2, 16), new Address("Romania", "Bucharest", "Bd.Camil Ressu"));
//
//        // u.addCreditCard(new CreditCard("RO563728891029", LocalDate.of(2031, 2, 16), "2000"));
//        userService.save(u);
    }
}

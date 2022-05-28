package com.vwallet.vwallet_backend.RestController;

import com.vwallet.vwallet_backend.domain.CreditCard;
import com.vwallet.vwallet_backend.domain.CryptoCurrency;
import com.vwallet.vwallet_backend.domain.User;
import com.vwallet.vwallet_backend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class ControllerRest {
    private final UserService userService;

    public ControllerRest(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/creditCards")
    public ResponseEntity<Set<CreditCard>> getCreditCards(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.getUserCreditCards(id), HttpStatus.OK);
    }

    @PostMapping("/{id}/creditCards")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addCreditCard(@PathVariable("id") Long id, @RequestBody CreditCard cc){
        userService.addCreditCard(id, cc);
    }

    @DeleteMapping("/{id}/creditCards/{ccId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCreditCard(@PathVariable("id") Long id, @PathVariable("ccId") Long ccId){
        userService.removeCreditCard(id, ccId);
    }

    @PutMapping("/{id}/creditCards/{ccId}/{sendingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editCreditCard(@PathVariable("id") Long id, @PathVariable("ccId") Long ccId, @PathVariable("sendingId") Long sendingId,@RequestBody CreditCard cc){
        CreditCard ccSending = userService.findById(id).getCreditCardById(sendingId);
        ccSending.setDeposit(String.valueOf(Double.parseDouble(ccSending.getDeposit()) - Double.parseDouble(cc.getDeposit())));
        cc.setDeposit(String.valueOf(Double.parseDouble(cc.getDeposit()) + Double.parseDouble(userService.findById(id).getCreditCardById(ccId).getDeposit())));
        userService.editCreditCard(id, ccId, cc);
        userService.editCreditCard(id, sendingId, ccSending);
    }


    @GetMapping("/{id}/cryptoCurrencies")
    public ResponseEntity<Set<CryptoCurrency>> getCryptos(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.getUserCryptos(id), HttpStatus.OK);
    }

    @PostMapping("/{id}/cryptoCurrencies")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addCrypto(@PathVariable("id") Long id, @RequestBody CryptoCurrency cc){
        userService.addCrypto(id, cc);
    }

    @DeleteMapping("/{id}/cryptoCurrencies/{ccId}/{money}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCrypto(@PathVariable("id") Long id, @PathVariable("ccId") Long ccId, @PathVariable("money") String money){
        userService.removeCrypto(id, ccId, Double.parseDouble(money));
    }
}

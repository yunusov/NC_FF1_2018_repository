package ru.fulfilment1.ticketDealer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.fulfilment1.ticketDealer.entity.Account;
import ru.fulfilment1.ticketDealer.entity.Authority;
import ru.fulfilment1.ticketDealer.entity.Passenger;
import ru.fulfilment1.ticketDealer.form.PassengerForm;
import ru.fulfilment1.ticketDealer.repository.AccountRepository;
import ru.fulfilment1.ticketDealer.repository.PassengerRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean addAccount(Account account, Authority authority) {
        if (accountRepository.existsByUsername(account.getUsername())) {
            return false;
        }
        String password = account.getPassword();
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);
        account.setPassword(passwordEncoder.encode(password));
        account.setAuthorities(authorities);
        account.setActive(true);

        accountRepository.save(account);
        return true;
    }

    public void setPassenger(Account account, PassengerForm passengerForm) {
        Passenger passenger;

        if (passengerRepository.existsByAccount(account)) {
            passenger = passengerRepository.findByAccount(account);
        } else {
            passenger = new Passenger();
            passenger.setAccount(account);
        }
        passenger.setAll(passengerForm);

        passengerRepository.save(passenger);
    }

    public boolean changePassword(Account account, String oldPassword, String newPassword) {
        String currentPassword = account.getPassword();

        if (passwordEncoder.matches(oldPassword, currentPassword)) {
            String encodedPassword = passwordEncoder.encode(newPassword);
            account.setPassword(encodedPassword);
            accountRepository.save(account);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        return account;
    }
}

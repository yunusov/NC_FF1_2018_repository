package ru.fulfilment1.ticketDealer.service;

import org.springframework.stereotype.Service;
import ru.fulfilment1.ticketDealer.entity.Account;
import ru.fulfilment1.ticketDealer.entity.Authority;
import java.util.Set;

@Service
public class AuthorityService {

    public boolean isAdmin(Account account) {

        Set<Authority> authorities = (Set<Authority>) account.getAuthorities();

        for (Authority authority : authorities) {
            if (authority == Authority.ADMIN) {
                return true;
            }
        }
        return false;
    }

}

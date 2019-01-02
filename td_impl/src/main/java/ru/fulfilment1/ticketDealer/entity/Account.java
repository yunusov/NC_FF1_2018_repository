package ru.fulfilment1.ticketDealer.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "Account")

public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    private String username;
    private String email;
    private String password;
    private int balance;
    private boolean active;

    @ElementCollection(targetClass = Authority.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "account_authority", joinColumns = @JoinColumn(name = "account_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    private Set<Authority> authorities;

    public Account() {

    }

    public Account(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Authority> getAuthoritySet() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // WITHOUT IMPLEMENTATION ( ALWAYS TRUE )
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // WITHOUT IMPLEMENTATION ( ALWAYS TRUE )
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // WITHOUT IMPLEMENTATION ( ALWAYS TRUE )
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}

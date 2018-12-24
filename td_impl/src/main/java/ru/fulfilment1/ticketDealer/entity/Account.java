package ru.fulfilment1.ticketDealer.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "TD_USER")

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name", length = 64, nullable = false)
    private String username;

    @Column(name = "email", length = 64, nullable = false)
    private String email;

    @Column(name = "passwd", length = 64, nullable = false)
    private String password;
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "account_role", joinColumns = @JoinColumn(name = "account_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

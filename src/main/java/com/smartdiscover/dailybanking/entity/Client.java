package com.smartdiscover.dailybanking.entity;

import com.smartdiscover.dailybanking.model.CreateClientModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;

@Getter
@Setter
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @OneToMany(mappedBy = "client", cascade = ALL)
    private Set<Loan> loans;

    public Client() {

    }

    public Client(CreateClientModel model) {
        this.firstName = model.getFirstName();
        this.lastName = model.getLastName();
        this.email = model.getEmail();
    }

}

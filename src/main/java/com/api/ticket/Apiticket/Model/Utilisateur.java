package com.api.ticket.Apiticket.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "utilisateur")
@Data
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private String nom;
    private String email;
    private String password;
    private String role;

}
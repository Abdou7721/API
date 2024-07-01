package com.api.ticket.Apiticket.Model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Base_de_connaissance")
@Data
public class BaseConnaissance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String question;
    private String reponse;

}

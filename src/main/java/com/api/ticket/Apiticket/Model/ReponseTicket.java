package com.api.ticket.Apiticket.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "reponse")
@Data

public class ReponseTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id", nullable = false)
    private Ticket ticket;
    private String reponseTicket;
    private String dateReponse;

}

package com.api.ticket.Apiticket.controller;

import com.api.ticket.Apiticket.DTO.TicketDTO;
import com.api.ticket.Apiticket.Model.Ticket;
import com.api.ticket.Apiticket.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apprenant/")
@Data
public class ApprenantControleTicket {

    @Autowired
    private final TicketService ticketService;

    @Operation(description = "Teste de connection")
    @GetMapping("/connect")
    public String Welcome(){
        return " Welcome to Apiticket";
    }

    @Operation(description = "Création d'un ticket par un apprenant")
    @PostMapping("/soumettre")
    public TicketDTO soumettre(@RequestBody TicketDTO ticket) {
        return ticketService.soumettre(ticket);
    }

    @Operation(description = "L'apprenant peut voir la liste de ses ticket ici")
    @GetMapping("/listTicket")
    public List<Ticket> listTicket() {
        return ticketService.liste();
    }

    @Operation(description = "Pour modifier un ticket")
    @PutMapping("/modifierTicket/{id}")
    public Ticket modifier(@PathVariable Long id, @RequestBody Ticket ticket) {
        return ticketService.modifier(id,ticket);
    }


    @Operation(description = "Pour supprimer un ticket")
    @DeleteMapping("/suppTicket/{id}")
    public String supp(@PathVariable Long id) {
        return ticketService.supprimer(id);
    }
}

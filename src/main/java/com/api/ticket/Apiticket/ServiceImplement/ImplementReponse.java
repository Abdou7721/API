package com.api.ticket.Apiticket.ServiceImplement;

import com.api.ticket.Apiticket.Model.BaseConnaissance;
import com.api.ticket.Apiticket.Model.ReponseTicket;
import com.api.ticket.Apiticket.Model.Ticket;
import com.api.ticket.Apiticket.repository.BaseReponsitory;
import com.api.ticket.Apiticket.repository.ReponseRepository;
import com.api.ticket.Apiticket.repository.TIcketRepository;
import com.api.ticket.Apiticket.service.MailService;
import com.api.ticket.Apiticket.service.ReponseService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Data
public class ImplementReponse implements ReponseService {

    @Autowired
    private final ReponseRepository reponseRepository;

    @Autowired
    private final BaseReponsitory baseReponsitory;

    @Autowired
    private final TIcketRepository ticketRepository;

    @Autowired
    private MailService emailService;

    @Override
    public ReponseTicket CreerReponse(ReponseTicket reponse) {
        // Retrieve the ticket by its ID
        Ticket ticket = ticketRepository.findById(reponse.getTicket().getId())
                .orElseThrow(() -> new RuntimeException("Ticket not found with id " + reponse.getTicket().getId()));

        // Set ticket status to "en-cours"
        ticket.setStatus("en-cours");
        Ticket t= ticketRepository.save(ticket);
        try {
            notifyUsers(t, "New Ticket Created", "A new ticket has been created:\n\n" + t);
        } catch (Exception e) {
            // Log the error
            System.err.println("Failed to send notification email: " + e.getMessage());
        }
        return  reponseRepository.save(reponse);
    }

    @Override
    public ReponseTicket EnvoiReponse(ReponseTicket reponse) {
        // Retrieve the ticket by its ID
        ReponseTicket Reticket = reponseRepository.findById(reponse.getId())
                .orElseThrow(() -> new RuntimeException("Ticket not found with id " + reponse.getId()));

        Ticket ticket = ticketRepository.findById(reponse.getTicket().getId())
                .orElseThrow(() -> new RuntimeException("Ticket not found with id " + reponse.getTicket().getId()));


        // Set ticket status to "résolu"
        ticket.setStatus("résolu");
        ticketRepository.save(ticket);

        try {
            notify(Reticket, "New Ticket Created", "A new ticket has been created:\n\n" + Reticket);
        } catch (Exception e) {
            // Log the error
            System.err.println("Failed to send notification email: " + e.getMessage());
        }

        return reponse;
    }

    public BaseConnaissance saveBaseConnaissance(BaseConnaissance baseConnaissance) {
        return baseReponsitory.save(baseConnaissance);
    }

    @Override
    public List<ReponseTicket> ListReponse() {

        return reponseRepository.findAll();
    }

    @Override
    public ReponseTicket ModifierReponse(Long id, ReponseTicket reponse ) {
        return reponseRepository.findById(id)
                .map(r ->{

                    return reponseRepository.save(r);
                }).orElseThrow(() -> new RuntimeException("Reponse n'existe pas"));
    }



    @Override
    public String supprimerReponse(Long id ) {
        reponseRepository.deleteById(id);
        return "La reponse a été supprimé ! par ";
    }


    private void notifyUsers(Ticket ticket, String subject, String body) {
        // Suppose you have a list of user emails
        List<String> userEmails = List.of("doolcoumba@gmail.com", "samakebakary036@gmail.com");

        for (String email : userEmails) {
            emailService.sendEmail(email, subject, body);
        }
    }
    private void notify(ReponseTicket ticket, String subject, String body) {
        // Suppose you have a list of user emails
        List<String> userEmails = List.of("doolcoumba@gmail.com", "samakebakary036@gmail.com");

        for (String email : userEmails) {
            emailService.sendEmail(email, subject, body);
        }
    }
}

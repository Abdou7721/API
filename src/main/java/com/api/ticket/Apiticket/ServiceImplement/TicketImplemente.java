package com.api.ticket.Apiticket.ServiceImplement;

import com.api.ticket.Apiticket.DTO.TicketDTO;
import com.api.ticket.Apiticket.Model.Category;
import com.api.ticket.Apiticket.Model.Ticket;
import com.api.ticket.Apiticket.repository.CategoryRepository;
import com.api.ticket.Apiticket.repository.TIcketRepository;
import com.api.ticket.Apiticket.service.MailService;
import com.api.ticket.Apiticket.service.TicketService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class TicketImplemente implements TicketService {

    @Autowired
    private final TIcketRepository ticketRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MailService emailService;

    private TicketDTO convertToDTO(Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticket.getId());

        ticketDTO.setDescription(ticket.getDescription());
        ticketDTO.setCategory(ticket.getCategory().getName());
        ticketDTO.setPriority(ticket.getPriority());
        ticketDTO.setStatus(ticket.getStatus());
        return ticketDTO;
    }

    private Ticket convertToEntity(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketDTO.getId());

        ticket.setDescription(ticketDTO.getDescription());
        Category category = categoryRepository.findById(Long.valueOf(ticketDTO.getCategory()))
                .orElseThrow(() -> new RuntimeException("Category not found"));
        ticket.setCategory(category);
        switch (category.getName().toUpperCase()) {
            case "TECHNIQUE":
                ticket.setPriority("Elevée");
                break;
            case "PRATIQUE":
                ticket.setPriority("Moyenne");
                break;
            case "THEORIQUE":
                ticket.setPriority("Faible");
                break;
            default:
                ticket.setPriority(" ");
                break;
        }
        ticket.setStatus(ticketDTO.getStatus());
        return ticket;
    }



    @Override
    public TicketDTO soumettre(TicketDTO ticket) {
        Ticket tck = convertToEntity(ticket);

        Ticket t = ticketRepository.save(tck);
        try {
            notifyUsers(t, "New Ticket Created", "A new ticket has been created:\n\n" + t);
        } catch (Exception e) {
            // Log the error
            System.err.println("Failed to send notification email: " + e.getMessage());
        }

        return convertToDTO(t);

    }

    @Override
    public List<Ticket> liste () {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket modifier(Long id, Ticket ticket) {
        Ticket t = ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found with id " + id));


        t.setDescription(ticket.getDescription());
        t.setCategory(ticket.getCategory());
        t.setPriority(ticket.getPriority());
        t.setStatus(ticket.getStatus());


        Ticket updatedTicket = ticketRepository.save(ticket);
        try {
            notifyUsers(updatedTicket, "Ticket Updated", "A ticket has been updated:\n\n" + updatedTicket);
        } catch (Exception e) {
            // Log the error
            System.err.println("Failed to send notification email: " + e.getMessage());
        }
        return updatedTicket;


    }

    @Override
    public String supprimer (Long id ) {
        ticketRepository.deleteById(id);
        return "";
    }


    private void notifyUsers(Ticket ticket, String subject, String body) {
        // Suppose you have a list of user emails
        List<String> userEmails = List.of("doolcoumba@gmail.com", "samakebakary036@gmail.com");

        for (String email : userEmails) {
            emailService.sendEmail(email, subject, body);
        }
    }
}

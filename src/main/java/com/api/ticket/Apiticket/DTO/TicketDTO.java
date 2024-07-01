package com.api.ticket.Apiticket.DTO;

import lombok.Data;

@Data
public class TicketDTO {
    private Long id;
    private String title;
    private String description;
    private String category;
    private String priority;
    private String status;
}

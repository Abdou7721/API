package com.api.ticket.Apiticket.service;

import com.api.ticket.Apiticket.Model.BaseConnaissance;
import com.api.ticket.Apiticket.Model.ReponseTicket;

import java.util.List;

public interface ReponseService {
    ReponseTicket CreerReponse(ReponseTicket reponse);
    ReponseTicket EnvoiReponse(ReponseTicket reponse);

    List<ReponseTicket> ListReponse();


    ReponseTicket ModifierReponse(Long id, ReponseTicket reponse);

    String supprimerReponse(Long id);

    BaseConnaissance saveBaseConnaissance(BaseConnaissance baseConnaissance);

}

package com.api.ticket.Apiticket.service;

import com.api.ticket.Apiticket.Model.Utilisateur;

import java.util.List;

public interface AdminService {
   Utilisateur creer (Utilisateur utilisateur);

    List<Utilisateur> afficher();

    Utilisateur modifier(Long id, Utilisateur utilisateur );

    String supprimer(Long id);
}

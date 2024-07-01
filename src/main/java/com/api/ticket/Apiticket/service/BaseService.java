package com.api.ticket.Apiticket.service;

import com.api.ticket.Apiticket.Model.BaseConnaissance;
import com.api.ticket.Apiticket.Model.Utilisateur;

import java.util.List;

public interface BaseService {
    BaseConnaissance creerbase (BaseConnaissance connaissance);

    List<BaseConnaissance> afficheuserbase();

    BaseConnaissance modifierbase(Long id, BaseConnaissance connaissance );

    String supprimerbase(Long id);
}


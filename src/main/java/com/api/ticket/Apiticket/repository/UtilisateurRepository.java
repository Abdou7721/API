package com.api.ticket.Apiticket.repository;

import com.api.ticket.Apiticket.Model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository <Utilisateur,Long> {
    Optional <Utilisateur> findByEmail(String email);

}

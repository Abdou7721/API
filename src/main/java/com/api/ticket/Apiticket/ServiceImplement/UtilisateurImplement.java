package com.api.ticket.Apiticket.ServiceImplement;

import com.api.ticket.Apiticket.Model.Utilisateur;
import com.api.ticket.Apiticket.repository.UtilisateurRepository;
import com.api.ticket.Apiticket.service.AdminService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class UtilisateurImplement implements AdminService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Utilisateur creer(Utilisateur utilisateur) {
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public List<Utilisateur> afficher() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateur modifier(Long id, Utilisateur utilisateur) {
        return utilisateurRepository.findById(id)
                .map(p ->{
                    p.setNom(utilisateur.getNom());
                    p.setEmail(utilisateur.getEmail());
                    p.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
                    p.setRole(utilisateur.getRole());
                    return utilisateurRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Pas d'utilisateur"));
    }

    @Override
    public String supprimer(Long id) {
        utilisateurRepository.deleteById(id);
        return "Utilisateur a été supprimer !";
    }
}

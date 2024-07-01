package com.api.ticket.Apiticket.service;

import com.api.ticket.Apiticket.Model.Utilisateur;
import com.api.ticket.Apiticket.repository.UtilisateurRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@Data
public class ServiceUtilisateur implements UserDetailsService {

    @Autowired
    private  final UtilisateurRepository utilisateurRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByEmail(email);

        if (utilisateur.isPresent()){
            var obj = utilisateur.get();
            return User.builder()
                    .username(obj.getEmail())
                    .password(obj.getPassword())
                    .roles(obj.getRole())
                    .build();

        }else {
            throw new UsernameNotFoundException(email);
        }
    }
    private String[]getRole(Utilisateur utilisateur){
        if (utilisateur.getRole()==null){
            return new String[] {"ADMIN"};
        }
        return  utilisateur.getRole().split(",");

    }

}

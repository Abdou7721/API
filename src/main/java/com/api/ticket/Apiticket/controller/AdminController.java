package com.api.ticket.Apiticket.controller;

import com.api.ticket.Apiticket.Model.Utilisateur;
import com.api.ticket.Apiticket.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/")
@Data
public class AdminController {

    @Autowired
    private final AdminService adminService;

    @Operation(description = "Teste de connection")
    @GetMapping("/connect")
    public String Welcome(){
        return " Welcome to Apiticket";
    }

    @Operation(description = "Pour ajouter un utilisateur")
    @PostMapping("/Create")
    public Utilisateur Create(@RequestBody Utilisateur utilisateur) {
        return adminService.creer(utilisateur);
    }
    @Operation(description = "Lister les utilisateurs")
    @GetMapping("/liste")
    public List<Utilisateur> liste() {
        return adminService.afficher();
    }

    @Operation(description = "Modifier des utilisateurs ")
    @PutMapping("/modifier/{id}")
    public Utilisateur modifier(@PathVariable long id , @RequestBody Utilisateur utilisateur) {
        return  adminService.modifier(id,utilisateur);
    }

    @Operation(description = "Pour supprimer un utilisateur")
    @DeleteMapping("/supprimer/{id}")
    public String supprimer(@PathVariable long id) {
        return adminService.supprimer(id);
    }

}

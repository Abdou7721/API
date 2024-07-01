package com.api.ticket.Apiticket.controller;

import com.api.ticket.Apiticket.Model.BaseConnaissance;
import com.api.ticket.Apiticket.Model.ReponseTicket;
import com.api.ticket.Apiticket.service.ReponseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formateur/")
@Data
public class FormateurController {

    @Autowired
    private final ReponseService reponseService;

    @Operation(description = "Teste de connection")
    @GetMapping("/connect")
    public String Welcome(){
        return " Welcome to Apiticket";
    }


    @Operation(description = "Création d'une reponse par le formateur")
    @PostMapping("/creerReponse")
    public ReponseTicket createReponse(@RequestBody ReponseTicket reponse) {
        return reponseService.CreerReponse(reponse);
    }

    @Operation(description = "L'envoi de la reponse créée")
    @PostMapping("/EnvoiReponse")
    public ReponseTicket EnvoyReponse(@RequestBody ReponseTicket reponse) {
        return reponseService.EnvoiReponse(reponse);
    }

    @Operation(description = "Le formateur peut voir la liste des reponses ici")
    @GetMapping("/listReponse")
    public List<ReponseTicket> list() {
        return reponseService.ListReponse();
    }

    @Operation(description = "Pour Modifier un ticket")
    @PutMapping("/modifierReponse/{id}")
    public ReponseTicket modifier(@PathVariable long id , ReponseTicket reponse) {
        return reponseService.ModifierReponse(id, reponse);
    }

    @Operation(description = "Pour supprimer un ticket")
    @DeleteMapping("/suppReponse/{id}")
   String delete(@PathVariable long id ) {

      return   reponseService.supprimerReponse(id);

    }

    @Operation(description = "Création d'une base de connaissance")
    @PostMapping("/creerBaseConnaissance")
    public BaseConnaissance creerBaseConnaissance(@RequestBody BaseConnaissance baseConnaissance) {
        return reponseService.saveBaseConnaissance(baseConnaissance);
    }
}

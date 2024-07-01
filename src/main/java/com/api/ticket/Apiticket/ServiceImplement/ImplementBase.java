package com.api.ticket.Apiticket.ServiceImplement;

import com.api.ticket.Apiticket.Model.BaseConnaissance;
import com.api.ticket.Apiticket.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplementBase implements BaseService {
    @Override
    public BaseConnaissance creerbase(BaseConnaissance connaissance) {
        return null;
    }

    @Override
    public List<BaseConnaissance> afficheuserbase() {
        return List.of();
    }

    @Override
    public BaseConnaissance modifierbase(Long id, BaseConnaissance connaissance) {
        return null;
    }

    @Override
    public String supprimerbase(Long id) {
        return "";
    }
}

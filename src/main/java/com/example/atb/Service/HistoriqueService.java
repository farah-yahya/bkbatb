package com.example.atb.Service;

import com.example.atb.Entities.Historique;

import java.util.List;

public interface HistoriqueService {
    Historique addHistorique(Historique historique);
    void deleteHistorique(long id);
    Historique updateHistorique(long id, Historique historique);
    List<Historique> getHistoriques();
    Historique getHistoriqueById(long id);
    List<Historique> getHistoriquesByEquipementId(Long equipementId);

}


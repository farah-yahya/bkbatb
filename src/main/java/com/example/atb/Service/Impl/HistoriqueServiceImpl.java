package com.example.atb.Service.Impl;

import com.example.atb.Entities.Historique;
import com.example.atb.Repository.HistoriqueRepository;
import com.example.atb.Service.HistoriqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoriqueServiceImpl implements HistoriqueService {
    private final HistoriqueRepository historiqueRepository;

    @Override
    public Historique addHistorique(Historique historique) {
        return historiqueRepository.save(historique);
    }

    @Override
    public void deleteHistorique(long id) {
        if (historiqueRepository.existsById(id)) {
            historiqueRepository.deleteById(id);
        }
    }

    @Override
    public Historique updateHistorique(long id, Historique historique) {
        if (historiqueRepository.existsById(id)) {
            historique.setId(id);
            historique.setEtat(historique.getEtat());
            historique.setDatePrise(historique.getDatePrise());
            historique.setDateRetour(historique.getDateRetour());
            historique.setNombreModifications(historique.getNombreModifications());
            historique.setUser(historique.getUser());
            return historiqueRepository.save(historique);
        }
        return null;
    }

    @Override
    public List<Historique> getHistoriques() {
        return historiqueRepository.findAll();
    }

    @Override
    public Historique getHistoriqueById(long id) {
        return historiqueRepository.findById(id).orElse(null);
    }
    @Override
    public List<Historique> getHistoriquesByEquipementId(Long equipementId) {
        return historiqueRepository.findByEquipementId(equipementId);
    }
}

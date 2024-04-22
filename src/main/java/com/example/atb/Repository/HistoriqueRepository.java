package com.example.atb.Repository;

import com.example.atb.Entities.Historique;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoriqueRepository extends JpaRepository<Historique, Long> {
    List<Historique> findByEquipementId(long equipementId);
}
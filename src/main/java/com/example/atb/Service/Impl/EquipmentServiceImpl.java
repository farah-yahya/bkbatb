package com.example.atb.Service.Impl;

import com.example.atb.Entities.Equipment;
import com.example.atb.Entities.Historique;
import com.example.atb.Entities.User;
import com.example.atb.Repository.EquipmentRepository;
import com.example.atb.Repository.HistoriqueRepository;
import com.example.atb.Repository.UserRepository;
import com.example.atb.Service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final HistoriqueRepository historiqueRepository;
    private final UserRepository userRepository;

    @Override
    public Equipment addEquipment(Equipment equipment) {
        Equipment equipment1 = new Equipment();
        Date currentDate = new Date();
        if (equipment.getEntree().before(currentDate) && equipment.getSortie().after(currentDate)) {
            equipment.setEtat("Indisponible");
            Object
                    principal
                    = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if
            (principal instanceof UserDetails userDetails) {
                equipment1 = equipmentRepository.save(equipment);
                String
                        username
                        = userDetails.getUsername();
                User user = userRepository.findByEmail(userDetails.getUsername()).get();
                Historique historique = new Historique();
                historique.setDatePrise(new Date());
                historique.setEquipement(equipment1);
                historique.setUser(user);
                historique.setDateRetour(equipment1.getSortie());
                historiqueRepository.save(historique);
            }
        }else {
            equipment.setEtat("Disponible");
            equipment1 = equipmentRepository.save(equipment);
        }
            return equipment1;
    }

    @Override
    public void deleteEquipment(long id) {
        if (equipmentRepository.existsById(id)) {
            equipmentRepository.deleteById(id);
        }
    }

    @Override
    public Equipment updateEquipment(long id, Equipment equipment) {
        if (equipmentRepository.existsById(id)) {
            Equipment equipment1 = equipmentRepository.findById(id).get();
            equipment1.setName(equipment.getName());
            equipment1.setCategory(equipment.getCategory());
            equipment1.setEntree(equipment.getEntree());
            equipment1.setSortie(equipment.getSortie());
            return equipmentRepository.save(equipment1);
        }
        return null;
    }

    @Override
    public List<Equipment> getEquipments() {
        return equipmentRepository.findAll();
    }

    @Override
    public Equipment getEquipmentById(long id) {
        return equipmentRepository.findById(id).orElse(null);
    }
}

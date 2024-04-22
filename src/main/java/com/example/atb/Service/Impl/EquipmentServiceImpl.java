package com.example.atb.Service.Impl;

import com.example.atb.Entities.Equipment;
import com.example.atb.Repository.EquipmentRepository;
import com.example.atb.Service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentRepository equipmentRepository;
    @Override
    public Equipment addEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
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

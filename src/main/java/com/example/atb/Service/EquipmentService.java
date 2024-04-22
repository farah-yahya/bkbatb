package com.example.atb.Service;

import com.example.atb.Entities.Equipment;

import java.util.List;

public interface EquipmentService {

    Equipment addEquipment(Equipment equipment);
    void deleteEquipment(long id);
    Equipment updateEquipment(long id, Equipment equipment);
    List<Equipment> getEquipments();
    Equipment getEquipmentById(long id);

}
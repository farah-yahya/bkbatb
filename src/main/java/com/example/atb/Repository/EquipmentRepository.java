package com.example.atb.Repository;

import com.example.atb.Entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EquipmentRepository extends JpaRepository<Equipment, Long>  {}
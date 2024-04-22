package com.example.atb.Controller;

import com.example.atb.Entities.Equipment;
import com.example.atb.Service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/equipment")
@RequiredArgsConstructor

public class EquipmentController {
    private final EquipmentService equipmentService;

    @PostMapping
    public ResponseEntity<Equipment> createEquipment(@RequestBody Equipment equipment) {
        return new ResponseEntity<Equipment>(equipmentService.addEquipment(equipment), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<Iterable<Equipment>> getAllEquipment() {
        return new ResponseEntity<Iterable<Equipment>>(equipmentService.getEquipments(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable Long id) {
        return new ResponseEntity<Equipment>(equipmentService.getEquipmentById(id), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Equipment> updateEquipment(@PathVariable Long id, @RequestBody Equipment equipment) {
        return new ResponseEntity<Equipment>(equipmentService.updateEquipment(id, equipment), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}

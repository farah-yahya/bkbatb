package com.example.atb.Controller;

import com.example.atb.Entities.Historique;
import com.example.atb.Service.HistoriqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/historique")
@RequiredArgsConstructor
public class HistoriqueController {
    private final HistoriqueService historiqueService;

    @GetMapping
    public ResponseEntity<Iterable<Historique>> getAllHistorique() {
        return new ResponseEntity<Iterable<Historique>>(historiqueService.getHistoriques(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Historique> getHistoriqueById(@PathVariable long id) {
        return new ResponseEntity<Historique>(historiqueService.getHistoriqueById(id), HttpStatus.OK);
    }

    @GetMapping("/equipement/{equipementId}")
    public ResponseEntity<Iterable<Historique>> getHistoriqueByEquipementId(@PathVariable Long equipementId) {
        return new ResponseEntity<Iterable<Historique>>(historiqueService.getHistoriquesByEquipementId(equipementId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Historique> addHistorique(@RequestBody Historique historique) {
        return new ResponseEntity<Historique>(historiqueService.addHistorique(historique), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Historique> updateHistorique(@PathVariable long id, @RequestBody Historique historique) {
        return new ResponseEntity<Historique>(historiqueService.updateHistorique(id, historique), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistorique(@PathVariable long id) {
        historiqueService.deleteHistorique(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}

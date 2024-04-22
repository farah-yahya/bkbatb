package com.example.atb.Controller;

import com.example.atb.Entities.Atb;
import com.example.atb.Service.AtbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/atb")
@RequiredArgsConstructor
public class AtbController {
    private final AtbService atbService;

    @PostMapping
    public ResponseEntity<Atb> createAtb(@RequestBody Atb atb) {
        return new ResponseEntity<Atb>(atbService.addAtb(atb), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<Iterable<Atb>> getAllAtb() {
        return new ResponseEntity<Iterable<Atb>>(atbService.getAtbs(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Atb> getAtbById(@PathVariable Long id) {
        return new ResponseEntity<Atb>(atbService.getAtbById(id), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Atb> updateAtb(@PathVariable Long id, @RequestBody Atb atb) {
        return new ResponseEntity<Atb>(atbService.updateAtb(id, atb), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAtb(@PathVariable Long id) {
        atbService.deleteAtb(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

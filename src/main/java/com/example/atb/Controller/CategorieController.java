package com.example.atb.Controller;

import com.example.atb.Entities.Categorie;
import com.example.atb.Service.CategorieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/categorie")
@RequiredArgsConstructor

public class CategorieController {
    private final CategorieService categorieService;

    @GetMapping
    public ResponseEntity<List<Categorie>> getCategorie() {
        return new ResponseEntity<List<Categorie>>(categorieService.getCategories(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Categorie> createCategorie(@RequestBody Categorie categorie) {
        return new ResponseEntity<Categorie>(categorieService.addCategorie(categorie), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Categorie> getCategorieById(@PathVariable Long id) {
        return new ResponseEntity<Categorie>(categorieService.getCategorieById(id), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable Long id, @RequestBody Categorie categorie) {
        return new ResponseEntity<Categorie>(categorieService.updateCategorie(id, categorie), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategorie(@PathVariable Long id) {
        categorieService.deleteCategorie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

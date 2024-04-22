package com.example.atb.Service.Impl;

import com.example.atb.Entities.Categorie;
import com.example.atb.Repository.CategorieRepository;
import com.example.atb.Service.CategorieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategorieServiceImpl implements CategorieService {
    private final CategorieRepository categorieRepository;
    @Override
    public Categorie addCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public void deleteCategorie(long id) {
        if (categorieRepository.existsById(id)) {
            categorieRepository.deleteById(id);
        }
    }

    @Override
    public Categorie updateCategorie(long id, Categorie categorie) {
        if (categorieRepository.existsById(id)) {
            Categorie categorie1 = categorieRepository.findById(id).get();
            categorie1.setNomCategorie(categorie.getNomCategorie());
            return categorieRepository.save(categorie1);
        }
        return null;
    }

    @Override
    public List<Categorie> getCategories() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie getCategorieById(long id) {
        return categorieRepository.findById(id).orElse(null);
    }
}

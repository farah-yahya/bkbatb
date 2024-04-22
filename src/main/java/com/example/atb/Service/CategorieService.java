package com.example.atb.Service;

import com.example.atb.Entities.Categorie;

import java.util.List;

public interface CategorieService {
    Categorie addCategorie(Categorie categorie);
    void deleteCategorie(long id);
    Categorie updateCategorie(long id, Categorie categorie);
    List<Categorie> getCategories();
    Categorie getCategorieById(long id);
}

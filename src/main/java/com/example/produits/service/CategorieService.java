package com.example.produits.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.produits.entities.Categorie;




public interface CategorieService  {

	List<Categorie> getAllCategories();
	Categorie saveCategorie(Categorie categorie);
	Page<Categorie> getAllCategoriesParPage(int i, int j);
	void deleteCategorieById(Long id);
	Categorie getCategories(Long id);
	Categorie updateCategories(Categorie c);
}

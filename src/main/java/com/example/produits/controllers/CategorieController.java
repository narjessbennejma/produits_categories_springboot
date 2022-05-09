package com.example.produits.controllers;

import java.text.ParseException;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.produits.entities.Categorie;

import com.example.produits.service.CategorieService;

@Controller
public class CategorieController {
	@Autowired
	CategorieService  categorieService;
	
	@RequestMapping("/showCreatecategorie")
	public String showCreateCat(ModelMap modelMap)
	{
		modelMap.addAttribute("categorie", new Categorie());
		modelMap.addAttribute("mode", "new");
		return "formCategorie";
	}
	
	
	@RequestMapping("/saveCategorie")
	public String saveCategorie(@Valid Categorie categorie,
	BindingResult bindingResult)
	{
		if (bindingResult.hasErrors()) return "formCategorie";
		 
		categorieService.saveCategorie(categorie);
		return "redirect:/ListeCategorie";
	}
	
	
	@RequestMapping("/ListeCategorie")
	public String listeCategories(ModelMap modelMap,
	@RequestParam (name="page",defaultValue = "0") int page,
	@RequestParam (name="size", defaultValue = "3") int size)
	{
	Page<Categorie> cat = categorieService.getAllCategoriesParPage(page, size);
	modelMap.addAttribute("categories", cat);
	 modelMap.addAttribute("pages", new int[cat.getTotalPages()]);
	modelMap.addAttribute("currentPage", page);
	modelMap.addAttribute("size", size);
	return "listeCategories"; 
	}
	
	
	@RequestMapping("/supprimerCategories")
	public String supprimerCategories(@RequestParam("id") Long id,
	ModelMap modelMap,
	@RequestParam (name="page",defaultValue = "0") int page,
	@RequestParam (name="size", defaultValue = "3") int size)
	{
		categorieService.deleteCategorieById(id);
	Page<Categorie> cat = categorieService.getAllCategoriesParPage(page, size);
	modelMap.addAttribute("categories", cat);
	modelMap.addAttribute("pages", new int[cat.getTotalPages()]);
	modelMap.addAttribute("currentPage", page);
	modelMap.addAttribute("size", size);
	return "redirect:/ListeCategorie";
	}
	
	@RequestMapping("/modifierCategorie")
	public String editerCategorie(@RequestParam("id") Long id,ModelMap modelMap)
	{
	Categorie c= categorieService.getCategories(id);
	modelMap.addAttribute("categorie", c);
	modelMap.addAttribute("mode", "edit");
	return "formCategorie";
	}

	@RequestMapping("/updateCategorie")
	public String updateCategorie(@ModelAttribute("categorie") Categorie categorie,
	
	ModelMap modelMap) throws ParseException 
	{
	
	
	 categorieService.updateCategories(categorie);
	 List<Categorie> cat = categorieService.getAllCategories();
	 modelMap.addAttribute("categories", cat);
	 
	    
	return "listeCategories";
	
}
}
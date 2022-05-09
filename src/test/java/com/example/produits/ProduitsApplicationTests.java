package com.example.produits;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.example.produits.entities.Categorie;
import com.example.produits.entities.Produit;
import com.example.produits.repos.ProduitRepository;
import com.example.produits.service.ProduitService;

@SpringBootTest
class ProduitsApplicationTests {
	
	@Autowired
	private ProduitRepository produitRepository;
	@Autowired
	private ProduitService  produitService;
	/*@Test
	public void testCreateProduit() {
		Produit prod = new Produit("pc Dell", 2500.000, new Date());
		produitRepository.save(prod);
	}*/

	@Test
	public void testFindProduit() {
		Produit p = produitRepository.findById(1L).get();
		System.out.println(p);
	}

	@Test
	public void testUpdateProduit() {
		Produit p = produitRepository.findById(3L).get();
		p.setPrixProduit(3000.0);
		produitRepository.save(p);
	}

	@Test
	public void testDeleteProduit() {
		produitRepository.deleteById(1L);
		;
	}

	@Test
	public void testListerTousProduits() {
		List<Produit> prods = produitRepository.findAll();
		for (Produit p : prods) {
			System.out.println(p);
		}
	}
	@Test
	public void testFindByNomProduitContains()
	{
	
	Page<Produit> prods = produitService.getAllProduitsParPage(0,2);
	System.out.println(prods.getSize());
	System.out.println(prods.getTotalElements());
	System.out.println(prods.getTotalPages());
	prods.getContent().forEach(p -> {System.out.println(p.toString());
	 });
	/*ou bien
	for (Produit p : prods)
	{
	System.out.println(p);
	} */
	}
	 @Test
	 public void testFindByNomProduit()
	 {
	 List<Produit> prods = produitRepository.findByNomProduit("pc HP");
	 for (Produit p : prods)
	 {
	 System.out.println(p);
	 }
	 }
	 @Test
	 public void testFindByNomProduitContains1 ()
	 {
	 List<Produit> prods=produitRepository.findByNomProduitContains("pc dell");
	 for (Produit p : prods)
	 {
	 System.out.println(p);
	 } }
	 @Test
	 public void testfindByNomPrix()
	 {
	 List<Produit> prods = produitRepository.findByNomPrix("pc dell", 2500.0);
	 for (Produit p : prods)
	 {
	 System.out.println(p);
	 }
	 }
	 @Test
	 public void testfindByCategorie()
	 {
	 Categorie cat = new Categorie();
	 cat.setIdCat(1L);
	 List<Produit> prods = produitRepository.findByCategorie(cat);
	 for (Produit p : prods)
	 {
	 System.out.println(p);
	 }
	 }
	 @Test
	 public void findByCategorieIdCat()
	 {
	 List<Produit> prods = produitRepository.findByCategorieIdCat(2L);
	 for (Produit p : prods)
	 {
	 System.out.println(p);
	 }
	  }
	 @Test
	 public void testfindByOrderByNomProduitAsc()
	 {
	 List<Produit> prods = 
	 produitRepository.findByOrderByNomProduitAsc();
	 for (Produit p : prods)
	 {
	 System.out.println(p);
	 }
	 }
	 @Test
	 public void testTrierProduitsNomsPrix()
	 {
	 List<Produit> prods = produitRepository.trierProduitsNomsPrix();
	 for (Produit p : prods)
	 {
	 System.out.println(p);
	 }
	 }

}
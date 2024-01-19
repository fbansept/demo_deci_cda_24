package edu.fbansept.demo.controllers;

import edu.fbansept.demo.dao.CategoryDao;
import edu.fbansept.demo.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getProduit(@PathVariable int id){
        Optional<Category> optionalCategory = categoryDao.findById(id);

        if(optionalCategory.isPresent()){
            return new ResponseEntity<>(optionalCategory.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/categories")
    public List<Category> getCategories(){
      List<Category> categories = categoryDao.findAll();
        return categories;
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Integer> deleteCategory(@PathVariable int id){

        boolean exist = categoryDao.existsById(id);

        if(exist) {
            categoryDao.deleteById(id);
            return new ResponseEntity<>(id,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {

        //Est ce que l'utilisateur n'a pas fourni d'ID (cad il veut créer un nouveau produit)
        if(category.getId() == null) {
            categoryDao.save(category);
            return new ResponseEntity<>(category,HttpStatus.CREATED);
        } else {
            //si l'utilisateur a fourni un id (cad il veut modifier un produit)

            boolean exist = categoryDao.existsById(category.getId());

            //si le produit exist bien en bdd on le met à jour
            if(exist) {
                categoryDao.save(category);
                return new ResponseEntity<>(category,HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

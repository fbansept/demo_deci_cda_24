package edu.fbansept.demo.controllers;

import edu.fbansept.demo.dao.ProductDao;
import edu.fbansept.demo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduit(@PathVariable int id){
        Optional<Product> optionalProduct = productDao.findById(id);

        if(optionalProduct.isPresent()){
            return new ResponseEntity<>(optionalProduct.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/products")
    public List<Product> getProducts(){
      List<Product> products = productDao.findAll();
        return products;
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Integer> deleteProduct(@PathVariable int id){

        boolean exist = productDao.existsById(id);

        if(exist) {
            productDao.deleteById(id);
            return new ResponseEntity<>(id,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {

        //Est ce que l'utilisateur n'a pas fourni d'ID (cad il veut créer un nouveau produit)
        if(product.getId() == null) {
            productDao.save(product);
            return new ResponseEntity<>(product,HttpStatus.CREATED);
        } else {
            //si l'utilisateur a fourni un id (cad il veut modifier un produit)

            boolean exist = productDao.existsById(product.getId());

            //si le produit exist bien en bdd on le met à jour
            if(exist) {
                productDao.save(product);
                return new ResponseEntity<>(product,HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }
}

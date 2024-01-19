package edu.fbansept.demo.controllers;

import edu.fbansept.demo.dao.TagDao;
import edu.fbansept.demo.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TagController {

    @Autowired
    private TagDao tagDao;

    @GetMapping("/tag/{id}")
    public ResponseEntity<Tag> getProduit(@PathVariable int id){
        Optional<Tag> optionalTag = tagDao.findById(id);

        if(optionalTag.isPresent()){
            return new ResponseEntity<>(optionalTag.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/tags")
    public List<Tag> getTags(){
      List<Tag> tags = tagDao.findAll();
        return tags;
    }

    @DeleteMapping("/tag/{id}")
    public ResponseEntity<Integer> deleteTag(@PathVariable int id){

        boolean exist = tagDao.existsById(id);

        if(exist) {
            tagDao.deleteById(id);
            return new ResponseEntity<>(id,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/tag")
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {

        //Est ce que l'utilisateur n'a pas fourni d'ID (cad il veut créer un nouveau produit)
        if(tag.getId() == null) {
            tagDao.save(tag);
            return new ResponseEntity<>(tag,HttpStatus.CREATED);
        } else {
            //si l'utilisateur a fourni un id (cad il veut modifier un produit)

            boolean exist = tagDao.existsById(tag.getId());

            //si le produit exist bien en bdd on le met à jour
            if(exist) {
                tagDao.save(tag);
                return new ResponseEntity<>(tag,HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

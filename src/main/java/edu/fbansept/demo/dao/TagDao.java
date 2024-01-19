package edu.fbansept.demo.dao;

import edu.fbansept.demo.models.Category;
import edu.fbansept.demo.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagDao extends JpaRepository<Tag, Integer> {
}

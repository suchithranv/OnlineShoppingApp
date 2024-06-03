package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.model.Category;


//CategoryRepository interface extends JpaRepository and adds custom query methods for a Category entity with an Integer type primary key
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
	// Checks if a category with the given name exist
	public Boolean existsByName(String name);
   
	// Finds all active categories
	public List<Category> findByIsActiveTrue();
	
	//retrieve all categories from the database
	public List<Category> findAll();

}


/**JPA stands for Java Persistence API. 
It's a Java specification for accessing, persisting, and managing data between Java objects/classes and a relational database. 
JPA provides a standardized way to interact with databases using object-oriented paradigms, 
reducing the need for developers to write SQL queries manually.**/

/**Spring Data JPA :
It allows you to define query methods simply by declaring methods in your repository interfaces. 
The method names follow a specific pattern which is parsed by Spring Data JPA to generate the corresponding SQL queries. 
It generates corresponding SQL queries

findByIsActiveTrue() :  
  --findBy : prefix indicates we are performing query to retrieve data from db
  --IsActive : name of the field in the Category entity
  --True : isActive field should be true
When the method findByIsActiveTrue is called, Spring Data JPA translates it into a SQL query :SELECT * FROM category WHERE is_active = true;
This query retrieves all records from the category table where the is_active column has the value true

existsByName(String name) :
  --existsBy: This prefix indicates that we are performing an existence check.
  --name: This is the name of the field in the Category entity.  
checks if Category entity with a given name exists in the database
Translation : SELECT 
              CASE WHEN COUNT(c) > 0 
                   THEN TRUE ELSE FALSE 
              END FROM category c WHERE c.name = ?1;
              
save(category) : save if new else update if old

findAll() : SELECT * FROM category;  This query retrieves all records from the category table.

findById(id) :  SELECT * FROM category WHERE id = ?;

delete(category) : Spring Data JPA will translate  to DELETE FROM category WHERE id = :id

**/
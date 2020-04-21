package com.denisab85.dishes.repositories;

import org.springframework.data.repository.CrudRepository;
import com.denisab85.dishes.entities.Category;

public interface DishCategoryRepository extends CrudRepository<Category, Integer> {

}
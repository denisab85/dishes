package com.denisab85.dishes.repositories;

import org.springframework.data.repository.CrudRepository;
import com.denisab85.dishes.entities.DishCategory;

public interface DishCategoryRepository extends CrudRepository<DishCategory, Integer> {

}
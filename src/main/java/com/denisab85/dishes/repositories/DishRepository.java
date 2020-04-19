package com.denisab85.dishes.repositories;

import org.springframework.data.repository.CrudRepository;
import com.denisab85.dishes.entities.Dish;

public interface DishRepository extends CrudRepository<Dish, Integer> {

}
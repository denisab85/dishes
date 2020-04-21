package com.denisab85.dishes.repositories;

import com.denisab85.dishes.entities.Dish;
import com.denisab85.dishes.repositories.naturalid.NaturalRepository;

public interface DishRepository extends NaturalRepository<Dish, String> {

}
package com.denisab85.dishes.repositories;

import org.springframework.data.repository.CrudRepository;

import com.denisab85.dishes.entities.Cuisine;

public interface CuisineRepository extends CrudRepository<Cuisine, Integer> {

}
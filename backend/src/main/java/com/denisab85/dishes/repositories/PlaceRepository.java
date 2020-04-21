package com.denisab85.dishes.repositories;

import org.springframework.data.repository.CrudRepository;
import com.denisab85.dishes.entities.Place;

public interface PlaceRepository extends CrudRepository<Place, Integer> {

}
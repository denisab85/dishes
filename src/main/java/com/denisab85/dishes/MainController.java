package com.denisab85.dishes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.denisab85.dishes.entities.Cuisine;
import com.denisab85.dishes.entities.Place;
import com.denisab85.dishes.repositories.CuisineRepository;
import com.denisab85.dishes.repositories.PlaceRepository;

@Controller
@RequestMapping(path = "/dishes")
public class MainController {

	@Autowired
	private CuisineRepository cuisineRepository;

	@Autowired
	private PlaceRepository placeRepository;

	@PostMapping(path = "/cuisine/add")
	public @ResponseBody String addNewCuisine(@RequestParam String name, @RequestParam(required = false) Cuisine parentCuisine) {
		Cuisine cuisine = new Cuisine();
		cuisine.setName(name);
		cuisine.setParentCuisine(parentCuisine);
		cuisineRepository.save(cuisine);
		return "Saved";
	}

	@GetMapping(path = "/cuisine/all")
	public @ResponseBody Iterable<Cuisine> getAllUsers() {
		return cuisineRepository.findAll();
	}

	@GetMapping(path = "/place/all")
	public @ResponseBody Iterable<Place> getAllPlaces() {
		return placeRepository.findAll();
	}
}
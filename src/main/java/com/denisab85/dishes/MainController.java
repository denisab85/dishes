package com.denisab85.dishes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.denisab85.dishes.entities.*;
import com.denisab85.dishes.repositories.*;

@Controller
@RequestMapping(path = "/dishes")
public class MainController {

	@Autowired
	private CuisineRepository cuisineRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private PlaceRepository placeRepository;

	@Autowired
	private DishRepository dishRepository;

	@PostMapping(path = "/cuisine/add")
	public @ResponseBody Integer addNewCuisine(@RequestParam String name, @RequestParam(required = false) Cuisine parentCuisine) {
		Cuisine cuisine = new Cuisine();
		cuisine.setName(name);
		cuisine.setParentCuisine(parentCuisine);
		cuisine = cuisineRepository.save(cuisine);
		return cuisine.getId();
	}

	@GetMapping(path = "/cuisine/all")
	public @ResponseBody Iterable<Cuisine> getAllUsers() {
		return cuisineRepository.findAll();
	}

	@PostMapping(path = "/category/add")
	public @ResponseBody String addNewCategory(@RequestParam String name) {
		Category category = new Category();
		category.setName(name);
		categoryRepository.save(category);
		return "Saved";
	}

	@GetMapping(path = "/category/all")
	public @ResponseBody Iterable<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@GetMapping(path = "/category/{name}")
	public @ResponseBody Category getCategory(@PathVariable("name") String name) {
		return categoryRepository.findBySimpleNaturalId(name).get();
	}

	@GetMapping(path = "/dish/all")
	public @ResponseBody Iterable<Dish> getAllDishes() {
		return dishRepository.findAll();
	}

	@PostMapping(path = "/dish/add")
	public @ResponseBody String addNewDish(@RequestParam String name) {
		Dish dish = new Dish();
		dish.setName(name);
		dishRepository.save(dish);
		return "Saved";
	}

	@GetMapping(path = "/dish/{name}")
	public @ResponseBody Dish getDish(@PathVariable("name") String name) {
		return dishRepository.findBySimpleNaturalId(name).get();
	}

	@PostMapping(path = "/dish/{dishName}/addCategory/{categoryName}")
	public @ResponseBody String addDishCategory(@PathVariable String dishName, @PathVariable String categoryName) {
		Dish dish = getDish(dishName);
		Category category = getCategory(categoryName);
		dish.getCategories().add(category);
		dishRepository.save(dish);
		return "Saved";
	}

	@GetMapping(path = "/place/all")
	public @ResponseBody Iterable<Place> getAllPlaces() {
		return placeRepository.findAll();
	}
}
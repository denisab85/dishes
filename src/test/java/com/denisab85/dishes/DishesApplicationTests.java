package com.denisab85.dishes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import com.denisab85.dishes.entities.Category;
import com.denisab85.dishes.entities.Dish;
import com.denisab85.dishes.repositories.CategoryRepository;
import com.denisab85.dishes.repositories.CuisineRepository;
import com.denisab85.dishes.repositories.DishRepository;
import com.denisab85.dishes.repositories.PlaceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class DishesApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private CuisineRepository cuisineRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private PlaceRepository placeRepository;

	@Autowired
	private DishRepository dishRepository;

	@BeforeAll
	void databaseSetup() {
		dishRepository.save(new Dish("Tom Yum"));
		categoryRepository.save(new Category("Soups"));
	}

	@Test
	void addCuisine() throws Exception {
		ResultActions result = mockMvc.perform(post("/dishes/cuisine/add").contentType(MediaType.APPLICATION_JSON).param("name", "Asian"))
				.andExpect(status().isOk());
		String id = objectMapper.readValue(result.andReturn().getResponse().getContentAsByteArray(), String.class);
		mockMvc.perform(post("/dishes/cuisine/add").contentType(MediaType.APPLICATION_JSON).param("name", "Thai").param("parentCuisine", id))
				.andExpect(status().isOk());
	}

	@Test
	void addCategory() throws Exception {
		for (String category : new String[] { "Appetizers", "Pasta", "Salads" }) {
			mockMvc.perform(post("/dishes/category/add").contentType(MediaType.APPLICATION_JSON).param("name", category)).andExpect(status().isOk());
		}
	}

	@Test
	void addDish() throws Exception {
		mockMvc.perform(post("/dishes/dish/add").contentType(MediaType.APPLICATION_JSON).param("name", "Chicken Curry")).andExpect(status().isOk());
	}

	@Test
	void getDish() throws Exception {
		String name = "Tom Yum";
		ResultActions result = mockMvc.perform(get("/dishes/dish/{name}", name).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		Dish dish = objectMapper.readValue(result.andReturn().getResponse().getContentAsByteArray(), Dish.class);
		Assertions.assertEquals(name, dish.getName());
	}

	@Test
	void addDishCategory() throws Exception {
		String dishName = "Tom Yum";
		String categoryName = "Soups";

		Dish dish = new Dish(dishName);
		Category category = new Category(categoryName);

		//		dishRepository.save(dish);
		//		categoryRepository.save(category);

		mockMvc.perform(post("/dishes/dish/{dishName}/addCategory/{categoryName}", dishName, categoryName).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		dish = dishRepository.findBySimpleNaturalId(dishName).get();
		Assertions.assertTrue(dish.getCategories().contains(category));
	}

}

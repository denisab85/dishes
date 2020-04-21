package com.denisab85.dishes.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Cuisine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Setter
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "id")
	@Setter
	private Cuisine parentCuisine;

}
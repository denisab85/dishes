package com.denisab85.dishes.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Type {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Getter
	@Setter
	private String name;

}

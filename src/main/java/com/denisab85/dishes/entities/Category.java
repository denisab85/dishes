package com.denisab85.dishes.entities;

import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NaturalIdCache
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NoArgsConstructor
@RequiredArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Setter
	@NaturalId
	@NonNull
	private String name;

	@ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL)
	private Set<Dish> dishes;

	//Getters and setters omitted for brevity

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Category category = (Category) o;
		return Objects.equals(name, category.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

}

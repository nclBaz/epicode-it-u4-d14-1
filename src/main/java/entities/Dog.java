package entities;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dogs")
// @DiscriminatorValue("Cane")
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name = "findByName", query = "SELECT d FROM Dog d WHERE d.name = :name")
public class Dog extends Animal {
	private double maxSpeed;
	private String breed;

	public Dog(String name, int age, double maxSpeed, String breed) {
		super(name, age);
		this.maxSpeed = maxSpeed;
		this.breed = breed;
	}

}

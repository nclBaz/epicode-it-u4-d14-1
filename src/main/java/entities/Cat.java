package entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cats")
// @DiscriminatorValue("Gatto")
@Getter
@Setter
@NoArgsConstructor
public class Cat extends Animal {
	private double maxJumpHeight;

	public Cat(String name, int age, double maxJumpHeight) {
		super(name, age);
		this.maxJumpHeight = maxJumpHeight;
	}

	@Override
	public String toString() {
		return "Cat [maxJumpHeight=" + maxJumpHeight + ", getId()=" + getId() + ", getName()=" + getName()
				+ ", getAge()=" + getAge() + "]";
	}

}

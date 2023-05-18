package entities;

import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
// @DiscriminatorColumn(name = "animal_type")
@Getter
@Setter
@NoArgsConstructor
public class Animal {
	@Id
	@GeneratedValue
	private UUID id;
	private String name;
	private int age;

	public Animal(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}

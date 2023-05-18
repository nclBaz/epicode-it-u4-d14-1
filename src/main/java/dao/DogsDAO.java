package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Dog;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DogsDAO {
	private final EntityManager em;

	public DogsDAO(EntityManager em) {
		this.em = em;
	}

	public void saveDog(Dog d) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(d);
		t.commit();
		log.info("Cane salvato!");
	}

	public Dog findById(String id) {
		Dog found = em.find(Dog.class, UUID.fromString(id));
		return found;
	}
}

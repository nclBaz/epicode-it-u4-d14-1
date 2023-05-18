package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entities.Cat;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CatsDAO {
	private final EntityManager em;

	public CatsDAO(EntityManager em) {
		this.em = em;
	}

	public void saveCat(Cat d) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(d);
		t.commit();
		log.info("Gatto salvato!");
	}

	public Cat findById(String id) {
		Cat found = em.find(Cat.class, UUID.fromString(id));
		return found;
	}

	public List<Cat> findAllCats() {
		TypedQuery<Cat> getAllQuery = em.createQuery("SELECT c FROM Cat c", Cat.class);
		// SELECT * FROM animals WHERE animal_type = 'Gatto'
		return getAllQuery.getResultList();
	}
}

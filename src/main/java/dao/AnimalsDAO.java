package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entities.Animal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnimalsDAO {
	private final EntityManager em;

	public AnimalsDAO(EntityManager em) {
		this.em = em;
	}

	public void saveAnimal(Animal a) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(a);
		t.commit();
		log.info("Animale salvato!");
	}

	public Animal findById(String id) {
		Animal found = em.find(Animal.class, UUID.fromString(id));
		return found;
	}

	public List<Animal> findAll(){
		TypedQuery<Animal> getAllQuery = em.createQuery("SELECT a FROM Animal a", Animal.class);
		// SELECT * FROM animals
		return getAllQuery.getResultList();
	}
}

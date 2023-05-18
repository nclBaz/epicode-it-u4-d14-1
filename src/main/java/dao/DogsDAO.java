package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

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

	public int findByIdAndUpdate(String id, String name) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		Query q = em.createQuery("UPDATE Dog d SET name=:name WHERE id = :id");
		q.setParameter("name", name);
		q.setParameter("id", UUID.fromString(id));
		int numModificati = q.executeUpdate();

		t.commit();
		return numModificati;
	}

	public int findByIdAndDelete(String id) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		Query q = em.createQuery("DELETE FROM Dog WHERE id = :id");
		q.setParameter("id", UUID.fromString(id));
		int numCancellati = q.executeUpdate();
		t.commit();
		return numCancellati;
	}
}

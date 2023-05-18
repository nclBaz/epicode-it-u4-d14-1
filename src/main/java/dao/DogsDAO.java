package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

	public List<Dog> findByName(String name) {
		TypedQuery<Dog> q = em.createNamedQuery("findByName", Dog.class);
		q.setParameter("name", name);
		return q.getResultList();
	}
	
	public List<Dog> findByNameAndAgeWithCriteria(String name, int age){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Dog> crQ = cb.createQuery(Dog.class);
		
		Root<Dog> dog = crQ.from(Dog.class); // riferimento all'entity Dog --> FROM Dog dog
		
		// Filters
		Predicate nameStartsWith = cb.like(dog.get("name"), name + "%");
		Predicate ageLowerThan = cb.lt(dog.get("age"), age);

		// aggiungo SELECT dog FROM Dog dog WHERE filters ORDER BY name DESC
		crQ.select(dog).where(cb.and(nameStartsWith, ageLowerThan)).orderBy(cb.desc(dog.get("name")));
		
		TypedQuery<Dog> query = em.createQuery(crQ);

		return query.getResultList();
	}
}

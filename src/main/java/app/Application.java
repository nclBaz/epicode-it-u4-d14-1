package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.AnimalsDAO;
import dao.CatsDAO;
import dao.DogsDAO;
import entities.Cat;
import entities.Dog;
import lombok.extern.slf4j.Slf4j;
import utils.JpaUtil;

@Slf4j
public class Application {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {

		EntityManager em = emf.createEntityManager();
		CatsDAO cd = new CatsDAO(em);
		DogsDAO dd = new DogsDAO(em);
		AnimalsDAO ad = new AnimalsDAO(em);

		Cat tom = new Cat("Tom", 5, 2.0);
		Dog fido = new Dog("Fido", 3, 20.0, "Pastore Tedesco");

//		ad.saveAnimal(fido);
//		ad.saveAnimal(tom);

//		cd.saveCat(tom);
//		dd.saveDog(fido);
//		Cat tom = (Cat) ad.findById("0bdc7889-2a9d-4f18-9df7-58880f205e3b");
//		Dog fido = (Dog) ad.findById("10744556-0e47-4e90-a193-b3552004711d");
//		log.info(tom.toString());
//		log.info(fido.toString());
//
//		log.info("*********************** FIND ALL *********************");
//		ad.findAll().stream().forEach(animal -> log.info(animal.toString()));
//
//		log.info("*********************** FIND ALL CATS *********************");

		cd.findAllCats().stream().forEach(cat -> log.info(cat.toString()));
		// ad.findAll().stream().forEach(a -> log.info(a.toString()));
		// Cat tom = (Cat) ad.findById("d1d4c1ca-7591-4837-a397-68200f85e826");
		if (tom != null)
			log.info(tom.toString());

		// ***************************** JPQL UPDATE & DELETE *******************

		dd.findByIdAndUpdate("3fd76be0-992c-4bf3-9c57-69956fdc4059", "Giorgio");
		dd.findByIdAndDelete("3fd76be0-992c-4bf3-9c57-69956fdc4059");

		// ***************************** CRITERIA API ***************************
		log.info("******************************** CRITERIA API ************************");
		dd.findByNameAndAgeWithCriteria("F", 5).stream().forEach(dog -> log.info(dog.toString()));

		em.close();
		emf.close();

	}

}

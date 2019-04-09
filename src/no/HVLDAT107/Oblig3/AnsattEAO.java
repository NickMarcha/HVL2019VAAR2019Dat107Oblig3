package no.HVLDAT107.Oblig3;

import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

public final class AnsattEAO {

	public static boolean leggTilAnsatt(Ansatt a) {
		Ansatt f = findAnsattByUserName(a.getBrukernavn());
		
		if (f == null) {
			DataBase.leggTilObject(a);
			return true;
		} else {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	static Ansatt findAnsattByFirstName(String firstName) {
		EntityManager em = DataBase.getEMF().createEntityManager();
		firstName = "%" + firstName + "%";
		String selectQuery= "SELECT u FROM Ansatt u WHERE u.fornavn LIKE :brukernavn"; 
		Query query = em.createQuery(selectQuery, Ansatt.class);

		//List<String> brukern = Arrays.asList(firstName);
		query.setParameter("brukernavn", firstName);
		List<Ansatt> users;
		try {
			
			users = query.getResultList();
			
		} finally {
			em.close();
		}
		
		if(users.isEmpty()) {
			return null;
		}
		return users.get(0); 
	}
	
	@SuppressWarnings("unchecked")
	static Ansatt findAnsattByUserName(String bnavn) {
		EntityManager em = DataBase.getEMF().createEntityManager();
		
		String selectQuery= "SELECT u FROM Ansatt u WHERE u.brukernavn IN :brukernavn"; 
		Query query = em.createQuery(selectQuery, Ansatt.class);

		List<String> brukern = Arrays.asList(bnavn);
		query.setParameter("brukernavn", brukern);
		List<Ansatt> users;
		try {
			
			users = query.getResultList();
			
		} finally {
			em.close();
		}
		
		if(users.isEmpty()) {
			return null;
		}
		return users.get(0); 
	}
	
	static Ansatt findAnsattByID(int ID) {
		EntityManager em = DataBase.getEMF().createEntityManager();
		
		Ansatt a = null;
		
		try {
			a = em.find(Ansatt.class ,ID);
			
		} finally {
			em.close();
		}
		
		return a;
	}
	
	static List<Ansatt> hentAlleAnsatte() {
		EntityManager em = DataBase.getEMF().createEntityManager();
		List<Ansatt> ansatte;
		
		try {
			TypedQuery<Ansatt> hoi = em.createNamedQuery("hentAllePersoner", Ansatt.class);
			ansatte = hoi.getResultList();
		} finally {
			em.close();
		}
		
		return ansatte;

	}
	
	static Ansatt EditAnsattStilling(Ansatt a, String nyStilling) {
		EntityManager em = DataBase.getEMF().createEntityManager();
		
		try {
			em.getTransaction().begin();
			a = em.merge(a);
			a.setStilling(nyStilling);
			em.getTransaction().commit();
			
		} finally {
			em.close();
		}
		
		return a;
	}
	
	static Ansatt EditAnsattLonn(Ansatt a, int nylonn) {
		EntityManager em = DataBase.getEMF().createEntityManager();
		
		try {
			em.getTransaction().begin();
			a = em.merge(a);
			a.setMaanedslonn(nylonn);
			em.getTransaction().commit();
			
		} finally {
			em.close();
		}
		
		return a;
	}
	
	static Ansatt EditAnsattAvdeling(Ansatt a, int avd) {
		EntityManager em = DataBase.getEMF().createEntityManager();
		
		try {
			em.getTransaction().begin();
			a = em.merge(a);
			a.setAvdeling(avd);
			em.getTransaction().commit();
			
		} finally {
			em.close();
		}
		
		return a;
	}
	
	static Ansatt DeleteAnsatt(Ansatt a) {
		EntityManager em = DataBase.getEMF().createEntityManager();
		
		try {
			em.getTransaction().begin();
			for(ProsjektOversikt po : ProsjektOversiktEAO.hentProsjektOversikterByProsjekt(a.getId())) {
				po = em.merge(po);
				em.remove(po);
			}
			a = em.merge(a);
			em.remove(a);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		
		return a;
	}
	
	
	static List<Ansatt> HentAnsattByAvdeling(int avdeling) {
		EntityManager em = DataBase.getEMF().createEntityManager();
		
		String selectQuery= "SELECT u FROM Ansatt u WHERE u.avdeling = :avd"; 
		TypedQuery<Ansatt> query = em.createQuery(selectQuery, Ansatt.class);

		query.setParameter("avd", avdeling);
		List<Ansatt> users;
		try {
			
			users = query.getResultList();
			
		} finally {
			em.close();
		}
		
		if(users.isEmpty()) {
			return null;
		}
		return users; 
	}
	
}

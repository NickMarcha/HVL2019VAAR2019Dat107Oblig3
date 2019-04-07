package no.HVLDAT107.Oblig3;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public final class AvdelingEAO {
	
	public static boolean leggTilAvdeling(Avdeling a) {
		Avdeling f = findAvdelingByName(a.getNavn());
		
		if (f == null) {
			DataBase.leggTilObject(a);
			return true;
		} else {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	static Avdeling findAvdelingByName(String anavn) {
		EntityManager em = DataBase.getEMF().createEntityManager();
		
		String selectQuery= "SELECT u FROM Avdeling u WHERE u.navn IN :Anavn"; 
		Query query = em.createQuery(selectQuery, Avdeling.class);

		List<String> brukern = Arrays.asList(anavn);
		query.setParameter("Anavn", brukern);
		List<Avdeling> users;
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
	static Avdeling findAvdelingBySjefID(String sjefID) {
		EntityManager em = DataBase.getEMF().createEntityManager();
		
		String selectQuery= "SELECT u FROM Avdeling u WHERE u.sjefID IN :Anavn"; 
		Query query = em.createQuery(selectQuery, Avdeling.class);

		List<String> brukern = Arrays.asList(sjefID);
		query.setParameter("Anavn", brukern);
		List<Avdeling> users;
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
	
	static Avdeling findAvdelingByID(int ID) {
		EntityManager em = DataBase.getEMF().createEntityManager();
		
		Avdeling a = null;
		
		try {
			a = em.find(Avdeling.class ,ID);
			
		} finally {
			em.close();
		}
		
		return a;
	}
	
	static List<Avdeling> hentAlleAvdelinge() {
		EntityManager em = DataBase.getEMF().createEntityManager();
		List<Avdeling> Avdelinge;
		
		try {
			TypedQuery<Avdeling> hoi = em.createNamedQuery("HentAlleAvdelinger", Avdeling.class);
			Avdelinge = hoi.getResultList();
		} finally {
			em.close();
		}
		
		return Avdelinge;

	}
	
	static Avdeling EditAvdelingSjef(Avdeling a, int nySjef) {
		EntityManager em = DataBase.getEMF().createEntityManager();
		
		try {
			em.getTransaction().begin();
			a = em.merge(a);
			a.setSjefID(nySjef);
			em.getTransaction().commit();
			
		} finally {
			em.close();
		}
		
		return a;
	}
	
	static Avdeling EditAvdelingnavn(Avdeling a, String nyNavn) {
		EntityManager em = DataBase.getEMF().createEntityManager();
		
		try {
			em.getTransaction().begin();
			a = em.merge(a);
			a.setNavn(nyNavn);
			em.getTransaction().commit();
			
		} finally {
			em.close();
		}
		
		return a;
	}
	
	static Avdeling DeleteAvdeling(Avdeling a) {
		EntityManager em = DataBase.getEMF().createEntityManager();
		
		try {
			em.getTransaction().begin();
			a = em.merge(a);
			em.remove(a);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		
		return a;
	}
}

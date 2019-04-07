package no.HVLDAT107.Oblig3;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class ProsjektOversiktEAO {

	public static boolean leggTilProsjektOversikt(ProsjektOversikt p) {

		DataBase.leggTilObject(p);
		
		return true;
	}
	
	static List<ProsjektOversikt> hentProsjektOversikterByAnsatt(int ansattID) {
		EntityManager em = DataBase.getEMF().createEntityManager();
		
		String selectQuery= "SELECT u FROM ProsjektOversikt u WHERE u.ansattID = :pID"; 
		Query query = em.createQuery(selectQuery, ProsjektOversikt.class);

		//List<String> brukern = Arrays.asList(bnavn);
		
		query.setParameter("pID", ansattID);
		List<ProsjektOversikt> users;
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
	@SuppressWarnings("unchecked")
	static List<ProsjektOversikt> hentProsjektOversikterByProsjekt(int prosjektID) {
		EntityManager em = DataBase.getEMF().createEntityManager();
		
		String selectQuery= "SELECT u FROM ProsjektOversikt u WHERE u.prosjektID = :pID"; 
		Query query = em.createQuery(selectQuery, ProsjektOversikt.class);

		//List<String> brukern = Arrays.asList(bnavn);
		
		query.setParameter("pID", prosjektID);
		List<ProsjektOversikt> users;
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
	
	static List<ProsjektOversikt> hentAlleProsjektOversikter() {
		EntityManager em = DataBase.getEMF().createEntityManager();
		List<ProsjektOversikt> prosjekt;
		
		try {
			TypedQuery<ProsjektOversikt> hoi = em.createNamedQuery("hentAlleProsjekterOversikter", ProsjektOversikt.class);
			prosjekt = hoi.getResultList();
		} finally {
			em.close();
		}
		
		return prosjekt;

	}
	
}

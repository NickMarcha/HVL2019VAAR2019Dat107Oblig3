package no.HVLDAT107.Oblig3;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class ProsjektEAO {

	public static boolean leggTilProsjekt(Prosjekt p) {
		Prosjekt f = findProsjektByID(p.getId());
		
		if (f == null) {
			DataBase.leggTilObject(p);
			return true;
		} else {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	static Prosjekt findProsjektByName(String prosjektName) {
		prosjektName = "%" + prosjektName + "%";
		EntityManager em = DataBase.getEMF().createEntityManager();
		
		String selectQuery= "SELECT u FROM Prosjekt u WHERE u.navn LIKE :brukernavn"; 
		Query query = em.createQuery(selectQuery, Prosjekt.class);

		query.setParameter("brukernavn", prosjektName);
		List<Prosjekt> users;
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
	
	static Prosjekt findProsjektByID(int ID) {
		EntityManager em = DataBase.getEMF().createEntityManager();
		
		Prosjekt a = null;
		
		try {
			a = em.find(Prosjekt.class ,ID);
			
		} finally {
			em.close();
		}
		
		return a;
	}
	
	static List<Prosjekt> hentAlleProsjekter() {
		EntityManager em = DataBase.getEMF().createEntityManager();
		List<Prosjekt> prosjekt;
		
		try {
			TypedQuery<Prosjekt> hoi = em.createNamedQuery("hentAlleProsjekter", Prosjekt.class);
			prosjekt = hoi.getResultList();
		} finally {
			em.close();
		}
		
		return prosjekt;

	}
	
	static Prosjekt EditProsjektNavn(Prosjekt a, String nyNavn) {
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
	
	static Prosjekt EditProsjektBeskrivelse(Prosjekt a, String nyBeskrivelse) {
		EntityManager em = DataBase.getEMF().createEntityManager();
		
		try {
			em.getTransaction().begin();
			a = em.merge(a);
			a.setBeskrivelse(nyBeskrivelse);
			em.getTransaction().commit();
			
		} finally {
			em.close();
		}
		
		return a;
	}
	
	
	static Prosjekt DeleteProsjekt(Prosjekt a) {
		EntityManager em = DataBase.getEMF().createEntityManager();
		
		try {
			em.getTransaction().begin();
			a = em.merge(a);
			
			for(ProsjektOversikt po : ProsjektOversiktEAO.hentProsjektOversikterByProsjekt(a.getId())) {
				po = em.merge(po);
				em.remove(po);
			}
			em.remove(a);
			
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		
		return a;
	}
}

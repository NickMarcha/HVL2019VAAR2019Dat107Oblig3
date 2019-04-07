package no.HVLDAT107.Oblig3;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ProsjektOversiktEAO {

	public static boolean leggTilProsjektOversikt(ProsjektOversikt p) {

		DataBase.leggTilObject(p);
		
		return true;
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

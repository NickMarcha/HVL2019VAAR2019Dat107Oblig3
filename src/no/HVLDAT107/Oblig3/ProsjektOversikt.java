package no.HVLDAT107.Oblig3;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(schema = "oblig3", name ="prosjektOversikt")
@NamedQuery(name = "hentAlleProsjekterOversikter", query ="SELECT p FROM ProsjektOversikt p")
public class ProsjektOversikt {

	////VARIABLES            ////////////
	private int ID;
	private int ansattID;
	private int prosjektID;
	private String ansattRolle;
	private int ansattArbeidsTimer;
	
	////GET & SET            ////////////
	
	 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return ID;
    }
    public void setId(int
    		ID) {
    	this.ID = ID;
    }
	    
	@Column(name = "ansattID")
	public int getAnsattID() {
	  return ansattID;
	}
	public void setAnsattID(int ansattID) {
		this.ansattID = ansattID;
	}
	
	@Column(name = "prosjektID")
	public int getProsjektID() {
	  return prosjektID;
	}
	public void setProsjektID(int prosjektID) {
		this.prosjektID = prosjektID;
	}
	
	@Column(name = "ansattRolle")
	public String getAnsattRolle() {
	  return ansattRolle;
	}
	public void setAnsattRolle(String ansattRolle) {
		this.ansattRolle = ansattRolle;
	}
	
	@Column(name = "ansattArbeidsTimer")
	public int getAnsattArbeidsTimer() {
	  return ansattArbeidsTimer;
	}
	public void setAnsattArbeidsTimer(int ansattArbeidsTimer) {
		this.ansattArbeidsTimer = ansattArbeidsTimer;
	}
	
	////    CONTRUCTOR        ////////////
	public ProsjektOversikt() {
	}
	public ProsjektOversikt(int ansattID, int prosjektID, String ansattRolle, int ansattArbeidsTimer) {
		this.ansattID = ansattID;
		this.prosjektID = prosjektID;
		this.ansattRolle = ansattRolle;
		this.ansattArbeidsTimer = ansattArbeidsTimer;
	}
	
    ////		TO STRING            ////////////
    public String toString() {
    	return "AnsattID:" + ansattID + " ProsjektID:" + prosjektID + " Rolle:" + ansattRolle + " ArbeidsTimer:" + ansattArbeidsTimer;
    }
    ///////////OTHER      ///////////
    public static void CreateProsjektOversikt() {
    	System.err.println("Creating ProsjektOversikt:");
    	
    	ProsjektOversikt prosjektOversikt = new ProsjektOversikt(-1,-1, Helper.InputString("Input ansattRolle:"), Helper.InputInt("Input ArbeidsTimer"));
    	
    	List<Ansatt> ansatte = AnsattEAO.hentAlleAnsatte();
    	ansatte.remove(0);
    	
    	List<Integer> ansatteIDer = new ArrayList<Integer>();
    	
    	for(int i = 0; i < ansatte.size(); i++) {
    		ansatteIDer.add(ansatte.get(i).getId());
    	}
    	
    	while (prosjektOversikt.getAnsattID()== -1) {
    		System.out.println("Printer ansatte:");
    		Helper.PrintList(ansatte);
    		
    		int ansattID = Helper.InputInt("Input ansattID:");
    		
    		if (ansatteIDer.contains(ansattID)){
    			prosjektOversikt.setAnsattID(ansattID);
    		} else {
    			System.out.println("Ansatt finnes ikke");
    		}
    	}
    	
    	List<Prosjekt> prosjekter = ProsjektEAO.hentAlleProsjekter();
    	
    	List<Integer> ProsjektIDer = new ArrayList<Integer>();
    	
    	for(int i = 0; i < prosjekter.size(); i++) {
    		ProsjektIDer.add(prosjekter.get(i).getId());
    	}
    	
    	while (prosjektOversikt.getProsjektID() == -1) {
    		System.out.println("Printer Prosjekter:");
    		Helper.PrintList(prosjekter);
    		
    		int ProsjektID = Helper.InputInt("Input prosjektID:");
    		
    		if (ProsjektIDer.contains(ProsjektID)){
    			prosjektOversikt.setProsjektID(ProsjektID);
    		} else {
    			System.out.println("Prosjekt finnes ikke");
    		}
    	}
    	
    	
    	ProsjektOversiktEAO.leggTilProsjektOversikt(prosjektOversikt);
    	
    	System.err.println("La til prosjektOversikt");
    }
	
    public static void CreateProsjektOversiktForAnsatt(int ansatt) {
    	System.err.println("Creating ProsjektOversikt:");
    	
    	ProsjektOversikt prosjektOversikt = new ProsjektOversikt(ansatt,-1, Helper.InputString("Input ansattRolle:"), Helper.InputInt("Input ArbeidsTimer"));
    	
    	List<Prosjekt> prosjekter = ProsjektEAO.hentAlleProsjekter();
    	
    	List<Integer> ProsjektIDer = new ArrayList<Integer>();
    	
    	for(int i = 0; i < prosjekter.size(); i++) {
    		ProsjektIDer.add(prosjekter.get(i).getId());
    	}
    	
    	while (prosjektOversikt.getProsjektID() == -1) {
    		System.out.println("Printer Prosjekter:");
    		Helper.PrintList(prosjekter);
    		
    		int ProsjektID = Helper.InputInt("Input prosjektID:");
    		
    		if (ProsjektIDer.contains(ProsjektID)){
    			prosjektOversikt.setProsjektID(ProsjektID);
    		} else {
    			System.out.println("Prosjekt finnes ikke");
    		}
    	}
    	
    	ProsjektOversiktEAO.leggTilProsjektOversikt(prosjektOversikt);
    	
    	System.err.println("La til prosjektOversikt");
    }
    
    public static void CreateProsjektOversiktForProsjekt(int prosjekt) {
    	System.err.println("Creating ProsjektOversikt:");
    	
    	ProsjektOversikt prosjektOversikt = new ProsjektOversikt(-1,prosjekt, Helper.InputString("Input ansattRolle:"), Helper.InputInt("Input ArbeidsTimer"));
    	
    	List<Ansatt> ansatte = AnsattEAO.hentAlleAnsatte();
    	ansatte.remove(0);
    	
    	List<Integer> ansatteIDer = new ArrayList<Integer>();
    	
    	for(int i = 0; i < ansatte.size(); i++) {
    		ansatteIDer.add(ansatte.get(i).getId());
    	}
    	
    	while (prosjektOversikt.getAnsattID()== -1) {
    		System.out.println("Printer ansatte:");
    		Helper.PrintList(ansatte);
    		
    		int ansattID = Helper.InputInt("Input ansattID:");
    		
    		if (ansatteIDer.contains(ansattID)){
    			prosjektOversikt.setAnsattID(ansattID);
    		} else {
    			System.out.println("Ansatt finnes ikke");
    		}
    	}  	
    	
    	ProsjektOversiktEAO.leggTilProsjektOversikt(prosjektOversikt);
    	
    	System.err.println("La til prosjektOversikt");
    }
	public static void SkrivUtAlleProsjektOversikter() {
		System.err.println("Printer prosjektOversikt:");
		Helper.PrintList(ProsjektOversiktEAO.hentAlleProsjektOversikter());
	}
}

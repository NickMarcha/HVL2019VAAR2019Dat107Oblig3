package no.HVLDAT107.Oblig3;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(schema = "oblig3", name ="prosjekt")
@NamedQuery(name = "hentAlleProsjekter", query ="SELECT p FROM Prosjekt p")
public class Prosjekt {

	////      VARIABLES            ////////////
	private int ID;
	private String navn;
	private String beskrivelse;
	
	////GET & SET            ////////////
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
	return ID;
	}
	public void setId(int ID) {
	this.ID = ID;
	}
	
	@Column(name = "navn")
    public String getNavn() {
        return navn;
    }
    public void setNavn(String navn) {
    	this.navn = navn;
    }
    
    @Column(name = "beskrivelse")
    public String getBeskrivelse() {
        return beskrivelse;
    }
    public void setBeskrivelse(String beskrivelse) {
    	this.beskrivelse = beskrivelse;
    }
    
	////    CONTRUCTOR        ////////////
    public Prosjekt() {
    	
    }
    public Prosjekt(String navn, String beskrivelse) {
    	this.navn = navn;
    	this.beskrivelse = beskrivelse;
    }

    ////		TO STRING            ////////////
    public String toString() {
    	return ID+ " " + navn + " : " + beskrivelse;
    }
    ///////////OTHER      ///////////
    public static void CreateProsjekt() {
    	
    	Prosjekt prosjekt = new Prosjekt(Helper.InputString("Input Prosjektnavn:"), Helper.InputString("Input Prosjekt beskrivelse:"));
    	ProsjektEAO.leggTilProsjekt(prosjekt);
    	System.err.println("La til prosjekt");
    }
    
    public static void SkrivUtAlleProsjekter() {
		System.err.println("Printer prosjekter:");
		Helper.PrintList(ProsjektEAO.hentAlleProsjekter());
	}
}

package no.HVLDAT107.Oblig3;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;



@Entity
@Table(schema = "oblig3", name ="ansatt")
@NamedQuery(name = "hentAllePersoner", query ="SELECT p FROM Ansatt p")
public class Ansatt {

	////      VARIABLES            ////////////
	private int ID;
	private String brukernavn;
    private String fornavn;
    private String etternavn;
    private Date ansettelsesdato;
    private String stilling;
    private int maanedslonn;
    private int avdeling;
    
    ////      CONTRUCTORS            ////////////
    public Ansatt() {
    	
    }
    
    public Ansatt(
    		String brukernavn, 
    		String fornavn, 
    		String etternavn, 
    		Date ansettelsesdato, 
    		String stilling,
    		int maanedslonn, 
    		int avdeling) {
    	this.brukernavn = brukernavn; 
    	this.fornavn = fornavn;
    	this.etternavn = etternavn;
    	this.ansettelsesdato = ansettelsesdato;
    	this.stilling = stilling;
    	this.maanedslonn = maanedslonn;
    	this.avdeling = avdeling;
    }

    ////		GET & SET            ////////////
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return ID;
    }
    public void setId(int
    		ID) {
    	this.ID = ID;
    }
    
    @Column(name = "brukernavn")
    public String getBrukernavn() {
        return brukernavn;
    }
    public void setBrukernavn(String brukernavn) {
    	this.brukernavn = brukernavn;
    }
    
    @Column(name="fornavn")
    public String getFornavn() {
        return fornavn;
    }
    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }
    
    @Column(name="etternavn")
    public String getEtternavn() {
        return etternavn;
    }
    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }
    
    @Column(name="ansettelsesdato")
    public Date getAnsettelsesdato() {
        return ansettelsesdato;
    }
    public void setAnsettelsesdato(Date ansettelsesdato) {
        this.ansettelsesdato = ansettelsesdato;
    }
    
    @Column(name="stilling")
    public String getStilling() {
        return stilling;
    }
    public void setStilling(String stilling) {
        this.stilling = stilling;
    }
    
    @Column(name="maanedslonn")
    public int getMaanedslonn() {
        return maanedslonn;
    }
    public void setMaanedslonn(int maanedslonn) {
        this.maanedslonn = maanedslonn;
    }
    
    @Column(name="avdeling")
    public int getAvdeling() {
        return avdeling;
    }
    public void setAvdeling(int avdeling) {
        this.avdeling = avdeling;
    }
    
    ////		TO STRING            ////////////
    public String toString() {
    	return ID+ " " + brukernavn + " " + fornavn + " " + etternavn + " " + ansettelsesdato + " " + stilling + " " + maanedslonn + "kr " + avdeling;
    }

    ////		Other            ////////////
    public static void CreateAnsatt() {
    	System.err.println("Creating Ansatt");
    	
    	Ansatt a = new Ansatt(
    			Helper.InputString("Input Initials (2-4c):", 2, 4),
    			Helper.InputString("Input fornavn:"), 
    			Helper.InputString("Input etternavn:"),
    			Helper.InputDate("Input Ansettelsesdata (yyyy-mm-dd):"),
    			Helper.InputString("Input stilling:"),
    			Helper.InputInt("Input månedslønn:"),
    			-1
    			);
    	
    	List<Avdeling> avdelinger = AvdelingEAO.hentAlleAvdelinge();
    	avdelinger.remove(0);
    	
    	List<Integer> avde = new ArrayList<Integer>();
    	
    	for(int i = 0; i < avdelinger.size(); i++) {
    		avde.add(avdelinger.get(i).getId());
    	}
    	
    	while (a.getAvdeling()== -1) {
    		System.out.println("Printer avdelinger:");
    		Helper.PrintList(avdelinger);
    		
    		int avd = Helper.InputInt("Input Avdeling:");
    		
    		if (avde.contains(avd)){
    			a.setAvdeling(avd);
    		} else {
    			System.out.println("Avdelingen finnes ikke");
    		}
    		
    	}
    	
    	
    	AnsattEAO.leggTilAnsatt(a);
    	
    	System.err.println("La till Ansatt");
    }
	
	public static void SkrivUtAlleAnsatte() {
		System.err.println("Printer Ansatte:");
		List<Ansatt> ansatte = AnsattEAO.hentAlleAnsatte();
		ansatte.remove(0);
		Helper.PrintList(ansatte);
	}
}

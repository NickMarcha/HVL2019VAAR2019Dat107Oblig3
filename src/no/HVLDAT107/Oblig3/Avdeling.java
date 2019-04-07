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
@Table(schema = "oblig3", name ="avdeling")
@NamedQuery(name = "HentAlleAvdelinger", query ="SELECT p FROM Avdeling p")
public class Avdeling {

	private int ID;
	private String navn;
	private int sjefID;
	
	////CONTRUCTORS            ////////////
	public Avdeling() {
		
	}
	
	public Avdeling(
			String navn, 
			int sjefID) {
		this.navn = navn; 
		this.sjefID = sjefID;
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
    
    @Column(name = "navn")
    public String getNavn() {
        return navn;
    }
    public void setNavn(String navn) {
    	this.navn = navn;
    }
    
    @Column(name = "sjefID")
    public int getSjefID() {
        return sjefID;
    }
    public void setSjefID(int sjefID) {
    	this.sjefID = sjefID;
    }
    
	////		TO STRING            ////////////
	public String toString() {
	return "ID:" + ID + " Navn:" + navn + " SjefID:" + sjefID;
	}
	
	////		Other            ////////////
	public static void CreateAvdeling() {
	System.err.println("Creating Avdeling");
	
	Avdeling a = new Avdeling(
			Helper.InputString("Input AvdelingsNavn:"),
			-1
			);
	
	List<Ansatt> ansatte = AnsattEAO.hentAlleAnsatte();
	ansatte.remove(0);
	
	List<Integer> ansatteIDer = new ArrayList<Integer>();
	
	for (Ansatt an: ansatte) {
		ansatteIDer.add(an.getId());
	}
	
	
	while(a.getSjefID() == -1) {
		
		Helper.PrintList(ansatte);
		int i = Helper.InputInt("Input SjefID:");
		
		//ideen eksisterer
		if(ansatteIDer.contains(i)) {
			Ansatt denne = AnsattEAO.findAnsattByID(i);
			
			//er ikke sjef i egen avdeling
			if(AvdelingEAO.findAvdelingByID(denne.getAvdeling()).getSjefID() != denne.getId()) {
				a.setSjefID(i);
			} else {
				System.err.println("Ansatt er sjef i egen avdeling");
			}
		} else {
			System.err.println("Ansatt eksisterer ikke");
		}
	}
	
	AvdelingEAO.leggTilAvdeling(a);
	
	AnsattEAO.EditAnsattAvdeling(AnsattEAO.findAnsattByID(a.getSjefID()), a.getId());
	System.err.println("La till Avdeling");
	}
	
	public static void SkrivUtAlleAvdelinge() {
		System.err.println("Printer Avdelinge:");
		List<Avdeling> avde= AvdelingEAO.hentAlleAvdelinge();
		avde.remove(0);
		Helper.PrintList(avde);
	}
	public static void SkrivUtAlleAvdelingeMedAnsatte() {
		System.err.println("Printer Avdelinge:");
		List<Avdeling> avdelinger = AvdelingEAO.hentAlleAvdelinge();
		avdelinger.remove(0);
		
		List<Ansatt> ansatte = AnsattEAO.hentAlleAnsatte();
		ansatte.remove(0);
		
		for(Avdeling av :  avdelinger) {
			System.out.println("Avd:" + av.getId() + " "+ av.getNavn());
			
			List<Ansatt> avAnsatt = new ArrayList<Ansatt>();
			
			for(Ansatt a : ansatte) {
				//ansatt gører til avdeling
				if(a.getAvdeling() == av.getId()) {
					//avdelings sjef id er lik ansatt id
					if(av.getSjefID() == a.getId()) {
						System.out.println("--"+ a +"(Sjef)");
					} else {
						avAnsatt.add(a);
					}
				}
			}
			
			
			Helper.PrintList(avAnsatt);
		}
	}
}

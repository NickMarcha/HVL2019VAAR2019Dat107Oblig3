package no.HVLDAT107.Oblig3;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import javax.persistence.*;


public final class DataBase {
	
	private DataBase() {
		
	}
	private static final String PERSISTANCE_UNIT = "oblig3PersistanceUnit";
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT);
	
	public static EntityManagerFactory getEMF() {
		return emf;
	}
	
	public static void ResetSchemaAndTables() {
		
		System.err.println("Deleting and Reseting Schema and Tables");
		if(Helper.confirmation()) {
			System.out.println("Reseting Schema");
			File ResetSchema = new File("src/CreateDataBaseSchema.sql");
			runSqlFile(ResetSchema);
			System.out.println("Reseting Tables");
			File ResetTables = new File("src/CreateDataBaseTables.sql");
			runSqlFile(ResetTables);
		} else {
			System.err.println("Aborted");
			return;
		}
	}
	
	
	//runs sql command based from file
	static void runSqlFile(File myFile) {
		String myquery;
		
		try {
			myquery = readFile(myFile, Charset.defaultCharset());
		} catch (IOException ex){
			System.out.println(ex);
			return;
		}
		System.out.println("Trying to run " + myFile.getName());
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			Query q = em.createNativeQuery(myquery);
			tx.begin();
			q.executeUpdate();
			
			tx.commit();
			System.out.println("Succesfully ran SQL file");
		} finally {
			em.close();
		}
	}
	
	// Turns file into a string
	private static String readFile(File file, Charset charset) throws IOException {
	    return new String(Files.readAllBytes(file.toPath()), charset);
	}
	
	
	public static <T> boolean leggTilObject(T a) {
		EntityManager em = emf.createEntityManager();
	
		EntityTransaction tx = em.getTransaction();

		boolean succeded = false;
		try {
			tx.begin();
			em.persist(a);
			tx.commit();
			succeded = true;

		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
			
		} finally {
			em.close();
		}
		return succeded;
		
	}
	
}

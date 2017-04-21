package test01917;

import daoimpl01917_version2.MySQLOperatoerDAO;
import daoimpl01917_version2.MySQLProduktBatchDAO;
import daoimpl01917_version2.MySQLRaavareDAO;
import daointerfaces01917.DALException;
import dto01917.OperatoerDTO;
import dto01917.ProduktBatchDTO;
import dto01917.RaavareDTO;

import java.sql.SQLException;

import connector01917.Connector;

public class Main {
	public static void main(String[] args) {
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		
		//testOPR(); 	//Operatoer test
		
		testPB(); 	//ProduktBatch test
		/*testPBK(); 	//ProduktBatchKomponent test - not implemented
		testR();	//Recept test - not implemented
		testRK();	//ReceptKomponent test - not implemented
		testRB(); 	//RaavareBatch test - not implemented
		testRaa();	//Raavare test - not implemented
		*/
		
	}
	
	private static void testOPR(){
		System.out.println("__________________________________________");
		
		MySQLOperatoerDAO opr = new MySQLOperatoerDAO();
		
		System.out.println("Operatoer nummer 3:");
		try { System.out.println(opr.getOperatoer(3)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Indsaettelse af ny operatoer med opr_id =  4");
		OperatoerDTO oprDTO = new OperatoerDTO(4,"Don Juan","DJ","000000-0000","iloveyou");
		try { opr.createOperatoer(oprDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		//Will give an SQL exception ^
		//This is because previous tests have inserted an operatoer 
		//with the ID 4 and we cannot have duplicate primary keys.
		
		System.out.println("Operatoer nummer 4:");
		try { System.out.println(opr.getOperatoer(4)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Opdatering af initialer for operatoer nummer 4");
		oprDTO.setIni("DoJu");
		try { opr.updateOperatoer(oprDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Operatoer nummer 4:");
		try { System.out.println(opr.getOperatoer(4)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle operatoerer:");
		try { System.out.println(opr.getOperatoerList()); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Operatoer nummer 5:");
		try { System.out.println(opr.getOperatoer(5)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("");
	}
	
	private static void testPB(){
		/*
		 * This method has exactly the same tests as testOPR
		 */
		System.out.println("__________________________________________");
		
		MySQLProduktBatchDAO produktbatch = new MySQLProduktBatchDAO();
		
		System.out.println("Produktbatch nummer 2:");
		try { System.out.println(produktbatch.getProduktBatch(2)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Indsaettelse af ny produktbatch med pb_id =  6");
		ProduktBatchDTO produktbatchDTO = new ProduktBatchDTO(6, 2, 2);
		try { produktbatch.createProduktBatch(produktbatchDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		//Will give an SQL exception ^
		//This is because previous tests have inserted an operatoer 
		//with the ID 4 and we cannot have duplicate primary keys.
		
		System.out.println("Produktbatch nummer 6:");
		try { System.out.println(produktbatch.getProduktBatch(6)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Opdatering af status for produktbatch nummer 6");
		produktbatchDTO.setStatus(5);
		try { produktbatch.updateProduktBatch(produktbatchDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Produktbatch nummer 6:");
		try { System.out.println(produktbatch.getProduktBatch(6)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle produkt batches:");
		try { System.out.println(produktbatch.getProduktBatchList()); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Produktbatch nummer 7:");
		try { System.out.println(produktbatch.getProduktBatch(7)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
	}
	
	private static void testPBK(){
		//Produkt batch komponent
		
		//TODO implement test
	}
	
	private static void testR(){
		//Recept
		
		//TODO implement test
	}
	
	private static void testRK(){
		//Recept komponent
		
		//TODO implement test
	}
	
	private static void testRB(){
		//Raavare batch
		
		//TODO implement test
	}
	
	private static void testRaa(){
		//Raavare batch komponent
		
System.out.println("__________________________________________");
		
		MySQLRaavareDAO raa = new MySQLRaavareDAO();
		
		System.out.println("Raavare nummer 3:");
		try { System.out.println(raa.getRaavare(3)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Indsaettelse af ny raavare med raavareid =  8");
		RaavareDTO raaDTO = new RaavareDTO(8,"Liquid Magic","Hansi Hinterseer");
		try { raa.createRaavare(raaDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		//Will give an SQL exception ^
		//This is because previous tests have inserted an operatoer 
		//with the ID 4 and we cannot have duplicate primary keys.
		
		System.out.println("Raavare nummer 8:");
		try { System.out.println(raa.getRaavare(8)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Opdatering af leverandør for råvare nr. 8");
		raaDTO.setLeverandoer("Hansi H. Junior");
		try { raa.updateRaavare(raaDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Raavare nummer 8:");
		try { System.out.println(raa.getRaavare(8)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle raavarer:");
		try { System.out.println(raa.getRaavareList()); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Raavare nummer 9:");
		try { System.out.println(raa.getRaavare(9)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("");
	}
}

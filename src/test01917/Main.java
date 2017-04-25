package test01917;


import daointerfaces01917.DALException;
import daointerfaces01917.*;
import dto01917.OperatoerDTO;
import dto01917.ProduktBatchDTO;
import dto01917.ProduktBatchKompDTO;
import dto01917.ReceptDTO;
import dto01917.ReceptKompDTO;
import dto01917.RaavareBatchDTO;
import dto01917.RaavareDTO;

import daoimpl01917_version2.MySQLOperatoerDAO;
import daoimpl01917_version2.MySQLProduktBatchDAO;
import daoimpl01917_version2.MySQLProduktBatchKomponentDAO;
import daoimpl01917_version2.MySQLReceptDAO;
import daoimpl01917_version2.MySQLReceptKomponentDAO;
import daoimpl01917_version2.MySQLRaavareBatchDAO;
import daoimpl01917_version2.MySQLRaavareDAO;

import java.sql.SQLException;
import java.util.List;

import connector01917.Connector;

public class Main {
	public static void main(String[] args) {
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		
		//testOPR(); 	//Operatoer test
		
		//testPB(); 		//ProduktBatch test
		testPBK(); 	//ProduktBatchKomponent test - not implemented
		/*testRec();		//Recept test - not implemented
		testRecK();		//ReceptKomponent test - not implemented
		testRaaBat();	//RaavareBatch test - not implemented
		testRaa();		//Raavare test - not implemented
		*/
		
	}
	
	private static void testOPR(){
		System.out.println("__________________________________________");
		
		OperatoerDAO opr = new MySQLOperatoerDAO();
		
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
		
		ProduktBatchDAO produktbatch = new MySQLProduktBatchDAO();
		
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
		
		System.out.println("__________________________________________");
		
		ProduktBatchKompDAO produktbatchkomponent = new MySQLProduktBatchKomponentDAO();
		
		//DONE test getProduktBatchKomp(pbId, rbId)
		System.out.println("Produktbatchkompoent: pbId = 1, rbId = 2: ");
		try { System.out.println(produktbatchkomponent.getProduktBatchKomp(1,2)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		//TODO test getProduktBatchKompList(pbId)
		
		//TODO test getProduktBatchKompList()
		
		//TODO test createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent)
		
		//TODO test updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent)
	}
	
	private static void testRec(){
		//Recept
		
		//TODO test getRecept(receptId)
		
		//TODO test getReceptList()
		
		//TODO test createRecept(ReceptDTO)
		
		//TODO test updateRecept(ReceptDTO)
	}
	
	private static void testRecKomp(){
		//Recept komponent
		
		//TODO test getReceptKomp(receptId, raavareId)
		
		//TODO test getReceptKompList(raavareId)
		
		//TODO test getReceptKompList()
				
		//TODO test createReceptKomp(ReceptDTO)
				
		//TODO test updateReceptKomp(ReceptDTO)
	}
	
	private static void testRaaBat(){
		//Tobias
		System.out.println("__________________________________________");
		
		RaavareBatchDAO raavarebatch = new MySQLRaavareBatchDAO();
		RaavareBatchDTO raa = new RaavareBatchDTO(8, 8, 420);
		List<RaavareBatchDTO> raavarelist;
		
		//Raavare batch
		
		//DONE test getRaavareBatch(int rbId)
		System.out.println("Raavarebatch 3: ");
		try { System.out.println(raavarebatch.getRaavareBatch(3)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		//DONE test getRaavareBatchList()
		System.out.println("Alle raavarebatches:");
		try {
			raavarelist = raavarebatch.getRaavareBatchList();
			for(int i = 0; i<raavarelist.size(); i++){
				System.out.println(raavarelist.get(i)); 
				}
			}
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		//DONE test getRaavareBatchList(int raavareId)
		System.out.println("Alle raavarebatches med raavare 5:");
		try {
			raavarelist = raavarebatch.getRaavareBatchList(5);
			for(int i = 0; i<raavarelist.size(); i++){
				System.out.println(raavarelist.get(i)); 
				}
			}
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		//DONE test createRaavareBatch(RaavareBatchDTO raavarebatch)
		System.out.println("Indsaettelse af ny raavarebatch med rb_id = 8");
		try { raavarebatch.createRaavareBatch(raa); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Raavarebatch 8: ");
		try { System.out.println(raavarebatch.getRaavareBatch(8)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		//DONE test updateRaavareBatch(RaavareBatchDTO raavarebatch)
		System.out.println("Opdatering af raavarebatch med rb_id = 8");
		raa.setMaengde(1337);
		try { raavarebatch.updateRaavareBatch(raa); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Raavarebatch 8: ");
		try { System.out.println(raavarebatch.getRaavareBatch(8)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		
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

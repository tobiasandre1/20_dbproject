package 
test01917;


import daointerfaces01917.DALException;
import daointerfaces01917.*;
import dto01917.OperatoerDTO;
import dto01917.ProduktBatchDTO;
import dto01917.ProduktBatchKompDTO;
import dto01917.ReceptDTO;
import dto01917.ReceptKompDTO;
import dto01917.RaavareBatchDTO;
import dto01917.RaavareDTO;
import dto01917.ViewMadDTO;
import dto01917.ViewVejningDTO;

import java.sql.SQLException;
import java.util.List;

import connector01917.Connector;
import daoimpl01917.MySQLOperatoerDAO;
import daoimpl01917.MySQLProduktBatchDAO;
import daoimpl01917.MySQLProduktBatchKomponentDAO;
import daoimpl01917.MySQLRaavareBatchDAO;
import daoimpl01917.MySQLRaavareDAO;
import daoimpl01917.MySQLReceptDAO;
import daoimpl01917.MySQLReceptKomponentDAO;

import views.Views;

public class Main {
	public static void main(String[] args) {
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		
		testOPR(); 	//Operatoer test
		testPB(); 		//ProduktBatch test
		testPBK(); 	//ProduktBatchKomponent test
		testRec();		//Recept test
		testRecKomp();		//ReceptKomponent test
		testRaaBat();	//RaavareBatch test
		testRaa();		//Raavare test
		testView();
		
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
		ProduktBatchKompDTO pro = new ProduktBatchKompDTO(5, 3, 0.5, 4.20, 3);
		List<ProduktBatchKompDTO> produktbatchkomponentlist;
		
		//DONE test getProduktBatchKomp(pbId, rbId)
		System.out.println("Produktbatchkomponent: pbId = 1, rbId = 2: ");
		try { System.out.println(produktbatchkomponent.getProduktBatchKomp(1,2)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		//TODO test getProduktBatchKompList()
		System.out.println("Alle produktbatchkomponenter:");
		try {
			produktbatchkomponentlist = produktbatchkomponent.getProduktBatchKompList();
			for(int i = 0; i<produktbatchkomponentlist.size(); i++){
				System.out.println(produktbatchkomponentlist.get(i)); 
				}
			}	
		catch (DALException e) { System.out.println(e.getMessage()); }
	
		//TODO test getProduktBatchKompList(pbId)
		System.out.println("Alle produktbatchkomponenter med produktbatch id = 3:");
		try {
			produktbatchkomponentlist = produktbatchkomponent.getProduktBatchKompList(3);
			for(int i = 0; i<produktbatchkomponentlist.size(); i++){
				System.out.println(produktbatchkomponentlist.get(i)); 
				}
			}	
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		//TODO test createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent)
		System.out.println("Indsaettelse af ny produktbatchkomponent med pb_id = 5 og rb_id = 3");
		try { produktbatchkomponent.createProduktBatchKomp(pro); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Produktbatchkomponent 5,3: ");
		try { System.out.println(produktbatchkomponent.getProduktBatchKomp(5,3)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		
		//TODO test updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent)
		System.out.println("Opdatering af produktbatchkomponent med pb_id = 5 og rb_id = 3");
		pro.setOprId(2);
		pro.setNetto(420.69);
		try { produktbatchkomponent.updateProduktBatchKomp(pro); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Produktbatchkomponent 5,3: ");
		try { System.out.println(produktbatchkomponent.getProduktBatchKomp(5,3)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
	}
	
	private static void testRec(){
		ReceptDAO recept = new MySQLReceptDAO();
		
		//Test af getRecept()
		System.out.println();
		System.out.println("Recept id 1:");	
		try { System.out.println(recept.getRecept(1)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		//Test af getReceptList()
		System.out.println();
		System.out.println("Alle recepter:");
		try { System.out.println(recept.getReceptList()); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		//Test af createRecept()
		System.out.println();
		System.out.println("Indsættelse af nyt recept med: recept_id = 4 receptnavn = hawaii");
		ReceptDTO receptDTO = new ReceptDTO(4, "hawaii");
		try { recept.createRecept(receptDTO); }
		catch (DALException e) {System.out.println(e.getMessage()); }
		
		//Test af updateRecept()
		System.out.println();
		System.out.println("Opdatering af recept med id 4. Sætter recept_navn = ananas_pizza");
		ReceptDTO receptDTO2 = new ReceptDTO(4, "ananas_pizza");
		try { recept.updateRecept(receptDTO2); }
		catch (DALException e) {System.out.println(e.getMessage()); }
		
		//Test om updateRecept har indsat korrekt
		System.out.println();
		System.out.println("Tester om recept id 4 er korrekt:");
		try { System.out.println(recept.getRecept(4)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
	}
	
	private static void testRecKomp(){
		//Recept komponent
		
		MySQLReceptKomponentDAO RK = new MySQLReceptKomponentDAO();
		List<ReceptKompDTO> RKList;
		try {RKList = RK.getReceptKompList();} 
		catch (DALException e) {e.getMessage();}
		ReceptKompDTO TestRK = new ReceptKompDTO(4, 8, 20, 0.2);
		
		//DONE test getReceptKomp(receptId, raavareId)
		System.out.println("Recept id = 1, Raavare id = 1: ");
		try{RK.getReceptKomp(1, 1);}
		catch(DALException e){e.getMessage();}
		
		//DONE test getReceptKompList(raavareId)
		System.out.println("Alle Receptkomponenter med id 3:");
		try {
			RKList = RK.getReceptKompList(3);
				for (int i = 0; i < RKList.size(); i++){
					System.out.println(RKList.get(i));
				}
			}
		catch(DALException e){e.getMessage();}
		
		//DONE test getReceptKompList()
				
		System.out.println("Alle Receptkomponenter:");
		try {
			RKList = RK.getReceptKompList();
				for (int i = 0; i < RKList.size(); i++){
					System.out.println(RKList.get(i));
				}
			}
		catch(DALException e){e.getMessage();}
		
		//DONE test createReceptKomp(ReceptDTO)
		System.out.println("Ny Receptkomonent med recept id = 4, raavare id = 8, nomnetto = 20, tolerance = 0.2");
		try { RK.createReceptKomp(TestRK); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Receptkomponent 4: ");
		try { System.out.println(RK.getReceptKompList(4)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
				
		//DONE test updateReceptKomp(ReceptDTO)
		System.out.println("Opdatering af Receptkomponent med recept id = 1");
		TestRK.setTolerance(1000);
		try { RK.updateReceptKomp(TestRK); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Receptkomponent 4: ");
		try { System.out.println(RK.getReceptKompList(4)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
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
	
	private static void testView(){
		
		Views view = new Views();
		
//		System.out.println("Drop views:");
//		try {view.DropViews();}
//		catch(DALException e){System.out.println(e.getMessage());}
		
//		System.out.println("Implementing views:");
//		try {view.ImplementViews();}
//		catch(DALException e){System.out.println(e.getMessage());}
		
		System.out.println("Show view mad:");
		try {System.out.println(view.getMad(1, 1));}
		catch(DALException e){System.out.println(e.getMessage());}
		
		System.out.println("Show view vejning:");
		try {System.out.println(view.getVejning(1));}
		catch(DALException e){System.out.println(e.getMessage());}
		
		System.out.println("Show view mad list:");
		try {System.out.println(view.getMadList());}
		catch(DALException e){System.out.println(e.getMessage());}
		
		System.out.println("Show view vejning list:");
		try {System.out.println(view.getVejningList());}
		catch(DALException e){System.out.println(e.getMessage());}
		
	}
	
}

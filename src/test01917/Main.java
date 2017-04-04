package test01917;

import daoimpl01917.MySQLOperatoerDAO;
import daoimpl01917.MySQLProduktBatchDAO;
import daointerfaces01917.DALException;
import dto01917.OperatoerDTO;

import java.sql.SQLException;

import connector01917.Connector;

public class Main {
	public static void main(String[] args) {
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		
		testOPR(); //Operatoer test
		testPB(); //ProduktBatch test
		
		//TODO test remaining DAO classes
		
	}
	
	private static void testOPR(){
		System.out.println("Operatoer nummer 3:");
		MySQLOperatoerDAO opr = new MySQLOperatoerDAO();
		try { System.out.println(opr.getOperatoer(3)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Indsaettelse af ny operatoer med opr_id =  4");
		OperatoerDTO oprDTO = new OperatoerDTO(4,"Don Juan","DJ","000000-0000","iloveyou");
		try { opr.createOperatoer(oprDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }	
		
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
	}
	
	private static void testPB(){
		System.out.println("Produktbatch nummer 2:");
		MySQLProduktBatchDAO produktbatch = new MySQLProduktBatchDAO();
		try { System.out.println(produktbatch.getProduktBatch(2)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
	}
}

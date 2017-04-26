package views;

import java.sql.ResultSet;

import connector01917.Connector;
import connector01917.SQLMapper;
import daointerfaces01917.DALException;

import dto01917.ViewMadDTO;

public class Views {

	public void ImplementViews() throws DALException {
		
		String statement = SQLMapper.getStatement("view_create_vejning");
		Connector.setPreparedStatement(statement);
		System.out.println("View 1: DONE");
			
		statement = SQLMapper.getStatement("view_create_vejning");
		Connector.setPreparedStatement(statement);
		System.out.println("View 2: DONE");
		
	}
	
	public void DropViews() throws DALException {
		
		String statement = SQLMapper.getStatement("view_drop_mad");
		Connector.setPreparedStatement(statement);
		System.out.println("Drop 1: DONE");
		
		statement = SQLMapper.getStatement("view_drop_vejning");
		Connector.setPreparedStatement(statement);
		System.out.println("Drop 2: DONE");
		
	}
	
	
	
}

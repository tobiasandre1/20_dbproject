package views;

//import java.sql.ResultSet;

import connector01917.Connector;
import connector01917.SQLMapper;
import daointerfaces01917.DALException;

import dto01917.ViewMadDTO;

public class Views {

	public void ImplementViews() throws DALException {
		
		String statement = SQLMapper.getStatement("create_view_mad");
		Connector.doQuery(statement);
		
		String statement2 = SQLMapper.getStatement("create_view_vejning");
		Connector.doQuery(statement2);
		
	}
	
	
	
	
}

package views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import connector01917.SQLMapper;
import daointerfaces01917.DALException;
import dto01917.OperatoerDTO;
import dto01917.ViewMadDTO;
import dto01917.ViewVejningDTO;

public class Views {

	public void ImplementViews() throws DALException {
		
		String statement = SQLMapper.getStatement("view_create_vejning");
		Connector.doQuery(statement);
		System.out.println("View 1: DONE");
			
		statement = SQLMapper.getStatement("view_create_vejning");
		Connector.doQuery(statement);
		System.out.println("View 2: DONE");
		
	}
	
	public void DropViews() throws DALException {
		
		String statement = SQLMapper.getStatement("view_drop_mad");
		Connector.doQuery(statement);
		System.out.println("Drop 1: DONE");
		
		statement = SQLMapper.getStatement("view_drop_vejning");
		Connector.doQuery(statement);
		System.out.println("Drop 2: DONE");
		
	}
	
	public ViewMadDTO getMad(int recept_id, int raavare_id) throws DALException {
		
		String statement = SQLMapper.getStatement("view_SELECT_mad");
		String[] values = new String[]{Integer.toString(recept_id), Integer.toString(raavare_id)};
		statement = SQLMapper.insertValuesIntoString(statement, values);
		System.out.println("Query: "+statement);
		ResultSet rs = Connector.doQuery(statement);
		
	    try {
	    	if (!rs.first()) throw new DALException("Viewet for recept_id: " + recept_id + "og raavare_id: " + raavare_id + " findes ikke");
	    	return new ViewMadDTO(rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getString("recept_navn"), rs.getString("raavare_navn"), rs.getString("leverandoer")) ;
	    }
	    catch (SQLException e) {throw new DALException(e); }
		
	}
	
	public ViewVejningDTO getVejning(int opr_id) throws DALException {
		
		String statement = SQLMapper.getStatement("view_SELECT_vejning");
		String[] values = new String[]{Integer.toString(opr_id)};
		statement = SQLMapper.insertValuesIntoString(statement, values);
		System.out.println("Query: "+statement);
		ResultSet rs = Connector.doQuery(statement);
		
	    try {
	    	if (!rs.first()) throw new DALException("Viewet for opr_id: " + opr_id + " findes ikke");
	    	return new ViewVejningDTO(rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getInt("tara"), rs.getInt("netto"), rs.getInt("raavare_id"), rs.getString("raavare_navn"), rs.getInt("maengde")); 
	    }
	    catch (SQLException e) {throw new DALException(e); }
		
	}
	
	public List<ViewMadDTO> getMadList() throws DALException {
		List<ViewMadDTO> list = new ArrayList<ViewMadDTO>();
		ResultSet rs = Connector.doQuery(SQLMapper.getStatement("view_SELECTALL_mad"));
		try
		{
			while (rs.next()) 
			{
				list.add(new ViewMadDTO(rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getString("recept_navn"), rs.getString("raavare_navn"), rs.getString("leverandoer")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	public List<ViewVejningDTO> getVejningList() throws DALException {
		List<ViewVejningDTO> list = new ArrayList<ViewVejningDTO>();
		ResultSet rs = Connector.doQuery(SQLMapper.getStatement("view_SELECTALL_vejning"));
		try
		{
			while (rs.next()) 
			{
				list.add(new ViewVejningDTO(rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getInt("tara"), rs.getInt("netto"), rs.getInt("raavare_id"), rs.getString("raavare_navn"), rs.getInt("maengde")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}
	
}

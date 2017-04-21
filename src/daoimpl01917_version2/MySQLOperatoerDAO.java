package daoimpl01917_version2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.OperatoerDAO;
import dto01917.OperatoerDTO;
import connector01917.*;

public class MySQLOperatoerDAO implements OperatoerDAO {

	@Override
	public OperatoerDTO getOperatoer(int oprId) throws DALException {
		
		String statement = SQLMapper.getStatement("opr_SELECT");
		String[] values = new String[]{Integer.toString(oprId)};
		statement = SQLMapper.insertValuesIntoString(statement, values);
		System.out.println("Query: "+statement);
		ResultSet rs = Connector.doQuery(statement);
		
	    try {
	    	if (!rs.first()) throw new DALException("Operatoeren " + oprId + " findes ikke");
	    	return new OperatoerDTO (rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
		
	}

	@Override
	public List<OperatoerDTO> getOperatoerList() throws DALException {
		List<OperatoerDTO> list = new ArrayList<OperatoerDTO>();
		ResultSet rs = Connector.doQuery(SQLMapper.getStatement("opr_SELECT_ALL"));
		try
		{
			while (rs.next()) 
			{
				list.add(new OperatoerDTO(rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createOperatoer(OperatoerDTO opr) throws DALException {
		String statement = SQLMapper.getStatement("opr_INSERT");
		String[] values = new String[]{Integer.toString(opr.getOprId()), opr.getOprNavn(), opr.getIni(), opr.getCpr(), opr.getPassword()};
		statement = SQLMapper.insertValuesIntoString(statement, values);
		System.out.println(statement);
		Connector.doUpdate(statement);

	}

	@Override
	public void updateOperatoer(OperatoerDTO opr) throws DALException {
		String statement = SQLMapper.getStatement("opr_UPDATE");
		String[] values = new String[]{opr.getOprNavn(), opr.getIni(), opr.getCpr(), opr.getPassword(), Integer.toString(opr.getOprId())}; //Only difference from INSERT is operatoer id is at the end
		statement = SQLMapper.insertValuesIntoString(statement, values);
		System.out.println(statement);
		Connector.doUpdate(statement);

	}

}

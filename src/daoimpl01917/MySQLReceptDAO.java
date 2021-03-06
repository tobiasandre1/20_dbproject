package daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import connector01917.SQLMapper;
import daointerfaces01917.DALException;
import daointerfaces01917.ReceptDAO;
import dto01917.ReceptDTO;

/**
 * @author Frederik
 *
 */
public class MySQLReceptDAO implements ReceptDAO {

	@Override
	public ReceptDTO getRecept(int receptId) throws DALException {
		String statement = SQLMapper.getStatement("rec_SELECT");
		String[] values = new String[]{Integer.toString(receptId)};
		statement = SQLMapper.insertValuesIntoString(statement, values);
		System.out.println("Query: "+statement);
		ResultSet rs = Connector.doQuery(statement);
		
	    try {
	    	if (!rs.first()) throw new DALException("Recepten " + receptId + " findes ikke");
	    	return new ReceptDTO (rs.getInt("recept_id"), rs.getString("recept_navn"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<ReceptDTO> getReceptList() throws DALException {
		List<ReceptDTO> list = new ArrayList<ReceptDTO>();
		ResultSet rs = Connector.doQuery(SQLMapper.getStatement("rec_SELECT_ALL"));
		try
		{
			while (rs.next()) 
			{
				list.add(new ReceptDTO (rs.getInt("recept_id"), rs.getString("recept_navn")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createRecept(ReceptDTO recept) throws DALException {
		String statement = SQLMapper.getStatement("rec_INSERT");
		String[] values = new String[]{
								Integer.toString(recept.getReceptId()), 
								recept.getReceptNavn()
				};
		statement = SQLMapper.insertValuesIntoString(statement, values);
		System.out.println(statement);
		Connector.doUpdate(statement);

	}

	@Override
	public void updateRecept(ReceptDTO recept) throws DALException {
		String statement = SQLMapper.getStatement("rec_UPDATE");
		String[] values = new String[]{
						recept.getReceptNavn(),	
						Integer.toString(recept.getReceptId())
								
				};
		statement = SQLMapper.insertValuesIntoString(statement, values);
		System.out.println(statement);
		Connector.doUpdate(statement);

	}

}

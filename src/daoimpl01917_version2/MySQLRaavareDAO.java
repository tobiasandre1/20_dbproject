package daoimpl01917_version2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import connector01917.SQLMapper;
import daointerfaces01917.DALException;
import daointerfaces01917.RaavareDAO;
import dto01917.RaavareDTO;

/**
 * @author Tobias
 *
 */
public class MySQLRaavareDAO implements RaavareDAO {

	@Override
	public RaavareDTO getRaavare(int raavareId) throws DALException {
		String statement = SQLMapper.getStatement("ra_SELECT");
		String[] values = new String[]{Integer.toString(raavareId)};
		statement = SQLMapper.insertValuesIntoString(statement, values);
		System.out.println("Query: "+statement);
		ResultSet rs = Connector.doQuery(statement);
	    try {
	    	if (!rs.first()) throw new DALException("Raavaren " + raavareId + " findes ikke");
	    	return new RaavareDTO (rs.getInt("raavare_id"), rs.getString("raavare_navn"), rs.getString("leverandoer"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<RaavareDTO> getRaavareList() throws DALException {
		List<RaavareDTO> list = new ArrayList<RaavareDTO>();
		ResultSet rs = Connector.doQuery(SQLMapper.getStatement("ra_SELECT_ALL"));
		try
		{
			while (rs.next()) 
			{
				list.add(new RaavareDTO(rs.getInt("raavare_id"), rs.getString("raavare_navn"), rs.getString("leverandoer")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createRaavare(RaavareDTO raavare) throws DALException {
		String statement = SQLMapper.getStatement("ra_INSERT");
		String[] values = new String[]{
				Integer.toString(raavare.getRaavareId()),
				raavare.getRaavareNavn(),
				raavare.getLeverandoer()
			};
		statement = SQLMapper.insertValuesIntoString(statement, values);
		System.out.println(statement);
		Connector.doUpdate(statement);

	}

	@Override
	public void updateRaavare(RaavareDTO raavare) throws DALException {
		String statement = SQLMapper.getStatement("ra_UPDATE");
		String[] values = new String[]{
				raavare.getRaavareNavn(),
				raavare.getLeverandoer(),
				Integer.toString(raavare.getRaavareId())
			};
		statement = SQLMapper.insertValuesIntoString(statement, values);
		System.out.println(statement);
		Connector.doUpdate(statement);

	}

}

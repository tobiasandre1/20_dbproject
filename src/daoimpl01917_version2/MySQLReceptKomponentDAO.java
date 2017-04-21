package daoimpl01917_version2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import connector01917.SQLMapper;
import daointerfaces01917.DALException;
import daointerfaces01917.ReceptKompDAO;
import dto01917.ReceptKompDTO;

/**
 * @author Theis
 *
 */
public class MySQLReceptKomponentDAO implements ReceptKompDAO {

	@Override
	public ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws DALException {
		String statement = SQLMapper.getStatement("rec_komponent_SELECT");
		String[] values = new String[]{Integer.toString(receptId), Integer.toString(raavareId)};
		statement = SQLMapper.insertValuesIntoString(statement, values);
		System.out.println("Query: "+statement);
		ResultSet rs = Connector.doQuery(statement);
		
	    try {
	    	if (!rs.first()) throw new DALException("Receptkomponenten for recept " + receptId + " og raavare " + raavareId + " findes ikke");
	    	return new ReceptKompDTO (rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getDouble("nom_netto"), rs.getDouble("tolerance"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<ReceptKompDTO> getReceptKompList(int receptId) throws DALException {
		List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();
		String statement = SQLMapper.getStatement("rec_komponent_SELECT_ALL");
		statement = SQLMapper.insertValuesIntoString(statement, new String[]{Integer.toString(receptId)});
		ResultSet rs = Connector.doQuery(statement);
		try
		{
			while (rs.next()) 
			{
				list.add(new ReceptKompDTO(rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getDouble("nom_netto"), rs.getDouble("tolerance")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public List<ReceptKompDTO> getReceptKompList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
		// TODO Auto-generated method stub

	}

}

/**
 * 
 */
package daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.ProduktBatchDAO;
import dto01917.ProduktBatchDTO;

/**
 * @author Tobias
 *
 */
public class MySQLProduktBatchDAO implements ProduktBatchDAO {

	@Override
	public ProduktBatchDTO getProduktBatch(int pbId) throws DALException {
		/*
		 * We have imported our connector class. It's static, 
		 * so we can use the methods within it without having to create an instance of it.
		 * 
		 * We can store the result of a query in the class ResultSet
		 */
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatch WHERE pb_id = " + pbId);
	    //Result is stored ^
		try {
	    	if (!rs.first()) throw new DALException("Produkt batch " + pbId + " findes ikke");
	    	/*
	    	 * If no rows are returned, 
	    	 * that must mean that there is no batch with the given ID ^
	    	 * We throw an exception because there is no object with the given ID.
	    	 */
	    	return new ProduktBatchDTO (rs.getInt("pb_id"), rs.getInt("status"), rs.getInt("recept_id"));
	    	//If there is a result returned, then we create a new object from it. ^
		}
	    catch (SQLException e) {throw new DALException(e); }
		//We also check for SQL exceptions ^
	}

	@Override
	public List<ProduktBatchDTO> getProduktBatchList() throws DALException {
		/*
		 * We return a list of all the product batches. 
		 * Our query selects all present elements in the table.
		 */
		List<ProduktBatchDTO> list = new ArrayList<ProduktBatchDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatch");
		try
		{
			while (rs.next()) 
			{
				list.add(new ProduktBatchDTO (rs.getInt("pb_id"), rs.getInt("status"), rs.getInt("recept_id")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createProduktBatch(ProduktBatchDTO produktbatch) throws DALException {
		//We do an insert query with the data in our DTO.
		Connector.doUpdate(
				"INSERT INTO produktbatch(pb_id, status, recept_id) VALUES " +
				"(" + produktbatch.getPbId() + ", '" + produktbatch.getStatus() + 
				"', '" + produktbatch.getReceptId() + "')"
		); //End of insert
	}


	@Override
	public void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException {
		//We do an update query in the same way as our create method.
		Connector.doUpdate(
				"UPDATE produktbatch SET pb_id = " + produktbatch.getPbId()
				+ ", status = " + produktbatch.getStatus()
				+ ", recept_id = " + produktbatch.getReceptId()
				+ " WHERE pb_id = " + produktbatch.getPbId()
		); //End of update
	}

}

package tr.com.yakanyazilim.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.yakanyazilim.core.ObjectHelper;
import tr.com.yakanyazilim.interfaces.DALInterfaces;
import tr.com.yakanyazilim.types.SehirlerContract;

public class SehirlerDAL extends ObjectHelper implements DALInterfaces<SehirlerContract> {

	public SehirlerDAL() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Insert(SehirlerContract contract) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO Sehirler (SehirAdi) VALUES(' " + contract.getSehirAdi() + "')");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<SehirlerContract> GetAll() {
		List<SehirlerContract> dataContract = new ArrayList<SehirlerContract>();
		Connection connection = getConnection();
		SehirlerContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resulset = statement.executeQuery("SELECT * FROM Sehirler");// gelen sorguyu resulset icine
																					// dolduracak
			while (resulset.next()) {// while ile domainlerin icine set ediyoruz
				contract = new SehirlerContract();

				contract.setSehirAdi(resulset.getString("SehirAdi"));
				contract.setSehirId(resulset.getInt("SehirId"));
				dataContract.add(contract);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataContract;
	}

	@Override
	public SehirlerContract Delete(SehirlerContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(SehirlerContract entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SehirlerContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}

package tr.com.yakanyazilim.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.yakanyazilim.core.ObjectHelper;
import tr.com.yakanyazilim.interfaces.DALInterfaces;
import tr.com.yakanyazilim.types.YetkilerContract;

public class YetkilerDAL extends ObjectHelper implements DALInterfaces<YetkilerContract> {

	@Override
	public void Insert(YetkilerContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO Yetkiler (Adi) VALUES('" + entity.getAdi() + "')");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<YetkilerContract> GetAll() {
		List<YetkilerContract> dataContract = new ArrayList<YetkilerContract>();
		Connection connection = getConnection();
		YetkilerContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resulset = statement.executeQuery("SELECT * FROM Yetkiler");// gelen sorguyu resulset icine
																					// dolduracak
			while (resulset.next()) {// while ile domainlerin icine set ediyoruz
				contract = new YetkilerContract();
				contract.setId(resulset.getInt("Id"));
				contract.setAdi(resulset.getString("Adi"));

				dataContract.add(contract);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataContract;
	}

	@Override
	public YetkilerContract Delete(YetkilerContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(YetkilerContract entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<YetkilerContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}

package tr.com.yakanyazilim.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.yakanyazilim.core.ObjectHelper;
import tr.com.yakanyazilim.interfaces.DALInterfaces;
import tr.com.yakanyazilim.types.PersonelContract;

public class PersonelDAL extends ObjectHelper implements DALInterfaces<PersonelContract> {

	@Override
	public void Insert(PersonelContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO Personel (AdiSoyadi, Email) VALUES('" + entity.getAdiSoyadi() + "','"
					+ entity.getEmail() + "')");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<PersonelContract> GetAll() {
		List<PersonelContract> dataContract = new ArrayList<PersonelContract>();
		Connection connection = getConnection();
		PersonelContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resulset = statement.executeQuery("SELECT * FROM Personel");// gelen sorguyu resulset icine
																					// dolduracak
			while (resulset.next()) {// while ile domainlerin icine set ediyoruz
				contract = new PersonelContract();
				contract.setId(resulset.getInt("Id"));
				contract.setAdiSoyadi(resulset.getString("AdiSoyadi"));
				contract.setEmail(resulset.getString("Email"));
				dataContract.add(contract);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataContract;
	}

	@Override
	public PersonelContract Delete(PersonelContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(PersonelContract entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PersonelContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}

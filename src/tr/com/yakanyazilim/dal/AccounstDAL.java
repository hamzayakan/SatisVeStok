package tr.com.yakanyazilim.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.yakanyazilim.core.ObjectHelper;
import tr.com.yakanyazilim.interfaces.DALInterfaces;
import tr.com.yakanyazilim.types.AccountsContract;

public class AccounstDAL extends ObjectHelper implements DALInterfaces<AccountsContract> {

	@Override
	public void Insert(AccountsContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO Accounts (PersonelId,YetkiId,Sifre) VALUES(" + entity.getPersonelId()
					+ "," + entity.getYetkiId() + ",'" + entity.getSifre() + "')");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public AccountsContract GetPersonelVeSifre(int personelId, String sifre) {

		AccountsContract contract = new AccountsContract();
		List<AccountsContract> dataContract = new ArrayList<AccountsContract>();
		Connection connection = getConnection();

		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet resulset = statement.executeQuery(
					"SELECT * FROM accounts WHERE PersonelId =" + personelId + " AND Sifre='" + sifre + "' ");

			while (resulset.next()) {
				contract.setId(resulset.getInt("Id"));
				contract.setPersonelId(resulset.getInt("PersonelId"));
				contract.setSifre(resulset.getString("Sifre"));
				contract.setYetkiId(resulset.getInt("YetkiId"));

			}
			statement.close();
			resulset.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return contract;

	}

	public AccountsContract GetYetkiId(int personelId) {

		AccountsContract contract = new AccountsContract();
		List<AccountsContract> dataContract = new ArrayList<AccountsContract>();
		Connection connection = getConnection();

		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet resulset = statement.executeQuery("SELECT * FROM accounts WHERE PersonelId =" + personelId + "");

			while (resulset.next()) {
				contract.setId(resulset.getInt("Id"));
				contract.setPersonelId(resulset.getInt("PersonelId"));
				contract.setYetkiId(resulset.getInt("YetkiId"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return contract;

	}

	@Override
	public List<AccountsContract> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountsContract Delete(AccountsContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(AccountsContract entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<AccountsContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}

package tr.com.yakanyazilim.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.yakanyazilim.core.ObjectHelper;
import tr.com.yakanyazilim.interfaces.DALInterfaces;
import tr.com.yakanyazilim.types.KategoriContract;

public class KategoriDal extends ObjectHelper implements DALInterfaces<KategoriContract> {

	@Override
	public void Insert(KategoriContract entity) {

		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO Kategori (Adi, ParentId) VALUES('" + entity.getAdi() + "',"
					+ entity.getParentId() + ")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<KategoriContract> GetAll() {

		List<KategoriContract> dataContract = new ArrayList<KategoriContract>();
		Connection connection = getConnection();
		KategoriContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resulset = statement.executeQuery("SELECT * FROM Kategori");// gelen sorguyu resulset icine
																					// dolduracak
			while (resulset.next()) {// while ile domainlerin icine set ediyoruz
				contract = new KategoriContract();
				contract.setId(resulset.getInt("Id"));
				contract.setAdi(resulset.getString("Adi"));
				contract.setParentId(resulset.getInt("ParentId"));

				dataContract.add(contract);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataContract;
	}

	public List<KategoriContract> GetAllParentId() {

		List<KategoriContract> dataContract = new ArrayList<KategoriContract>();
		Connection connection = getConnection();
		KategoriContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resulset = statement.executeQuery("SELECT * FROM Kategori WHERE parentId=0");// gelen sorguyu
																									// resulset
																									// icine
			// dolduracak
			while (resulset.next()) {// while ile domainlerin icine set ediyoruz
				contract = new KategoriContract();
				contract.setId(resulset.getInt("Id"));
				contract.setAdi(resulset.getString("Adi"));
				contract.setParentId(resulset.getInt("ParentId"));

				dataContract.add(contract);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataContract;
	}

	@Override
	public KategoriContract Delete(KategoriContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(KategoriContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("UPDATE Kategori SET Adi='" + entity.getAdi() + "', ParentId="
					+ entity.getParentId() + "WHERE id=" + entity.getId() + "");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<KategoriContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<KategoriContract> GetSearchKategori(String kategoriAdi) {

		List<KategoriContract> dataContract = new ArrayList<KategoriContract>();

		Connection connection = getConnection();

		try {
			Statement statement = connection.createStatement();

			ResultSet resulset = statement.executeQuery("SELECT *FROM Kategori WHERE Adi Like '%" + kategoriAdi + "%'");

			while (resulset.next()) {

				KategoriContract contract = new KategoriContract();

				contract.setAdi(resulset.getString("Adi"));
				contract.setParentId(resulset.getInt("ParentId"));
				dataContract.add(contract);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataContract;
	}

}

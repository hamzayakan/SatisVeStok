package tr.com.yakanyazilim.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.yakanyazilim.complex.types.SatisContractComplex;
import tr.com.yakanyazilim.core.ObjectHelper;
import tr.com.yakanyazilim.interfaces.DALInterfaces;
import tr.com.yakanyazilim.types.SatisContract;

public class SatisDAL extends ObjectHelper implements DALInterfaces<SatisContract> {

	@Override
	public void Insert(SatisContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO Satis (UrunId, MusteriId, Tarih, Adet, PersonelId) VALUES("
					+ entity.getUrunId() + "," + entity.getMusteriId() + ",'" + entity.getTarih() + "',"
					+ entity.getAdet() + "," + entity.getPersonelId() + ")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<SatisContractComplex> GetAllSatis() {
		// bir list olusturuyoruz(dataContract). olusturdugumuz list in icerisine
		// contract atýyoruz.
		List<SatisContractComplex> dataContract = new ArrayList<SatisContractComplex>();
		Connection connection = getConnection();
		SatisContractComplex contract;
		try {
			Statement statement = connection.createStatement();
			// tablo birleþtirme yontemi ile verileri aldik
			ResultSet resulset = statement.executeQuery(
					"SELECT satis.Id, personel.AdiSoyadi, musteri.AdiSoyadi,Adi, Adet, satis.Tarih FROM satis "
							+ "LEFT JOIN musteri on satis.MusteriId = musteri.Id "
							+ "LEFT JOIN urunler on satis.UrunId = urunler.Id "
							+ "LEFT JOIN personel on satis.PersonelId = personel.Id ORDER BY satis.Id DESC");

			while (resulset.next()) {
				// new islemini while disinda yaparsak ev son veriyi listeye yazdirir
				contract = new SatisContractComplex();
				// burada bir listeleme yaptik
				contract.setId(resulset.getInt("Id"));
				contract.setMusteriAdi(resulset.getString("musteri.AdiSoyadi"));
				contract.setPersonelAdi(resulset.getString("personel.AdiSoyadi"));
				contract.setTarih(resulset.getString("satis.Tarih"));
				contract.setUrunAdi(resulset.getString("Adi"));
				contract.setAdet(resulset.getInt("Adet"));

				// dizi gibi calistigi icin teker teker butun eklemeleri yapariz
				dataContract.add(contract);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataContract;
	}

	@Override
	public SatisContract Delete(SatisContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SatisContract> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(SatisContract entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SatisContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}

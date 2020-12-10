package tr.com.yakanyazilim.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import tr.com.yakanyazilim.dal.KategoriDal;
import tr.com.yakanyazilim.dal.UrunlerDAL;
import tr.com.yakanyazilim.interfaces.FeInterfaces;
import tr.com.yakanyazilim.types.KategoriContract;
import tr.com.yakanyazilim.types.UrunlerContract;

public class UrunEkleFE extends JDialog implements FeInterfaces {// dialog sadece bu pencere kapansýn diye JFrame
																	// olsaysýn hepsi kapanacaktý

	public UrunEkleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		panel.setBorder(BorderFactory.createTitledBorder("Ürün Kayit Alani"));
		add(panel);
		setTitle("Ürün Ekleyin");
		pack();// boyutlarý otomatik versin
		setModalityType(DEFAULT_MODALITY_TYPE);// panel açýkken digerine geçmemesi icin
		setLocationRelativeTo(null);// pencere açýldýðýnda merkezde dursun
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(5, 2));

		JLabel adiLabel = new JLabel("Ürün Adý:", SwingConstants.RIGHT);
		panel.add(adiLabel);
		JTextField adiField = new JTextField(10);
		panel.add(adiField);

		JLabel kategoriLabel = new JLabel("Kategori Seç:", SwingConstants.RIGHT);
		panel.add(kategoriLabel);
		JComboBox kategoriBox = new JComboBox(new KategoriDal().GetAll().toArray());
		panel.add(kategoriBox);

		JLabel tarihLabel = new JLabel("tarih yazýnýz:", SwingConstants.RIGHT);
		panel.add(tarihLabel);
		JDateChooser tarihDate = new JDateChooser();
		panel.add(tarihDate);

		JLabel fiyatLabel = new JLabel("Fiyat gir:", SwingConstants.RIGHT);
		panel.add(fiyatLabel);

		JTextField fiyatField = new JTextField(10);
		panel.add(fiyatField);

		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);

		JButton iptalButton = new JButton("Ýptal");
		panel.add(iptalButton);

		kaydetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UrunlerContract contract = new UrunlerContract();
				KategoriContract castContract = (KategoriContract) kategoriBox.getSelectedItem();

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

				String date = format.format(tarihDate.getDate());

				contract.setAdi(adiField.getText());
				contract.setKategoriId(castContract.getId());
				contract.setTarih(date);
				contract.setFiyat(Float.parseFloat(fiyatField.getText()));

				new UrunlerDAL().Insert(contract);
				JOptionPane.showMessageDialog(null,
						contract.getAdi() + " adlý Urun  Baþarýlý Bir Þekilde Kayýt Edilmiþtir");
			}

		});
		return panel;
	}

	@Override
	public JMenuBar initBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JTabbedPane initTabs() {
		// TODO Auto-generated method stub
		return null;
	}

}

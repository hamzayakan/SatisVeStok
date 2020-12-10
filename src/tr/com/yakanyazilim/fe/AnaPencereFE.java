package tr.com.yakanyazilim.fe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import tr.com.yakanyazilim.complex.types.SatisContractComplex;
import tr.com.yakanyazilim.complex.types.StokContractComplex;
import tr.com.yakanyazilim.complex.types.StokContractTotalComplex;
import tr.com.yakanyazilim.dal.MusteriDAL;
import tr.com.yakanyazilim.dal.SatisDAL;
import tr.com.yakanyazilim.dal.StokDAL;
import tr.com.yakanyazilim.dal.UrunlerDAL;
import tr.com.yakanyazilim.interfaces.FeInterfaces;
import tr.com.yakanyazilim.types.MusteriContract;
import tr.com.yakanyazilim.types.PersonelContract;
import tr.com.yakanyazilim.types.SatisContract;
import tr.com.yakanyazilim.types.StokContract;
import tr.com.yakanyazilim.types.UrunlerContract;
import tr.com.yakanyazilim.utilities.MenulerCom;

public class AnaPencereFE extends JFrame implements FeInterfaces {

	public AnaPencereFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		// JTabbedPane tabs = initTabs();
		JMenuBar bar = initBar();

		add(panel);
		setJMenuBar(bar);
		setTitle("Satýþ ve Stok Programý");
		setSize(600, 250);
		setVisible(true);
		setLocationRelativeTo(null);// pencereyi olusturdugumuzda ne sag sol üst altta degil. merkezde dursun
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JTabbedPane pane = initTabs();
		panel.add(pane, BorderLayout.CENTER);
		return panel;
	}

	@Override
	public JMenuBar initBar() {
		JMenuBar bar = MenulerCom.initBar();

		return bar;
	}

	@Override
	public JTabbedPane initTabs() {
		JTabbedPane pane = new JTabbedPane();
		ImageIcon icon = new ImageIcon("icons/stockIcon.png");
		ImageIcon icon2 = new ImageIcon("icons/stockIcon.png");

		JPanel stokPanel = new JPanel(new BorderLayout());
		JPanel satisPanel = new JPanel(new BorderLayout());
		// *Stok itemleri*//
		JPanel stokSolPanel = new JPanel(new BorderLayout());
		JPanel stokSolUstPanel = new JPanel(new GridLayout(5, 2));
		JPanel stokSolAltPanel = new JPanel();

		stokSolPanel.setBorder(BorderFactory.createTitledBorder("Stok Ýþlemleri"));
		Object[] stokKolonlar = { "Id", "Ürün Adý", "Personel adý", "Adeti", "Tarihi", "Toplam" };
		DefaultTableModel model = new DefaultTableModel(stokKolonlar, 0);
		JTable table = new JTable(model);
		JScrollPane stokTablePane = new JScrollPane(table);

		for (StokContractComplex contract : new StokDAL().GetAllStok()) {
			model.addRow(contract.getVeriler());
		}

		JLabel stokUrunAdiLabel = new JLabel("Ürün Adý:", SwingConstants.RIGHT);
		stokSolUstPanel.add(stokUrunAdiLabel);
		JComboBox stokUrunAdiBox = new JComboBox(new UrunlerDAL().GetAll().toArray());
		stokSolUstPanel.add(stokUrunAdiBox);

		JLabel stokAdetLabel = new JLabel("Adet:", SwingConstants.RIGHT);
		stokSolUstPanel.add(stokAdetLabel);
		JTextField stokAdetField = new JTextField(10);
		stokSolUstPanel.add(stokAdetField);
		JLabel stokTarihiLabel = new JLabel("Stok Tarihi:", SwingConstants.RIGHT);
		stokSolUstPanel.add(stokTarihiLabel);
		JDateChooser stokTarihi = new JDateChooser();
		stokSolUstPanel.add(stokTarihi);

		JButton stokEkleButton = new JButton("Stok Ekle");
		stokSolUstPanel.add(stokEkleButton);
		JButton stokYenileButton = new JButton("Yenile");
		stokSolUstPanel.add(stokYenileButton);
		JButton stokTotalButton = new JButton("Stok Toplam Ürün");
		stokSolUstPanel.add(stokTotalButton);

		stokTotalButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int satir = model.getRowCount();
				for (int i = 0; i < satir; i++) {
					model.removeRow(0);// temizleyip yeniden veri
				}
				for (StokContractTotalComplex total : new StokDAL().GetTotalStok()) {
					model.addRow(total.getVeriler());
				}
			}
		});

		stokYenileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int satir = model.getRowCount();
				for (int i = 0; i < satir; i++) {
					model.removeRow(0);// temizleyip yeniden veri
				}
				for (StokContractComplex compContract : new StokDAL().GetAllStok()) {
					model.addRow(compContract.getVeriler());
				}

			}
		});

		stokEkleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StokContract contract = new StokContract();
				UrunlerContract uContract = (UrunlerContract) stokUrunAdiBox.getSelectedItem();
				PersonelContract pContract = (PersonelContract) LoginFE.emailBox.getSelectedItem();

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String date = format.format(stokTarihi.getDate());

				contract.setPersonelId(pContract.getId());
				contract.setUrunId(uContract.getId());
				contract.setTarih(date.toString());
				contract.setAdet(Integer.parseInt(stokAdetField.getText()));
				new StokDAL().Insert(contract);

				JOptionPane.showMessageDialog(null, uContract.getAdi() + " adlý ürün eklenmiþtir");
				// otomatik getirtmek icin
				int satir = model.getRowCount();
				for (int i = 0; i < satir; i++) {
					model.removeRow(0);// temizleyip yeniden veri
				}
				for (StokContractComplex compContract : new StokDAL().GetAllStok()) {
					model.addRow(compContract.getVeriler());
				}

			}
		});

		/* Satis itemleri */
		JPanel satisSagPanel = new JPanel(new BorderLayout());
		JPanel satisSagUstPanel = new JPanel(new GridLayout(6, 2));
		JPanel satisSagAltPanel = new JPanel();

		Object[] satisKolonlar = { "Id", "Müþteri adý", "Personel Adý", "Ürün Adý", "Adeti", "Tarihi" };
		DefaultTableModel satisModel = new DefaultTableModel(satisKolonlar, 0);
		JTable satisTable = new JTable(satisModel);
		JScrollPane satisTablePane = new JScrollPane(satisTable);

		JLabel musteriLabel = new JLabel("Müþteri Adi", SwingConstants.RIGHT);
		satisSagUstPanel.add(musteriLabel);
		JComboBox musteriAdiBox = new JComboBox(new MusteriDAL().GetAll().toArray());
		satisSagUstPanel.add(musteriAdiBox);

		JLabel satisUrunAdiLabel = new JLabel("Ürün Adý:", SwingConstants.RIGHT);
		satisSagUstPanel.add(satisUrunAdiLabel);
		JComboBox satisUrunAdiBox = new JComboBox(new UrunlerDAL().GetAll().toArray());
		satisSagUstPanel.add(satisUrunAdiBox);

		JLabel satisAdetLabel = new JLabel("Adet:", SwingConstants.RIGHT);
		satisSagUstPanel.add(satisAdetLabel);
		JTextField satisAdetField = new JTextField(10);
		satisSagUstPanel.add(satisAdetField);
		JLabel satisTarihiLabel = new JLabel("Satýþ Tarihi:", SwingConstants.RIGHT);
		satisSagUstPanel.add(satisTarihiLabel);
		JDateChooser satisTarihi = new JDateChooser();
		satisSagUstPanel.add(satisTarihi);

		JButton satisEkleButton = new JButton("Satýþ Yap");
		satisSagUstPanel.add(satisEkleButton);
		JButton satisYenileButton = new JButton("Yenile");
		satisSagUstPanel.add(satisYenileButton);
		for (SatisContractComplex contract : new SatisDAL().GetAllSatis()) {
			satisModel.addRow(contract.getVeriler());
		}

		satisEkleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// combobox tan gelen urun ve musteri verilerini listelettirdik. login
				// penceresindeki emailBox tan gelen veriyle bu üçünü cast ettikten sonra satis
				// ekleme kýsmýna bunlarý eklettirdik
				PersonelContract pContract = (PersonelContract) LoginFE.emailBox.getSelectedItem();
				UrunlerContract uContract = (UrunlerContract) satisUrunAdiBox.getSelectedItem();
				MusteriContract mContract = (MusteriContract) musteriAdiBox.getSelectedItem();
				SatisContract contract = new SatisContract();

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String date = format.format(satisTarihi.getDate());

				contract.setMusteriId(mContract.getId());
				contract.setPersonelId(pContract.getId());
				contract.setUrunId(uContract.getId());
				contract.setAdet(Integer.parseInt(satisAdetField.getText()));
				contract.setTarih(date.toString());

				new SatisDAL().Insert(contract);
				StokContract stokContract = new StokContract();

				stokContract.setPersonelId(pContract.getId());
				stokContract.setUrunId(uContract.getId());
				stokContract.setAdet(-Integer.parseInt(satisAdetField.getText()));
				stokContract.setTarih(date.toString());
				new StokDAL().Insert(stokContract);

				JOptionPane.showMessageDialog(null,
						mContract.getAdiSoyadi() + " adlý müþteriye satýþ gerçekleþtirilmiþtir ve " + uContract.getAdi()
								+ " adlý üründen stokta " + contract.getAdet() + " adet eksiltilmiþtir.");

				int satir = satisModel.getRowCount();
				for (int i = 0; i < satir; i++) {
					satisModel.removeRow(0);// temizleyip yeniden veri
				}
				// satistan gelen veriler yenileContract olarak gelicek. yenileContract icindeki
				// getVeriler i çekecek. bu sekilde tablomuza eklemis olduk
				for (SatisContractComplex yenileContract : new SatisDAL().GetAllSatis()) {

					satisModel.addRow(yenileContract.getVeriler());
				}
			}
		});

		satisYenileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int satir = satisModel.getRowCount();
				for (int i = 0; i < satir; i++) {
					satisModel.removeRow(0);// temizleyip yeniden veri
				}
				for (SatisContractComplex contract : new SatisDAL().GetAllSatis()) {
					satisModel.addRow(contract.getVeriler());
				}

			}
		});
		stokPanel.add(stokSolPanel, BorderLayout.WEST);
		stokPanel.add(stokTablePane, BorderLayout.CENTER);

		satisPanel.add(satisSagPanel, BorderLayout.EAST);
		satisPanel.add(satisTablePane, BorderLayout.CENTER);

		satisSagPanel.add(satisSagUstPanel, BorderLayout.NORTH);
		satisSagPanel.add(satisSagAltPanel, BorderLayout.SOUTH);

		stokSolPanel.add(stokSolUstPanel, BorderLayout.NORTH);
		stokSolPanel.add(stokSolAltPanel, BorderLayout.SOUTH);

		pane.addTab("Stoklar", icon, stokPanel, "Does nothing");
		pane.addTab("Satýþlar", icon2, satisPanel, "yazý");

		return pane;
	}

}

package tr.com.yakanyazilim.utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import tr.com.yakanyazilim.dal.AccounstDAL;
import tr.com.yakanyazilim.fe.KategoriDuzenleFE;
import tr.com.yakanyazilim.fe.KategoriEkleFE;
import tr.com.yakanyazilim.fe.LoginFE;
import tr.com.yakanyazilim.fe.MusteriEkleFE;
import tr.com.yakanyazilim.fe.PersonelEkleFE;
import tr.com.yakanyazilim.fe.SifreIslemleriFE;
import tr.com.yakanyazilim.fe.UrunEkleFE;
import tr.com.yakanyazilim.fe.YetkiEkleFE;
import tr.com.yakanyazilim.fe.sehirEkleFE;
import tr.com.yakanyazilim.types.PersonelContract;

public class MenulerCom {

	public static JMenuBar initBar() {
		JMenuBar bar = new JMenuBar();

		JMenu dosyaMenu = new JMenu("Dosya");
		bar.add(dosyaMenu);

		JMenuItem cikisItem = new JMenuItem("Çýkýþ");
		dosyaMenu.add(cikisItem);

		// Urunler Menusu
		JMenu urunlerMenu = new JMenu("Ürünler");
		bar.add(urunlerMenu);
		JMenuItem urunEkleItem = new JMenuItem("Ürün Ekle");
		urunlerMenu.add(urunEkleItem);
		JMenuItem kategoriEkleItem = new JMenuItem("Kategori Ekle");
		urunlerMenu.add(kategoriEkleItem);
		urunlerMenu.addSeparator();
		JMenuItem urunDuzenleItem = new JMenuItem("Ürünü Düzenle");
		urunlerMenu.add(urunDuzenleItem);
		JMenuItem KategoriDuzenleItem = new JMenuItem("Kategoryi Düzenle");
		urunlerMenu.add(KategoriDuzenleItem);
		// Personel Menusu
		JMenu personellerMenu = new JMenu("Personeller");
		bar.add(personellerMenu);
		JMenuItem personelEkleItem = new JMenuItem("Personel Ekle ");
		personellerMenu.add(personelEkleItem);
		JMenuItem yetkiEkleItem = new JMenuItem("Yetki Ekle");
		personellerMenu.add(yetkiEkleItem);
		JMenuItem sifreBelirleItem = new JMenuItem("Sifre Belirleme");
		personellerMenu.add(sifreBelirleItem);
		personellerMenu.addSeparator();

		JMenu personellerDuzenle = new JMenu("Personel Düzenle");
		personellerMenu.add(personellerDuzenle);
		JMenuItem yetkiDuzenle = new JMenuItem("Yetki Duzenle");
		personellerMenu.add(yetkiDuzenle);
		JMenuItem sifreDuzenle = new JMenuItem("Þifre Duzenle");
		personellerMenu.add(sifreDuzenle);

		/* Müsteri Menusu */
		JMenu musterilerMenu = new JMenu("Müþteriler");
		bar.add(musterilerMenu);
		JMenuItem musteriEkleItem = new JMenuItem("Müþteri Ekle ");
		musterilerMenu.add(musteriEkleItem);
		JMenuItem sehirEkleItem = new JMenuItem("Þehir Ekle ");
		musterilerMenu.add(sehirEkleItem);
		musterilerMenu.addSeparator();

		PersonelContract contract = (PersonelContract) LoginFE.emailBox.getSelectedItem();

		if (new AccounstDAL().GetYetkiId(contract.getId()).getYetkiId() == 5) {
			personellerMenu.hide();
		} else if (new AccounstDAL().GetYetkiId(contract.getId()).getYetkiId() == 6) {
			musterilerMenu.hide();
			personellerMenu.hide();
		}

		JMenuItem musteriDuzenleItem = new JMenuItem("Müþteri Düzenle");
		musterilerMenu.add(musteriDuzenleItem);
		JMenuItem sehirDuzenleItem = new JMenuItem("Þehir Düzenle");
		musterilerMenu.add(sehirDuzenleItem);

		urunEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {

						new UrunEkleFE();

					}
				});

			}
		});
		kategoriEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new KategoriEkleFE();
			}
		});

		personelEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new PersonelEkleFE();

					}
				});

			}
		});

		yetkiEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new YetkiEkleFE();

					}
				});

			}
		});
		sifreBelirleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new SifreIslemleriFE();

					}
				});

			}
		});

		musteriEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new MusteriEkleFE();

					}
				});

			}
		});

		sehirEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new sehirEkleFE();

					}
				});

			}
		});
		KategoriDuzenleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new KategoriDuzenleFE();

					}
				});

			}
		});

		return bar;
	}
}

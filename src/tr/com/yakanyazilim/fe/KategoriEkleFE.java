package tr.com.yakanyazilim.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import tr.com.yakanyazilim.dal.KategoriDal;
import tr.com.yakanyazilim.interfaces.FeInterfaces;
import tr.com.yakanyazilim.types.KategoriContract;

public class KategoriEkleFE extends JDialog implements FeInterfaces {

	public KategoriEkleFE() {
		initPencere();

	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		panel.setBorder(BorderFactory.createTitledBorder("Kategori Ekle"));
		add(panel);
		setTitle("Kategori Ekle");
		pack();// boyutlar� otomatik versin
		setModalityType(DEFAULT_MODALITY_TYPE);// panel a��kken digerine ge�memesi icin
		setLocationRelativeTo(null);// pencere a��ld���nda merkezde dursun
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(3, 2));

		JLabel adiLabel = new JLabel("Kategori Ad�:", SwingConstants.RIGHT);
		panel.add(adiLabel);
		JTextField adiField = new JTextField(10);
		panel.add(adiField);

		JLabel kategoriLabel = new JLabel("Kategori Se�", SwingConstants.RIGHT);
		panel.add(kategoriLabel);
		JComboBox kategoriBox = new JComboBox(new KategoriDal().GetAllParentId().toArray());
		panel.add(kategoriBox);
		kategoriBox.insertItemAt("--Kategori Se�iniz", 0);
		kategoriBox.setSelectedIndex(0);
		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		kaydetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				KategoriContract contract = new KategoriContract();

				if (kategoriBox.getSelectedIndex() != 0) {
					KategoriContract castContract = (KategoriContract) kategoriBox.getSelectedItem();
					contract.setAdi(adiField.getText());
					contract.setParentId(castContract.getId());

					new KategoriDal().Insert(contract);
					JOptionPane.showMessageDialog(null,
							contract.getAdi() + " adl� Kategori  Ba�ar�l� Bir �ekilde Kay�t Edilmi�tir");
				} else {
					contract.setAdi(adiField.getText());
					contract.setParentId(kategoriBox.getSelectedIndex());

					new KategoriDal().Insert(contract);
					JOptionPane.showMessageDialog(null,
							contract.getAdi() + " adl� Kategori  Ba�ar�l� Bir �ekilde Kay�t Edilmi�tir");

				}

			}
		});

		JButton iptalButton = new JButton("�ptal");
		panel.add(iptalButton);

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

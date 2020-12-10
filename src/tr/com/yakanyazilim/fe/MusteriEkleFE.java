package tr.com.yakanyazilim.fe;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import tr.com.yakanyazilim.dal.MusteriDAL;
import tr.com.yakanyazilim.dal.SehirlerDAL;
import tr.com.yakanyazilim.interfaces.FeInterfaces;
import tr.com.yakanyazilim.types.MusteriContract;
import tr.com.yakanyazilim.types.SehirlerContract;

public class MusteriEkleFE extends JDialog implements FeInterfaces {

	public MusteriEkleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		panel.setBorder(BorderFactory.createTitledBorder("Müþteri Ekle"));
		add(panel);
		setTitle("Müþteri Ekle");
		pack();// boyutlarý otomatik versin
		setModalityType(DEFAULT_MODALITY_TYPE);// panel açýkken digerine geçmemesi icin
		setLocationRelativeTo(null);// pencere açýldýðýnda merkezde dursun
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel fieldPanel = new JPanel(new GridLayout(5, 2));
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
		JLabel adiSoyadiLabel = new JLabel("Adý Soyadý:", SwingConstants.RIGHT);
		fieldPanel.add(adiSoyadiLabel);
		JTextField adiSoyadiField = new JTextField(10);
		fieldPanel.add(adiSoyadiField);

		JLabel telefonLabel = new JLabel("Telefon:", SwingConstants.RIGHT);
		fieldPanel.add(telefonLabel);
		JTextField telefonField = new JTextField(10);
		fieldPanel.add(telefonField);

		JLabel sehirLabel = new JLabel("Þehir Seç:", SwingConstants.RIGHT);
		fieldPanel.add(sehirLabel);
		JComboBox sehirlerBox = new JComboBox(new SehirlerDAL().GetAll().toArray());
		fieldPanel.add(sehirlerBox);

		JLabel adresLabel = new JLabel("Adres:", SwingConstants.RIGHT);
		fieldPanel.add(adresLabel);
		JTextArea adresArea = new JTextArea(7, 1);
		JScrollPane pane = new JScrollPane(adresArea);
		pane.setBorder(BorderFactory.createTitledBorder("Adres Bilgisi"));

		JButton kaydetButton = new JButton("Kaydet");
		buttonPanel.add(kaydetButton);
		JButton iptalButton = new JButton("Ýptal");
		buttonPanel.add(iptalButton);

		panel.add(fieldPanel, BorderLayout.NORTH);
		panel.add(pane, BorderLayout.CENTER);
		panel.add(buttonPanel, BorderLayout.SOUTH);

		kaydetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				MusteriContract contract = new MusteriContract();
				// personelBox tan gelen veri cast etmemiz gerekiyor.
				SehirlerContract sContract = (SehirlerContract) sehirlerBox.getSelectedItem();

				contract.setAdiSoyadi(adiSoyadiField.getText());
				contract.setTelefon(telefonField.getText());
				contract.setSehirId(sContract.getSehirId());
				contract.setAdres(adresArea.getText());

				JOptionPane.showMessageDialog(null,
						contract.getAdiSoyadi() + " adlý kisi kayýt edilmiþtir " + contract.getSehirId());
				new MusteriDAL().Insert(contract);

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

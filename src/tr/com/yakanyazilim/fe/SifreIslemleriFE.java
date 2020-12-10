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
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import tr.com.yakanyazilim.dal.AccounstDAL;
import tr.com.yakanyazilim.dal.PersonelDAL;
import tr.com.yakanyazilim.dal.YetkilerDAL;
import tr.com.yakanyazilim.interfaces.FeInterfaces;
import tr.com.yakanyazilim.types.AccountsContract;
import tr.com.yakanyazilim.types.PersonelContract;
import tr.com.yakanyazilim.types.YetkilerContract;

public class SifreIslemleriFE extends JDialog implements FeInterfaces {

	public SifreIslemleriFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		panel.setBorder(BorderFactory.createTitledBorder("�ifre Belirleme ��lemleri"));
		add(panel);
		setTitle("�ifre Belirleme ��lemleri");
		pack();// boyutlar� otomatik versin
		setModalityType(DEFAULT_MODALITY_TYPE);// panel a��kken digerine ge�memesi icin
		setLocationRelativeTo(null);// pencere a��ld���nda merkezde dursun
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(5, 2));

		JLabel personelLabel = new JLabel("Personel Se�:", SwingConstants.RIGHT);
		panel.add(personelLabel);
		JComboBox personelBox = new JComboBox(new PersonelDAL().GetAll().toArray());
		panel.add(personelBox);

		JLabel yetkiLabel = new JLabel("Yetki Se�:", SwingConstants.RIGHT);
		panel.add(yetkiLabel);
		JComboBox yetkiBox = new JComboBox(new YetkilerDAL().GetAll().toArray());
		panel.add(yetkiBox);

		JLabel passwordlLabel = new JLabel("�ifre Belirle:", SwingConstants.RIGHT);
		panel.add(passwordlLabel);
		JPasswordField passField = new JPasswordField();
		panel.add(passField);

		JLabel passTekrarlLabel = new JLabel("�ifre Tekrar:", SwingConstants.RIGHT);
		panel.add(passTekrarlLabel);
		JPasswordField passTekrarField = new JPasswordField();
		panel.add(passTekrarField);

		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		JButton iptalButton = new JButton("�ptal");
		panel.add(iptalButton);
		kaydetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				AccountsContract contract = new AccountsContract();
				// personelBox tan gelen veri cast etmemiz gerekiyor.
				PersonelContract pContract = (PersonelContract) personelBox.getSelectedItem();
				// yetkiBox tan gelen veri cast etmemiz gerekiyor.
				YetkilerContract yContract = (YetkilerContract) yetkiBox.getSelectedItem();
				if (passField.getText().equals(passTekrarField.getText())) {
					contract.setPersonelId(pContract.getId());
					contract.setYetkiId(yContract.getId());
					contract.setSifre(passField.getText());
					JOptionPane.showMessageDialog(null, pContract.getAdiSoyadi() + "adl� ki�iye " + yContract.getAdi()
							+ " adl� yetki Ba�ar�l� bir�ekilde eklenmi�tir.");
					new AccounstDAL().Insert(contract);
				} else {
					JOptionPane.showMessageDialog(null, "�ifreler uyu�muyor tekrar kontrol ediniz");
				}

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

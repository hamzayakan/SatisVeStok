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
import tr.com.yakanyazilim.interfaces.FeInterfaces;
import tr.com.yakanyazilim.types.PersonelContract;

public class LoginFE extends JDialog implements FeInterfaces {

	public static JComboBox emailBox;// Diger pencerelere emailBox tan gelen veri alabilmem lazim

	public LoginFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		panel.setBorder(BorderFactory.createTitledBorder("Lütfen Sisteme Giriþ Yapmak Ýçin Bilgilerinizi Giriniz"));
		add(panel);
		setTitle("Lütfen Giriþ Yapýnýz");
		pack();// boyutlarý otomatik versin
		setLocationRelativeTo(null);// pencere açýldýðýnda merkezde dursun
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(3, 2));

		JLabel emailLabel = new JLabel("Email:", SwingConstants.RIGHT);
		panel.add(emailLabel);
		emailBox = new JComboBox(new PersonelDAL().GetAll().toArray());
		panel.add(emailBox);
		JLabel passwordLabel = new JLabel("Þifreniz:", SwingConstants.RIGHT);
		panel.add(passwordLabel);
		JPasswordField passwordField = new JPasswordField();
		panel.add(passwordField);

		JButton loginButton = new JButton("Giriþ Yap");
		panel.add(loginButton);
		JButton iptalButton = new JButton("Ýptal");
		panel.add(iptalButton);

		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				PersonelContract contract = (PersonelContract) emailBox.getSelectedItem();

				if (new AccounstDAL().GetPersonelVeSifre(contract.getId(), passwordField.getText()).getId() != 0) {
					new AnaPencereFE();
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Giriþ Baþarýsýz");
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

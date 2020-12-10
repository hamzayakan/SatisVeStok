package tr.com.yakanyazilim.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import tr.com.yakanyazilim.dal.YetkilerDAL;
import tr.com.yakanyazilim.interfaces.FeInterfaces;
import tr.com.yakanyazilim.types.YetkilerContract;

public class YetkiEkleFE extends JDialog implements FeInterfaces {

	public YetkiEkleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		panel.setBorder(BorderFactory.createTitledBorder("Yetki Ekle"));
		add(panel);
		setTitle("Yetki Ekle");
		pack();// boyutlar� otomatik versin
		setModalityType(DEFAULT_MODALITY_TYPE);// panel a��kken digerine ge�memesi icin
		setLocationRelativeTo(null);// pencere a��ld���nda merkezde dursun
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 2));

		JLabel adiLabel = new JLabel("Yetki Ad�:", SwingConstants.RIGHT);
		panel.add(adiLabel);
		JTextField adiField = new JTextField(10);
		panel.add(adiField);

		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		kaydetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				YetkilerContract contract = new YetkilerContract();
				contract.setAdi(adiField.getText());

				new YetkilerDAL().Insert(contract);
				JOptionPane.showMessageDialog(null, contract.getAdi() + " adl� yetki ba�ar�l� bir �ekilde eklenmi�tir");

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

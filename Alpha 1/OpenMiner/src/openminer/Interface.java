package openminer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.powerbot.script.PollingScript;
import org.powerbot.script.ClientContext;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

public class Interface{
	
	private JFrame frmOpenminer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frmOpenminer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOpenminer = new JFrame();
		frmOpenminer.setResizable(false);
		frmOpenminer.setTitle("FreeMiner");
		frmOpenminer.setBounds(100, 100, 243, 297);
		frmOpenminer.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmOpenminer.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 215, 249);
		frmOpenminer.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Main Settings", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblChooseYourLocation = new JLabel("Choose Your Location:");
		lblChooseYourLocation.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblChooseYourLocation.setBounds(10, 11, 190, 14);
		panel.add(lblChooseYourLocation);
		
		JLabel lblChooseYourMode = new JLabel("Choose Your Mode:");
		lblChooseYourMode.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblChooseYourMode.setBounds(10, 67, 190, 14);
		panel.add(lblChooseYourMode);
		
		JLabel lblInputRockIds = new JLabel("Choose What To Mine:");
		lblInputRockIds.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInputRockIds.setBounds(10, 123, 190, 14);
		panel.add(lblInputRockIds);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Gunnarsgrun", "Varrock East"}));
		comboBox.setBounds(10, 36, 190, 20);
		panel.add(comboBox);
		
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Banking", "Powermining"}));
		comboBox_1.setBounds(10, 92, 190, 20);
		panel.add(comboBox_1);
		
		final JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Clay rocks", "Rune Essence", "Copper ore rocks", "Tin ore rocks", "Blurite ore rocks", "Iron ore rocks", "Silver ore rocks", "Coal rocks", "Gold ore rocks", "Mithril ore rocks", "Adamantite ore rocks", "Runite ore rocks"}));
		comboBox_2.setBounds(10, 150, 190, 20);
		panel.add(comboBox_2);
		
		JButton btnNewButton = new JButton("Start FreeMiner!");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				OpenMiner.INTERFACE_SELECTION_ROX = comboBox_2.getModel().getSelectedItem().toString();
				OpenMiner.INTERFACE_SELECTION_LOCATION = comboBox.getModel().getSelectedItem().toString();
				OpenMiner.INTERFACE_SELECTION_MODE = comboBox_1.getModel().getSelectedItem().toString();
				frmOpenminer.hide();
				
			}
		});
		btnNewButton.setBounds(10, 187, 190, 23);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Supported", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JTextPane txtpnSaddd = new JTextPane();
		panel_1.add(txtpnSaddd);
		txtpnSaddd.setEditable(false);
		txtpnSaddd.setText("Locations (Ores) [B/P] :\r\nGunnarsgrun (Tin) [B+P]\r\nGunnarsgrun (Clay) [B+P]\r\nGunnarsgrun (Coal) [B+P]");
	}
}

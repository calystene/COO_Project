package presentation.client;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PanelCreerClient extends JPanel implements ActionListener {
	JButton btnValider = new JButton("Valider");
	JButton btnEffacer = new JButton("Effecer");
	
	JLabel lblNom = new JLabel("Nom");
	JLabel lblPrenom = new JLabel("Prenom");
	JLabel lblNumero = new JLabel("Numero");
	
	JTextField jtfNom = new JTextField();
	JTextField jtfPrenom = new JTextField();
	JTextField jtfNumero = new JTextField();
	
	
	public PanelCreerClient(JFrame pere) {
		setPreferredSize(new Dimension(200,300));
		setLayout(new GridLayout(2, 4));
		
		add(lblNom);
		add(jtfNom);
		add(lblPrenom);
		add(jtfPrenom);
	}

	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnValider) {
			//CreerClient();
		}
		
		if(e.getSource() == btnEffacer) {
			jtfNom.setText("");
			jtfPrenom.setText("");
			jtfNumero.setText("");
		}
	}
}

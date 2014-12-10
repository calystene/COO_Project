package presentation.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import exception.ExceptionClientExistant;
import metier.CreerClient;

@SuppressWarnings("serial")
public class PanelCreerClient extends JPanel implements ActionListener {
	JFrame parent;
	
	JButton btnValider = new JButton("Valider");
	JButton btnEffacer = new JButton("Effacer");
	
	JLabel lblNom = new JLabel("Nom");
	JLabel lblPrenom = new JLabel("Prenom");
	JLabel lblNumero = new JLabel("Numero");
	
	JTextField jtfNom = new JTextField();
	JTextField jtfPrenom = new JTextField();
	JTextField jtfNumero = new JTextField(9);
	
	JPanel panel = new JPanel(new GridLayout(4,2));
	
	public PanelCreerClient(JFrame pere) {
		parent = pere;
		
		setPreferredSize(new Dimension(200,150));
		setLayout(new BorderLayout());
		
		btnEffacer.addActionListener(this);
		btnValider.addActionListener(this);
		
		panel.setPreferredSize(new Dimension(200,150));
		
		panel.add(lblPrenom);
		panel.add(jtfPrenom);
		panel.add(lblNom);
		panel.add(jtfNom);
		panel.add(lblNumero);
		panel.add(jtfNumero);
		panel.add(btnEffacer);
		panel.add(btnValider);
		
		add(panel, BorderLayout.CENTER);
	}

	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnValider) {
			if (jtfPrenom.getText().length() != 0 && jtfNom.getText().length() != 0 && jtfNumero.getText().length() != 0) {
				if (jtfNumero.getText().length() == 9) {
					String prenom = jtfPrenom.getText();
					String nom = jtfNom.getText();
					int numero = Integer.parseInt(jtfNumero.getText());
					
					
					try {
						CreerClient ccli = new CreerClient();
						ccli.nouveauClient(prenom, nom, numero);
					} catch (ExceptionClientExistant e1) {
						JOptionPane.showMessageDialog(parent,
							   e1.getMessage(),
							    "Erreur création",
							    JOptionPane.WARNING_MESSAGE);
						
					} catch (SQLException e2) {
						JOptionPane.showMessageDialog(parent,
							    e2.getMessage(),
							    "Erreur création",
							    JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(parent,
						    "Le numéro doit faire 9 caractères");
				}
			} else {
				JOptionPane.showMessageDialog(parent,
					    "Veuillez remplir tous les champs");
			}
		}
		
		if(e.getSource() == btnEffacer) {
			jtfNom.setText("");
			jtfPrenom.setText("");
			jtfNumero.setText("");
		}
	}
}

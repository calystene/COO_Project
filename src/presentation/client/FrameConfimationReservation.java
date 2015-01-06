package presentation.client;

import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import data.Reservation;
import exception.ExceptionClientInexistant;

@SuppressWarnings("serial")
public class FrameConfimationReservation extends JFrame {
	
	JPanel panel;
	
	public FrameConfimationReservation(String title, Reservation r) throws SQLException, ExceptionClientInexistant {
		super(title);
		
		panel = new PanelConfirmationReservation(this,r);
		
		// configuration de la fenêtre
		setLocationRelativeTo(null);
		setSize(600, 500);
		getContentPane().setSize(600, 500);
		//setDefaultCloseOperation();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().add(panel);
		getContentPane().setSize(600, 500);
		setLayout(new BorderLayout());
		//setDefaultCloseOperation();
		
		getContentPane().add(panel, BorderLayout.CENTER);
		
		pack();
		setVisible(true);
	}
}

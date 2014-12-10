package presentation.client;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import data.Client;

public class FrameDetailsReservationClient extends JFrame {
			/** procedure de lancement du programme 
			 * @throws Exception **/	
			
			JPanel panel;
			
			public FrameDetailsReservationClient(String title, Client c) {
				super(title);
				
				panel = new PanelDetailsReservationClient(this,c);
				
				// configuration de la fenêtre
				setLocationRelativeTo(null);
				setSize(300, 500);

				getContentPane().setSize(300,500);
				//setDefaultCloseOperation();
				
				getContentPane().add(panel);

				getContentPane().setSize(300,400);
				setLayout(new BorderLayout());
				//setDefaultCloseOperation();
				
				getContentPane().add(panel, BorderLayout.CENTER);
				
				pack();
				setVisible(true);
			}//fin main
		}

}

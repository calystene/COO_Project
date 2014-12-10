package presentation;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import factory.FactorySQL;

public class MainProgramme {
	
		/** procedure de lancement du programme 
		 * @throws Exception **/	
		public static void main(String [] args) throws Exception {
			//creation de la fenetre principale
			JFrame frame = new JFrame("Gestion Réservation Salle");
			frame.getContentPane().add(new MenuPrincipal(frame), BorderLayout.SOUTH);
			
			// configuration de la fenêtre
			frame.setLocationRelativeTo(null);
			frame.setSize(300, 500);
			frame.getContentPane().setSize(300,500);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			// mise en place d'un observateur de cette fenetre - pour le exit 
			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					FactorySQL.getInstance().shutdown();
					System.exit(0);
				}
			});
			frame.pack();
			frame.setVisible(true);
		}//fin main
}

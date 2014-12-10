package presentation.client;

<<<<<<< HEAD
=======
import java.awt.BorderLayout;

>>>>>>> 163ff3b5031f2777eebff97955a4aaa607598467
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class FrameCreerClient extends JFrame {
	/** procedure de lancement du programme 
	 * @throws Exception **/	
	
	JPanel panel;
	
	public FrameCreerClient(String title) {
		super(title);
		
		panel = new PanelCreerClient(this);
		
		// configuration de la fenêtre
		setLocationRelativeTo(null);
		setSize(300, 500);
<<<<<<< HEAD
		getContentPane().setSize(300,500);
		//setDefaultCloseOperation();
		
		getContentPane().add(panel);
=======
		getContentPane().setSize(300,400);
		setLayout(new BorderLayout());
		//setDefaultCloseOperation();
		
		getContentPane().add(panel, BorderLayout.CENTER);
>>>>>>> 163ff3b5031f2777eebff97955a4aaa607598467
		
		pack();
		setVisible(true);
	}//fin main
}

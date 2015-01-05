package presentation.client;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ControleurTableResultat  implements ListSelectionListener{
	
    public void valueChanged(ListSelectionEvent listSelectionEvent){
        if (listSelectionEvent.getValueIsAdjusting())
            return;
        ListSelectionModel lsm = (ListSelectionModel)listSelectionEvent.getSource();
        if (lsm.isSelectionEmpty()) {
            System.out.println("No rows selected");
        }
        else{
            int selectedRow = lsm.getMinSelectionIndex();
            System.out.println("The row "+selectedRow+" is now selected");
 
        }
    }
}
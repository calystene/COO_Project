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
           
        }
        else{
            int selectedRow = lsm.getMinSelectionIndex();
           
 
        }
    }
}
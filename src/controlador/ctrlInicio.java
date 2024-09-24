package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import vista.frmDentistas;
import vista.frmInicio;

/**
 *
 * @author Estudiante
 */
public class ctrlInicio implements MouseListener {
    
    private frmInicio vista;
    
    public ctrlInicio(frmInicio vista) {
        this.vista = vista;
        
        vista.btnSistema.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == vista.btnSistema){
            frmDentistas.initfrmDentistas();
            vista.dispose();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}

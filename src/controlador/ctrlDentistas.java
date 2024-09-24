package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import modelo.tbDentista;
import vista.frmDentistas;
import vista.frmInicio;

/**
 *
 * @author Estudiante
 */
public class ctrlDentistas implements MouseListener, KeyListener {

    private tbDentista modelo;
    private frmDentistas vista;
    
    public ctrlDentistas(tbDentista modelo, frmDentistas vista){
        this.modelo = modelo;
        this.vista = vista;
        
        vista.btnAgregar.addMouseListener(this);
        vista.btnEliminar.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnLimpiar.addMouseListener(this);
        vista.txtBuscar.addKeyListener(this);
        vista.imgBack.addMouseListener(this);
        vista.jtDentistas.addMouseListener(this);
        modelo.Mostrar(vista.jtDentistas);
    }
    
    public void LimpiarCampos(){
        vista.txtBuscar.setText("");
        vista.txtCorreo.setText("");
        vista.txtEdad.setText("");
        vista.txtNombre.setText("");
        vista.txtPeso.setText("");
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource() == vista.imgBack){
            frmInicio.initfrmInicio();
            vista.dispose();
        }
        
        if(e.getSource() == vista.btnEliminar){
            if(vista.jtDentistas.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(vista, "Debes seleecionar un registro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int respuesta = JOptionPane.showConfirmDialog(vista, "¿Estás seguro de que deseas eliminar este registro?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                
                // Si el usuario selecciona "Sí/Yes"
            if (respuesta == JOptionPane.YES_OPTION) {
                    modelo.Eliminar(vista.jtDentistas);
                    modelo.Mostrar(vista.jtDentistas);
                    LimpiarCampos();
                }
            }
        }
        
        if(e.getSource() == vista.btnAgregar){
            
            if (vista.txtNombre.getText().isEmpty() ||
                vista.txtEdad.getText().isEmpty() || 
                vista.txtPeso.getText().isEmpty() || 
                vista.txtCorreo.getText().isEmpty()) {
            
                JOptionPane.showMessageDialog(vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            
                
                } else {
                    String edad = vista.txtEdad.getText();
                    String peso = vista.txtPeso.getText();
                    
                    if (edad.length() >= 3) {
                         JOptionPane.showMessageDialog(vista, "Ingrese una edad valida", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        modelo.setNombre_Dentista(vista.txtNombre.getText());
                        modelo.setEdad_Dentista(Integer.parseInt(vista.txtEdad.getText()));
                        modelo.setPeso_Dentista(Double.parseDouble(vista.txtPeso.getText()));
                        modelo.setCorreo_Dentista(vista.txtCorreo.getText());
            
                        modelo.Guardar();   
                        modelo.Mostrar(vista.jtDentistas);
                        LimpiarCampos();
                    }
                }
        }
        
        if(e.getSource() == vista.btnActualizar){
            modelo.setNombre_Dentista(vista.txtNombre.getText());
            modelo.setEdad_Dentista(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPeso_Dentista(Double.parseDouble(vista.txtPeso.getText()));
            modelo.setCorreo_Dentista(vista.txtCorreo.getText());
            
            modelo.Actualizar(vista.jtDentistas);
            modelo.Mostrar(vista.jtDentistas);
            LimpiarCampos();
        }
        
        if(e.getSource() == vista.jtDentistas){
            modelo.cargarDatosTabla(vista);
        }
        
        if(e.getSource() == vista.btnLimpiar){
            LimpiarCampos();
            modelo.Mostrar(vista.jtDentistas);
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource() == vista.txtBuscar){
            modelo.Buscar(vista.jtDentistas, vista.txtBuscar);
        }
    }
    
}

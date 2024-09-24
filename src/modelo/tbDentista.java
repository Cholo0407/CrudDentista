package modelo;

import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import vista.frmDentistas;

/**
 *
 * @author Estudiante
 */
public class tbDentista {

    public String getUUID_Dentista() {
        return UUID_Dentista;
    }

    public void setUUID_Dentista(String UUID_Dentista) {
        this.UUID_Dentista = UUID_Dentista;
    }

    public String getNombre_Dentista() {
        return Nombre_Dentista;
    }

    public void setNombre_Dentista(String Nombre_Dentista) {
        this.Nombre_Dentista = Nombre_Dentista;
    }

    public int getEdad_Dentista() {
        return Edad_Dentista;
    }

    public void setEdad_Dentista(int Edad_Dentista) {
        this.Edad_Dentista = Edad_Dentista;
    }

    public double getPeso_Dentista() {
        return Peso_Dentista;
    }

    public void setPeso_Dentista(double Peso_Dentista) {
        this.Peso_Dentista = Peso_Dentista;
    }

    public String getCorreo_Dentista() {
        return Correo_Dentista;
    }

    public void setCorreo_Dentista(String Correo_Dentista) {
        this.Correo_Dentista = Correo_Dentista;
    }
    
    private String UUID_Dentista;
    private String Nombre_Dentista;
    private int Edad_Dentista;
    private double Peso_Dentista;
    private String Correo_Dentista;
    
    public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
 
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID_Dentista", "Dentista", "Correo", "Edad", "Peso" });
 
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
 
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbDentista");
 
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("UUID_Dentista"),
                    rs.getString("Nombre_Dentista"),
                    rs.getString("Correo_Dentista"),
                    rs.getInt("Edad_Dentista"),
                    rs.getDouble("Peso_dentista"),});
            }
 
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);
            
            //Ocultar la columna UUID
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
            
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
    
    public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        //borramos 
        try {
            PreparedStatement deleteDentista = conexion.prepareStatement("delete from tbDentista where UUID_Dentista = ?");
            deleteDentista.setString(1, miId);
            deleteDentista.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
     
    public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addDentista = conexion.prepareStatement("INSERT INTO tbDentista(UUID_Dentista, Nombre_Dentista, Edad_Dentista, Peso_Dentista, Correo_Dentista) VALUES (?, ?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addDentista.setString(1, UUID.randomUUID().toString());
            addDentista.setString(2, getNombre_Dentista());
            addDentista.setInt(3, getEdad_Dentista());
            addDentista.setDouble(4, getPeso_Dentista());
            addDentista.setString(5, getCorreo_Dentista());
 
            //Ejecutar la consulta
            addDentista.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    
    public void cargarDatosTabla(frmDentistas vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtDentistas.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtDentistas.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.jtDentistas.getValueAt(filaSeleccionada, 1).toString();
            String correo = vista.jtDentistas.getValueAt(filaSeleccionada, 2).toString();
            String edad = vista.jtDentistas.getValueAt(filaSeleccionada, 3).toString();
            String peso = vista.jtDentistas.getValueAt(filaSeleccionada, 4).toString();

            // Establece los valores en los campos de texto
            vista.txtNombre.setText(NombreDeTB);
            vista.txtEdad.setText(edad);
            vista.txtCorreo.setText(correo);
            vista.txtPeso.setText(peso);
        }
    }
    
    public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateDentista = conexion.prepareStatement("update tbDentista set Nombre_Dentista = ?, Edad_Dentista = ?, Peso_Dentista = ?, Correo_Dentista = ? where UUID_Dentista = ?");
                updateDentista.setString(1, getNombre_Dentista());
                updateDentista.setInt(2, getEdad_Dentista());
                updateDentista.setDouble(3, getPeso_Dentista());
                updateDentista.setString(4, getCorreo_Dentista());
                updateDentista.setString(5, miUUId);
                updateDentista.executeUpdate();

            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
     
    public void Buscar(JTable tabla, JTextField txtBuscar) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID", "Dentista", "Correo", "Edad", "Peso"});
   
        try {
           
            PreparedStatement deleteEstudiante = conexion.prepareStatement("SELECT * FROM tbDentista WHERE Nombre_Dentista LIKE ? || '%'");
            deleteEstudiante.setString(1, txtBuscar.getText());
            ResultSet rs = deleteEstudiante.executeQuery();

             while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("UUID_Dentista"), 
                    rs.getString("Nombre_Dentista"),
                    rs.getString("Correo_Dentista"),
                    rs.getInt("Edad_Dentista"), 
                    rs.getDouble("Edad_Dentista")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);
            
            //Ocultar la columna UUID
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
            

        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo de buscar " + e);
        }
    }
}


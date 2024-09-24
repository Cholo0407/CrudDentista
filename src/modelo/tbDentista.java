package modelo;

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
    
    
}

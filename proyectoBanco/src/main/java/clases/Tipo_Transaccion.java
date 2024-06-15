package clases;

public class Tipo_Transaccion {
    private int tipoTransaccionId;
    private String nombre;
    private String descripcion;
    // Otros atributos y métodos según sea necesario

    public Tipo_Transaccion(int tipoTransaccionId, String nombre, String descripcion) {
        this.tipoTransaccionId = tipoTransaccionId;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getTipoTransaccionId() {
        return tipoTransaccionId;
    }

    public void setTipoTransaccionId(int tipoTransaccionId) {
        this.tipoTransaccionId = tipoTransaccionId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Tipo_Transaccion{" +
                "tipoTransaccionId=" + tipoTransaccionId +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
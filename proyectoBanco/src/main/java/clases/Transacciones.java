package clases;

import java.util.Date;

public class Transacciones {
    private int transaccionId;
    private int cuentaId;
    private int tipoTransaccionId;
    private double monto;
    private Date fechaTransaccion;
    private String descripcion;
    // Otros atributos y métodos según sea necesario

    public Transacciones(int transaccionId, int cuentaId, int tipoTransaccionId, double monto, Date fechaTransaccion, String descripcion) {
        this.transaccionId = transaccionId;
        this.cuentaId = cuentaId;
        this.tipoTransaccionId = tipoTransaccionId;
        this.monto = monto;
        this.fechaTransaccion = fechaTransaccion;
        this.descripcion = descripcion;
    }

    public int getTransaccionId() {
        return transaccionId;
    }

    public void setTransaccionId(int transaccionId) {
        this.transaccionId = transaccionId;
    }

    public int getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(int cuentaId) {
        this.cuentaId = cuentaId;
    }

    public int getTipoTransaccionId() {
        return tipoTransaccionId;
    }

    public void setTipoTransaccionId(int tipoTransaccionId) {
        this.tipoTransaccionId = tipoTransaccionId;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Transacciones{" +
                "transaccionId=" + transaccionId +
                ", cuentaId=" + cuentaId +
                ", tipoTransaccionId=" + tipoTransaccionId +
                ", monto=" + monto +
                ", fechaTransaccion=" + fechaTransaccion +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}

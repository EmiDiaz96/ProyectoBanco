package clases;

import java.util.Date;

public class Cuentas_Bancarias {
    private int cuentaId;
    private int clienteId;
    private String numeroCuenta;
    private String tipoCuenta;
    private double saldo;
    private Date fechaApertura;
    // Otros atributos y métodos según sea necesario

    public Cuentas_Bancarias(int cuentaId, int clienteId, String numeroCuenta, String tipoCuenta, double saldo, Date fechaApertura) {
        this.cuentaId = cuentaId;
        this.clienteId = clienteId;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
        this.fechaApertura = fechaApertura;
    }
    
    public Cuentas_Bancarias() {
    	
    }

    public int getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(int cuentaId) {
        this.cuentaId = cuentaId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    @Override
    public String toString() {
        return "Cuentas_Bancarias{" +
                "cuentaId=" + cuentaId +
                ", clienteId=" + clienteId +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                ", tipoCuenta='" + tipoCuenta + '\'' +
                ", saldo=" + saldo +
                ", fechaApertura=" + fechaApertura +
                '}';
    }
}
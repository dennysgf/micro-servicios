package com.microservicio.cuentamovimiento.application.dto;

import java.util.List;

public class EstadoCuentaDTO {

    private String numeroCuenta;
    private String tipo;
    private double saldoInicial;
    private boolean estado;
    private List<MovimientoDTO> movimientos;

    public EstadoCuentaDTO() {}

    public EstadoCuentaDTO(String numeroCuenta, String tipo, double saldoInicial, boolean estado, List<MovimientoDTO> movimientos) {
        this.numeroCuenta = numeroCuenta;
        this.tipo = tipo;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
        this.movimientos = movimientos;
    }


    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<MovimientoDTO> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoDTO> movimientos) {
        this.movimientos = movimientos;
    }
}

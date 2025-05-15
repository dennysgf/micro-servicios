package com.microservicio.cuentamovimiento.application.dto;

import java.time.LocalDateTime;

public class MovimientoDTO {
    private LocalDateTime fecha;
    private String tipo;
    private double valor;
    private double saldo;

    public MovimientoDTO(LocalDateTime fecha, String tipo, double valor, double saldo) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.valor = valor;
        this.saldo = saldo;
    }
    public MovimientoDTO() {}

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}

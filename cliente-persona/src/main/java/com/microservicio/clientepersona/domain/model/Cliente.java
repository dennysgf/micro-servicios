package com.microservicio.clientepersona.domain.model;

public class Cliente extends Persona {
    private String clienteId;
    private String contrasena;
    private boolean estado;

    public Cliente(String nombre, String identificacion, String direccion, String telefono,
                   String clienteId, String contrasena, boolean estado) {

        super(nombre, identificacion, direccion, telefono);
        this.clienteId = clienteId;
        this.contrasena = contrasena;
        this.estado = estado;
    }

    public Cliente() {
        super();
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}

package com.mycompany.poomaven;

public class Herenciacocinero {
    private String turnossemana;
    private String tiempotrabajado;

    public Herenciacocinero(String turnossemana, String tiempotrabajado) {
        this.turnossemana = turnossemana;
        this.tiempotrabajado = tiempotrabajado;        
    }

    // Getters
    public String getTurnossemana() {
        return turnossemana;
    }

    public String getTiempotrabajado() {
        return tiempotrabajado;
    }

    // Setters
    public void setTurnossemana(String turnossemana) {
        this.turnossemana = turnossemana;
    }

    public void setTiempotrabajado(String tiempotrabajado) {
        this.tiempotrabajado = tiempotrabajado;
    }

}









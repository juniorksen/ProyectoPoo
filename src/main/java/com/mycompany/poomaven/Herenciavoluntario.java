package com.mycompany.poomaven;

public class Herenciavoluntario {
    private String turnossemana;
    private String tiempotrabajado;

    public Herenciavoluntario (String turnossemana, String tiempotrabajado) {
        this.turnossemana = turnossemana;
        this.tiempotrabajado = tiempotrabajado;
        
    }

    public String getTurnossemana() {
        return turnossemana;
    }

    public String getTiempotrabajado() {
        return tiempotrabajado;
    }

    public void setTurnossemana(String turnossemana) {
        this.turnossemana = turnossemana;
    }

    public void setTiempotrabajado(String tiempotrabajado) {
        this.tiempotrabajado = tiempotrabajado;
    }

}



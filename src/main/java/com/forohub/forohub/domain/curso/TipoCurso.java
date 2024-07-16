package com.foroHub.foroHub.domain.curso;

public enum TipoCurso {

    COCINA("Cocina"),
    DEPORTES("Deportes"),
    ARTES("Artes");

    private String tipo;

    TipoCurso(String tipo){
        this.tipo = tipo;
    }

    public static TipoCurso fromString(String text){
        for (TipoCurso tipoCurso : TipoCurso.values()){
            if (tipoCurso.tipo.equalsIgnoreCase(text)){
                return tipoCurso;
            }
        }
        throw new IllegalArgumentException("No se encontro " + text);
    }

}

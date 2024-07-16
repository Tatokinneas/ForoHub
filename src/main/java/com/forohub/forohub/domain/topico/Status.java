package com.foroHub.foroHub.domain.topico;

public enum Status {

    POSTEADO("Posteado"),
    PENDIENTE("Pendiente");

    private String status;

    Status(String status){
        this.status = status;
    }

    public static Status fromString(String text){
        for(Status status : Status.values()){
            if(status.status.equalsIgnoreCase(text)){
                return status;
            }
        }
        throw new IllegalArgumentException("No hay tal estado" + text);
    }

}

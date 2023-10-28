package org.hbrs.se1.ws23.uebung2;

public class ContainerException extends Exception{
    private Integer id;

    public ContainerException() {
        super("Das Member-Objekt mit der ID ist bereits vorhanden!");
    }
    public ContainerException(Integer id) {
        super("Das Member-Objekt mit der ID " + id + " ist bereits vorhanden!");
        this.id = id;
    }
}

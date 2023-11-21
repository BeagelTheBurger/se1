package org.hbrs.se1.ws23.uebung4.prototype;

public class Client {
    public static void main (String[] args) throws Exception {
        // ToDo: Bewertung Exception-Handling (F3, F7)
        Container con = Container.getInstance();
        con.store();
    }

}

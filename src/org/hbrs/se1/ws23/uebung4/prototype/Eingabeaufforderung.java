package org.hbrs.se1.ws23.uebung4.prototype;

import java.util.Scanner;

public class Eingabeaufforderung {


private Container liste = null;


    public void startEingabe() throws ContainerException, Exception {
        String strInput = null;
        Container con = Container.getInstance();
        UserStory userStory = new UserStory();
        // Initialisierung des Eingabe-View
        // ToDo: Funktionsweise des Scanners erklären (F3)
        Scanner scanner = new Scanner( System.in );
        System.out.println("UserStory-Tool V1.0 by Julius P. (dedicated to all my friends)");
        while ( true ) {
            // Ausgabe eines Texts zur Begruessung


            System.out.print( "> "  );

            strInput = scanner.nextLine();

            // Extrahiert ein Array aus der Eingabe
            String[] strings = strInput.split(" ");

            // 	Falls 'help' eingegeben wurde, werden alle Befehle ausgedruckt
            if ( strings[0].equals("help") ) {
                System.out.println("Folgende Befehle stehen zur Verfuegung: help, dump....");
            }
            // Auswahl der bisher implementierten Befehle:
            if ( strings[0].equals("dump") ) {
                startAusgabe();
            }
            // Auswahl der bisher implementierten Befehle:
            if ( strings[0].equals("enter") ) {
                // Daten einlesen ...
                //this.addUserStory( new UserStory( data ) ) um das Objekt in die Liste einzufügen.



            }

            if (  strings[0].equals("store")  ) {
                // Daten speichern ...
                // Beispiel-Code
                con.addUserStorie(userStory);
                con.store();


            }
        } // Ende der Schleife
    }
    public void startAusgabe() {

        // Hier möchte Herr P. die Liste mit einem eigenen Sortieralgorithmus sortieren und dann
        // ausgeben. Allerdings weiss der Student hier nicht weiter

        // [Sortierung ausgelassen]
        // Todo: Implementierung Sortierung (F4)

        // Klassische Ausgabe ueber eine For-Each-Schleife

        // [Variante mit forEach-Methode / Streams (--> Kapitel 9, Lösung Übung Nr. 2)?
        //  Gerne auch mit Beachtung der neuen US1
        // (Filterung Projekt = "ein Wert (z.B. Coll@HBRS)" und Risiko >=5
        // Todo: Implementierung Filterung mit Lambda (F5)
        //liste.stream().filter(UserStory -> UserStory.getProjekt().equals("Coll@HBRS"))
         //       .forEach(
         //       .filter(UserStory -> UserStory.gedID() >= 5);
        //.filter()


    }
    public void addUserStory(UserStory us) throws ContainerException {
        if (liste.contains(us)) {
            ContainerException e = new ContainerException("Das UserStory-Objekt mit der ID " + us.getID() + " ist bereits vorhanden!");
            e.addID(us.getID());
            throw e;
        }
        //liste.add(us);
    }
}

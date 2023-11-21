package org.hbrs.se1.ws23.uebung3.persistence;

import java.util.List;
import java.io.*;
import java.util.ArrayList;
public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    // URL der Datei, in der die Objekte gespeichert werden
    private String LOCATION = "test.txt";

    private ObjectOutputStream oos = null;
    private FileOutputStream fos = null;

    private FileInputStream fis = null;
    private ObjectInputStream ois = null;


    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    @Override
    /**
     * Used only for openining a connection for storing objects
     * Exception occurs in some SDKs, when both are created (TODO!)
     * Workaround: open the Streams in the load method
     */
    public void openConnection() throws PersistenceException {
        try {
            fos = new FileOutputStream(LOCATION);
            fis = new FileInputStream(LOCATION);
        } catch (FileNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable
                    , "Error in opening the connection, File could not be found");
        }
        try {
            oos = new ObjectOutputStream(fos);
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable
                    , "Error in opening the connection, problems with the stream");
        }
    }

    @Override
    public void closeConnection() throws PersistenceException {
        try {
            // Closing the outputstreams for storing
            if (oos != null) oos.close();
            if (fos != null) fos.close();

            // Closing the inputstreams for loading
            if (ois != null) ois.close();
            if (fis != null) fis.close();
        } catch (IOException e) {
            // Lazy solution: catching the exception of any closing activity ;-)
            throw new PersistenceException(PersistenceException.ExceptionType.ClosingFailure, "error while closing connections");
        }
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<E> list) throws PersistenceException {
        // Write the objects to stream
        try {
            System.out.println("LOG: Es wurden " + list.size() + " Member-Objekte wurden erfolgreich gespeichert!");
            for (E tmp : list) {
                oos.writeObject(tmp);
            }
        } catch (IOException e) {
            // Koennte man ausgeben für interne Debugs: e.printStackTrace();
            // Chain of Responsibility: Hochtragen der Exception in Richtung Ausgabe (UI)
            // Uebergabe in ein lesbares Format fuer den Benutzer
            e.printStackTrace();
            throw new PersistenceException(PersistenceException.ExceptionType.SavingFailure, "Fehler beim Speichern der Datei!");
        }
    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples comes for free :-)
     */
    public List<E> load() throws PersistenceException {

        // Load the objects from stream
        List<E> list = new ArrayList<E>();

        try {
            // Create Streams here instead using "this.openConnection();"
            // Workaround!
            fis = new FileInputStream("store.txt");
            ois = new ObjectInputStream(fis);
            // Auslesen der Liste




            while (true) {
                Object tmp = ois.readObject();
                list.add((E) tmp);
            }

        } catch (EOFException | FileNotFoundException e) {
            // Sup-Optimal, da Exeception in Form eines unlesbaren Stake-Traces ausgegeben wird
            System.out.println("LOG: Es wurden " + list.size() + " User Stories erfolgreich reingeladen!");
            return list;
        } catch (ClassNotFoundException e) {
            // Chain of Responsbility erfuellt, durch Throw der Exceotion kann UI
            // benachrichtigt werden!
            throw new PersistenceException(PersistenceException.ExceptionType.LoadingFailure, "Fehler beim Laden der Datei! Class not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}





/**
    @Override

     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     *
    public List<E> load() throws PersistenceException  {
        // Some Coding hints ;-)

        // ObjectInputStream ois = null;
        // FileInputStream fis = null;
        // List<...> newListe =  null;
        //
        // Initiating the Stream (can also be moved to method openConnection()... ;-)
        // fis = new FileInputStream( " a location to a file" );

        // Tipp: Use a directory (ends with "/") to implement a negative test case ;-)
        // ois = new ObjectInputStream(fis);

        // Reading and extracting the list (try .. catch ommitted here)
        // Object obj = ois.readObject();

        // if (obj instanceof List<?>) {
        //       newListe = (List) obj;
        // return newListe

        // and finally close the streams (guess where this could be...?)
        return null;
    }
}
     */


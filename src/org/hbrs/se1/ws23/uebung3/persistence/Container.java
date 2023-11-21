package org.hbrs.se1.ws23.uebung3.persistence;

import java.io.*;
import java.util.ArrayList;

public class Container implements Serializable {
    private static Container container = null;
    private ArrayList<Member> list;  // list ist abstrakter und damit vielseitiger als ArrayList

    private Container() {
        this.list = new ArrayList<>();
    }

    public static synchronized Container getInstance() { // damit der Block abgearbeitet wird, bevor der nächste startet braucht man 'synchronized'
        if (container == null) {
            container = new Container();
        }
        return container;
    }

    public void addMember(Member member) throws ContainerException {
        if (member == null) {
            ContainerException e = new ContainerException();
            throw e;
        }

        if (list.contains(member)) {
            ContainerException e = new ContainerException();
            throw e;
        }
        list.add(member);
    }

    public String deleteMember(Integer id) {
        for (Member member : list) {
            if (member.getID().equals(id)) {
                list.remove(member);
                return "Member mit der ID " + id + " wurde gelöscht.";
            }
        }
        return "Member mit der ID " + id + " wurde nicht gefunden.";
    }

    public int size() {
        return list.size();
    }

    public void store() throws PersistenceException {
        try {
            FileOutputStream fos = new FileOutputStream("store.txt");
            ObjectOutputStream os = new ObjectOutputStream(fos);
            for (Member member : list) {
                os.writeObject(member);
            }
            os.close();
            fos.close();
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "IO Exception");
        } finally {
            System.out.println("Daten wurden gespeichert");
        }
    }

    public void load() throws PersistenceException {
        try {
            FileInputStream fis = new FileInputStream("store.txt");
            ObjectInputStream is = new ObjectInputStream(fis);
            while (true) {
                try {
                    Member member = (Member) is.readObject();
                    list.add(member);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Fehler beim Laden der Daten");
        } finally {
            System.out.println("Daten wurden geladen");
        }
    }
}

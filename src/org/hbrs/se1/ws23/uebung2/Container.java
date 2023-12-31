package org.hbrs.se1.ws23.uebung2;

import org.hbrs.se1.ws23.solutions.uebung2.ContainerException;
import org.hbrs.se1.ws23.uebung3.persistence.MemberView;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Container {

    private static Container container = null;
    private ArrayList<Member> list;

    private Container() {

        this.list = new ArrayList<Member>();

    }

    public static Container getInstance() {
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
            if (member.getID() == id) {
                list.remove(member);
                return "Member mit der ID " + id + " wurde gelöscht.";
            }
        }
        return "Member mit der ID " + id + " wurde nicht gefunden.";
    }

    public void dump() {
        for (Member member : list) {
            System.out.println(member.toString());
        }
    }
    public int size() {
        return list.size();
    }

    public void store() throws PersistenceException {

        try {
            FileOutputStream fos = new FileOutputStream("store.txt");
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(list);
            os.close();
            fos.close();
        }
        catch (FileNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "File not found");
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "IO Exception");
        }
        finally {
            dump();
        }

        

    }

}

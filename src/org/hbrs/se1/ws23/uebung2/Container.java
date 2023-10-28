package org.hbrs.se1.ws23.uebung2;

import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;

import java.util.ArrayList;

public class Container {

    private static Container container = null;
    private ArrayList<Member> list = new ArrayList<>();        ;

    private Container() {

        container = new Container();

    }

    public static Container getInstance() {
        if (container == null) {
            container = new Container();
        }
        return container;
    }



    public void addMember(Member member) throws ContainerException{

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

        return;

    }

}

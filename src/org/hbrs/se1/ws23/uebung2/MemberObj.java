package org.hbrs.se1.ws23.uebung2;

public class MemberObj implements Member {
    private Integer id;

    public MemberObj(Integer id) {
        this.id = id;
    }
    public int getID() {
        return this.id;
    }
    @Override
    public String toString() {
        return "Member ID: " + this.id + "\n";
    }
}

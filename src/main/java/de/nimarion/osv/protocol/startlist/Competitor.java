package de.nimarion.osv.protocol.startlist;

public class Competitor {

    private final String firstname;
    private final String lastname;
    private final int lane;
    private final int bib;
    private final String nation;

    public Competitor(String firstname, String lastname, int lane, int bib, String nation) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.lane = lane;
        this.bib = bib;
        this.nation = nation;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getLane() {
        return lane;
    }

    public int getBib() {
        return bib;
    }

    public String getNation() {
        return nation;
    }

}

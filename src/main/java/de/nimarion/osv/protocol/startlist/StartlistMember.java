package de.nimarion.osv.protocol.startlist;

public class StartlistMember {

    private final String firstname;
    private final String lastname;
    private final int lane;
    private final int bib;
    private final String nation;

    public StartlistMember(String firstname, String lastname, int lane, int bib, String nation) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.lane = lane;
        this.bib = bib;
        this.nation = nation;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @return the lane
     */
    public int getLane() {
        return lane;
    }

    /**
     * @return the bib
     */
    public int getBib() {
        return bib;
    }

    /**
     * @return the nation
     */
    public String getNation() {
        return nation;
    }

    

    
}

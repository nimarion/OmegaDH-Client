package de.nimarion.osv.protocol.omega;

public class SupplementaryInfoResult {

    private final String bib;
    private final String lastname;
    private final String firstname;
    private final String nation;
    private final String time;
    private final String rank;
    private final String lane;

    /**
     * @param bib
     * @param lastname
     * @param firstname
     * @param nation
     * @param time
     * @param rank
     * @param lane
     */
    public SupplementaryInfoResult(String time, String rank,
            String lane, String bib, String lastname, String firstname, String nation) {
        this.bib = bib;
        this.lastname = lastname;
        this.firstname = firstname;
        this.nation = nation;
        this.time = time;
        this.rank = rank;
        this.lane = lane;
    }

    /**
     * @return the bib
     */
    public String getBib() {
        return bib;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @return the nation
     */
    public String getNation() {
        return nation;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @return the rank
     */
    public String getRank() {
        return rank;
    }

    /**
     * @return the lane
     */
    public String getLane() {
        return lane;
    }

}
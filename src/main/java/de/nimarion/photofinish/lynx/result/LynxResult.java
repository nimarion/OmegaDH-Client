package de.nimarion.photofinish.lynx.result;

public class LynxResult {

    private final int place;
    private final int bib;
    private final int lane;
    private final String firstname;
    private final String lastname;
    private final String affilation;
    private final String time;
    private final String license;
    private final String reactionTime;

    public LynxResult(int place, int bib, int lane, String firstname, String lastname, String affilation, String time,
            String license, String reactionTime) {
        this.place = place;
        this.bib = bib;
        this.lane = lane;
        this.firstname = firstname;
        this.lastname = lastname;
        this.affilation = affilation;
        this.time = time;
        this.license = license;
        this.reactionTime = reactionTime;
    }

    public int getPlace() {
        return place;
    }

    public int getBib() {
        return bib;
    }

    public int getLane() {
        return lane;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAffilation() {
        return affilation;
    }

    public String getTime() {
        return time;
    }

    public String getLicense() {
        return license;
    }

    public String getReactionTime() {
        return reactionTime;
    }

}

package de.nimarion.photofinish.taf;

public class BasicAthlete {

    private final Integer bib;
    private final String firstName;
    private final String lastName;
    private final String country;
    
    public BasicAthlete(Integer bib, String firstName, String lastName, String country) {
        this.bib = bib;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
    } 

    public Integer getBib() {
        return bib;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCountry() {
        return country;
    }
    
}

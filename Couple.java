package org.example.wedding;

public class Couple {
    private Person Bride;
    private Person Groom;

    // constructor that takes in the bride and groom
    public Couple(Person Bride, Person Groom){
        this.Bride = Bride;
        this.Groom = Groom;
    }
    // getter method for bride

    public Person getBride() {
        return Bride;
    }
    // getter method for groom
    public Person getGroom() {
        return Groom;
    }
}
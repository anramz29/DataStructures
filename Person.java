package org.example.wedding;

public class Person{
    private String firstName;
    private String lastName;

    // person constructor
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // get first name method
    public String getFirstName() {
        return firstName;
    }

    // get last name method
    public String getLastName() {
        return lastName;
    }
}
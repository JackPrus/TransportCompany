package by.prus.finalproject.domain;

import java.util.Objects;

public abstract class Person extends Entity {

    private String name;

    public Person(){}
    public Person( String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }



}

package by.prus.finalproject.bean;

/**
 * Class means nothing. I don't actualy know why did I do this. Sorry
 * @autor Dzmitry Prus
 * @version 1.0
 */
public abstract class Person extends Entity {

    private String name;

    public Person(){}
    public Person( String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }



}

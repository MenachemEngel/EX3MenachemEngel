package com.menachem.ex3.repo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * This class define database table of github users.
 */
@Entity
public class GithubUser {

    /**
     * Id field make unique id to record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Unique name field.
     */
    @Column(unique = true)
    private String name;

    /**
     * Link field for link to github user.
     */
    private String link;

    /**
     * Counter field for counting the num of search
     */
    private int counter = 1;

    /**
     * GithubUser constructor
     * Init the link
     */
    public GithubUser() {
        this.link = "https://github.com/";
    }

    /**
     * Get the id field
     * @return id field
     */
    public long getId() {
        return id;
    }

    /**
     * Set the id field
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the name field
     * @return name field
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name field
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the link field
     * @return link field
     */
    public String getLink() {
        return link;
    }

    /**
     * Set the link field
     * @param link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Get the counter field
     * @return counter field
     */
    public int getCounter() {
        return counter;
    }

    /**
     * Set the counter field
     * @param counter
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * increase counter in one
     */
    public void increase(){
        this.counter++;
    }

    /**
     * Overide toString function
     * @return diplay class in string form
     */
    @Override
    public String toString() {
        return "GithubUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", counter=" + counter +
                '}';
    }
}

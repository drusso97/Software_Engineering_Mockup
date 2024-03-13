package model;

/**
 * Class defines part objects.
 * */
public abstract class Customer {

    private int id;
    private String name;
    private String phoneNumber;
    private String emailAddress;
    protected Customer(int id, String name, String phoneNumber, String emailAddress) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    /**
     * @return the part's id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the part's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the part's name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the part's name
     */
    public void setName(String name) {
        this.name = name;
    }
}



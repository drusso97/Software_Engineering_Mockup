package model;

/**
 * Class defines part objects.
 * */
public class Customer {

    private int id;
    private String name;

    private String phoneNumber;
    private String emailAddress;
    public Customer(int id, String name, String phoneNumber, String emailAddress) {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}



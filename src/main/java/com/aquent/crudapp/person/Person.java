package com.aquent.crudapp.person;
import com.aquent.crudapp.client.Client;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.*;

/**
 * The person entity corresponding to the "person" table in the database.
 */
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Integer personId;

    @NotNull
    @Size(min = 1, max = 100, message = "First name is required with maximum length of 100")
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Size(min = 1, max = 100, message = "Last name is required with maximum length of 100")
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Size(min = 1, max = 100, message = "Email address is required with maximum length of 100")
    @Column(name = "email_address")
    private String emailAddress;

    @NotNull
    @Size(min = 1, max = 100, message = "Street address is required with maximum length of 100")
    @Column(name = "street_address")
    private String streetAddress;

    @NotNull
    @Size(min = 1, max = 100, message = "City is required with maximum length of 100")
    @Column(name = "city")
    private String city;

    @NotNull
    @Size(min = 2, max = 2, message = "State is required with length 2")
    @Column(name = "state")
    private String state;

    @NotNull
    @Size(min = 5, max = 5, message = "Zip code is required with length 5")
    @Column(name = "zip_code")
    private String zipCode;

    // adding many --> one relationship
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    // getter/setter for client
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}

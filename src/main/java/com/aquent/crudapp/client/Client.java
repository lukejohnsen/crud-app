package com.aquent.crudapp.client;
import com.aquent.crudapp.person.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clientId;

    @NotNull
    @Size(min = 1, max = 100, message = "Company name is required with a maximum length of 100")
    @Column(name = "company_name")
    private String companyName;

    @NotNull
    @Size(min = 1, max = 100, message = "Company website URI is required has a maximum length of 100")
    @Column(name = "website_uri")
    private String websiteUri;

    @NotNull
    @Size(min = 1, max = 20, message = "Company phone number is require has a maximum length of 20")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @Size(min = 1, max = 100, message = "Company address is required and has a maximum length of 100")
    @Column(name = "street_address")
    private String streetAddress;

    @NotNull
    @Size(min = 1, max = 100, message = "Company city is required and has a maximum length of 100")
    @Column(name = "city")
    private String city;

    @NotNull
    @Size(min = 2, max = 2, message = "Company state abbreviation must be two characters")
    @Column(name = "state")
    private String state;

    @NotNull
    @Size(min = 5, max = 5, message = "Zip code is required with length 5")
    @Column(name = "zip_code")
    private String zipCode;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Person> persons;

    // getters/setters

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWebsiteUri() {
        return websiteUri;
    }

    public void setWebsiteUri(String websiteUri) {
        this.websiteUri = websiteUri;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

}

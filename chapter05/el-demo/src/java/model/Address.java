package model;

public class Address {
    private String streetName;
    private String city;
    private String zipCode;
    
    public Address(String streetName, String city, String zipCode) {
        this.streetName = streetName;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    @Override
    public String toString() {
        return "Address in " + zipCode;
    }
}
package com.gbook.profiles.contact.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-10
 * Time: 11:23
 */
public class AddressContact implements ContactData {
    private String street;
    private String city;
    private String region;
    private String postcode;
    private String country;

    public AddressContact(@JsonProperty("street") String aStreet,
                          @JsonProperty("city") String aCity,
                          @JsonProperty("region") String aRegion,
                          @JsonProperty("postcode") String aPostcode,
                          @JsonProperty("country") String aCountry) {
        street = aStreet;
        city = aCity;
        region = aRegion;
        postcode = aPostcode;
        country = aCountry;
    }

    public AddressContact(String addressValue) {
        String[] addressParts = addressValue.split(":");
        Preconditions.checkArgument(addressParts.length == 5);

        street = addressParts[0];
        city = addressParts[1];
        region = addressParts[2];
        postcode = addressParts[3];
        country = addressParts[4];
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getRegion() {
        return region;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String flatten() {
        return String.join(":", street, city, region, postcode, country);
    }
}

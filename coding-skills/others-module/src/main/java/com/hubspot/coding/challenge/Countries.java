
package com.hubspot.coding.challenge;

import java.io.Serializable;
import java.util.List;

public class Countries implements Serializable
{

    private List<Country> countries = null;
    private final static long serialVersionUID = -8543723997985620148L;

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

}

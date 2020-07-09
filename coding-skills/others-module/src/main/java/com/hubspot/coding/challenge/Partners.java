
package com.hubspot.coding.challenge;

import java.io.Serializable;
import java.util.List;

public class Partners implements Serializable
{

    private List<Partner> partners = null;
    private final static long serialVersionUID = 4563493100969716422L;

    public List<Partner> getPartners() {
        return partners;
    }

    public void setPartners(List<Partner> partners) {
        this.partners = partners;
    }

}

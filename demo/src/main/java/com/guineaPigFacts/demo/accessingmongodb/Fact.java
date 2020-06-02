package com.guineaPigFacts.demo.accessingmongodb;
import org.springframework.data.annotation.Id;

import java.util.Date;

/***
 * MongoDB model for 'Fact'
 *  { "factId": 1,
 * "factValue": "Guinea Pig babies are called pups.",
 * "dateCreated": "06/02/2020",
 * "approved": false }
 */
public class Fact {
    @Id
    private String factId;

    public String factValue;
    private boolean approved;
    public Date dateCreated;

    public Fact() {}

    public Fact(String factValue, boolean approved, Date dateCreated) {
        this.factValue = factValue;
        this.approved = approved;
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[factValue=%s, dateCreated='%s']",
                factValue, dateCreated);
    }
}

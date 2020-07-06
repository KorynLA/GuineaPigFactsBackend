package com.factsdemo.guineaPigFacts.errorHandling;

import java.util.HashMap;

public class ErrorMessages {
    private HashMap<String, String> pattern;

    public ErrorMessages() {
        pattern = new HashMap<String, String>();
        pattern.put("id", "Id provided is not viable. Id field need 12 bytes");
        pattern.put("userName", "Username provided does not meet standards. Username should have between 4-20 characters and only allows letters, numbers, and underscores.");
        pattern.put("dateCreated", "Date provided is incorrect. Needs to be in MM/dd/yyyy format.");
        pattern.put("password", "Password provided does not meet standards. Needs at least 1 number and 8 characters.");
        pattern.put("factValue", "Fact provided needs at least 8 characters and a space.");
        pattern.put("contactInfo.email", "Email provided is not in correct format.");
        pattern.put("contactInfo.dailyUpdate", "Do you want to be updated? Daily update is boolean (true / false).");
    }

    public String getMessage(String message) {
        return pattern.get(message);
    }
}

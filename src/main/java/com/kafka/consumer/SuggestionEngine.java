package com.kafka.consumer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SuggestionEngine {

    public void processSuggestion(String userId, String product){
        String[] suggestions = product.split(",");

        String design = suggestions[0];
        String color = suggestions[1];
        String productType = suggestions[2];

        log.info("User with ID: " + userId + " showed interest over " + productType + " of color " + color + " and design " + design);

        //Ideally we would persist the user preferences in the DB.
    }
}

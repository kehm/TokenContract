/*
 * SPDX-License-Identifier: Apache-2.0
 */
package main.java.com.example.tokencontract;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 */
public class JsonParser {

    /**
     * Get JSON representation of the object
     *
     * @param object Object to parse
     * @return JSON representation of the object
     * @throws JsonProcessingException If cannot parse JSON
     */
    public static String getJSON(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    /**
     * Get token object from JSON string
     *
     * @param json JSON string
     * @return Object
     * @throws JsonProcessingException If cannot parse JSON
     */
    public static Token getToken(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.readValue(json, Token.class);
        return mapper.readValue(json, Token.class);
    }
}

/*
 * SPDX-License-Identifier: Apache-2.0
 */
package main.java.com.example.tokencontract;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.List;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.hyperledger.fabric.shim.ledger.CompositeKey;
import org.hyperledger.fabric.shim.ledger.KeyValue;
import org.hyperledger.fabric.shim.ledger.QueryResultsIterator;

/**
 * Class to read/modify tokens in state
 */
public class TokenState {

    /**
     * Add token
     *
     * @param stub Transaction context
     * @param token Token
     * @throws JsonProcessingException If cannot parse token
     */
    public static void add(ChaincodeStub stub, Token token) throws JsonProcessingException {
        CompositeKey key = stub.createCompositeKey(TokenState.class.getPackageName(), token.getId());
        stub.putStringState(key.toString(), JsonParser.getJSON(token));
    }

    /**
     * Update token
     *
     * @param stub Transaction context
     * @param token Token
     * @throws JsonProcessingException If cannot parse token
     */
    public static void update(ChaincodeStub stub, Token token) throws JsonProcessingException {
        add(stub, token);
    }

    /**
     * Get token by ID
     *
     * @param stub Transaction context
     * @param id Token ID
     * @return Token
     * @throws JsonProcessingException If cannot parse token
     */
    public static Token getToken(ChaincodeStub stub, String id) throws JsonProcessingException {
        CompositeKey key = stub.createCompositeKey(TokenState.class.getPackageName(), id);
        String state = stub.getStringState(key.toString());
        return JsonParser.getToken(state);
    }

    /**
     * Get list of all tokens
     *
     * @param stub Transaction context
     * @return Token
     * @throws JsonProcessingException If cannot parse token
     */
    public static List<Token> getAllTokens(ChaincodeStub stub) throws JsonProcessingException {
        String state = stub.getStringState(TokenState.class.getPackageName());
        QueryResultsIterator<KeyValue> pairs = stub.getStateByPartialCompositeKey(state);
        List<Token> tokens = new ArrayList<>();
        for (KeyValue kv : pairs) {
            Token token = JsonParser.getToken(kv.getStringValue());
            tokens.add(token);
        }
        return tokens;
    }
}

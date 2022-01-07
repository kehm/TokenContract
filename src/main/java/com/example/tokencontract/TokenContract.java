/*
 * SPDX-License-Identifier: Apache-2.0
 */
package main.java.com.example.tokencontract;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import java.util.logging.Logger;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.shim.ChaincodeStub;

/**
 * A smart contract for managing tokens
 */
@Contract(name = "com.example.tokencontract", info = @Info(title = "Token Contract", description = "", version = "0.0.1"))
@Default
public class TokenContract implements ContractInterface {

    private final static Logger LOG = Logger.getLogger(TokenContract.class.getName());

    /**
     * Create a new token
     *
     * @param stub Transaction context
     * @param name Token name
     * @param description Token description
     * @param issuer Token issuer
     * @return New token
     * @throws JsonProcessingException If cannot parse token
     */
    @Transaction
    public Token create(ChaincodeStub stub, String name, String description, String issuer) throws JsonProcessingException {
        LOG.info("Invoking chaincode function 'create'...");
        Token token = new Token(name, description, issuer);
        token.setTrading();
        TokenState.add(stub, token);
        LOG.info("Successfully created new token");
        return token;
    }

    /**
     * Change token ownership
     *
     * @param stub Transaction context
     * @param id Token ID
     * @param newOwner New token owner
     * @throws JsonProcessingException If cannot parse token
     */
    @Transaction
    public void transfer(ChaincodeStub stub, String id, String newOwner) throws JsonProcessingException {
        LOG.info("Invoking chaincode function 'transfer'...");
        Token token = TokenState.getToken(stub, id);
        token.setOwner(newOwner);
        TokenState.update(stub, token);
        LOG.info("Successfully transferred token");
    }

    /**
     * Withdraw token
     *
     * @param stub Transaction context
     * @param id Token ID
     * @throws JsonProcessingException If cannot parse token
     */
    @Transaction
    public void withdraw(ChaincodeStub stub, String id) throws JsonProcessingException {
        LOG.info("Invoking chaincode function 'withdraw'...");
        Token token = TokenState.getToken(stub, id);
        token.setWithdrawn();
        TokenState.update(stub, token);
        LOG.info("Successfully withdrawn token");
    }

    /**
     * Get token from state
     *
     * @param stub
     * @param id Token ID
     * @return Token
     * @throws JsonProcessingException If cannot parse token
     */
    @Transaction
    public Token getToken(ChaincodeStub stub, String id) throws JsonProcessingException {
        LOG.info("Invoking chaincode function 'get'...");
        Token token = TokenState.getToken(stub, id);
        LOG.info("Successfully retrieved token");
        return token;
    }

    /**
     * Get list of all tokens in state
     *
     * @param stub Transaction context
     * @return Array of tokens
     * @throws JsonProcessingException If cannot parse token list
     */
    @Transaction
    public List<Token> getAllTokens(ChaincodeStub stub) throws JsonProcessingException {
        LOG.info("Invoking chaincode function 'list'...");
        List<Token> list = TokenState.getAllTokens(stub);
        LOG.info("Successfully retrieved list of tokens");
        return list;
    }
}

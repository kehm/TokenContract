/*
 * SPDX-License-Identifier: Apache-2.0
 */
package main.java.com.example.tokencontract;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

/**
 * Class to represent a token
 */
@DataType
public class Token {

    @Property
    private String id;
    @Property
    private String name;
    @Property
    private String description;
    @Property
    private TokenStatus status;
    @Property
    private String issuer;
    @Property
    private String owner;

    /**
     * Constructor for Token
     *
     * @param name Token name
     * @param description Token description
     * @param issuer Token issuer
     */
    public Token(String name, String description, String issuer) {
        this.id = name + issuer;
        this.name = name;
        this.description = description;
        this.status = TokenStatus.CREATED;
        this.issuer = issuer;
        this.owner = issuer;
    }

    /**
     * Get ID
     *
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * Set ID
     *
     * @param id ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get name
     *
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name
     *
     * @param name Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get description
     *
     * @return Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set description
     *
     * @param description Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get status
     *
     * @return Status
     */
    public TokenStatus getStatus() {
        return status;
    }

    /**
     * Set status
     *
     * @param status Status
     */
    public void setStatus(TokenStatus status) {
        this.status = status;
    }

    /**
     * Get issuer
     *
     * @return Issuer
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * Set issuer
     *
     * @param issuer Issuer
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    /**
     * Get owner
     *
     * @return Owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Set owner
     *
     * @param owner Owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Set status to trading
     */
    public void setTrading() {
        this.status = TokenStatus.TRADING;
    }

    /**
     * Set status to withdrawn
     */
    public void setWithdrawn() {
        this.status = TokenStatus.WITHDRAWN;
    }
}

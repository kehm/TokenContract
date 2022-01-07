/*
 * SPDX-License-Identifier: Apache-2.0
 */
package main.java.com.example.tokencontract;

/**
 * Enum class for token status
 */
public enum TokenStatus {

    CREATED("CREATED"),
    TRADING("TRADING"),
    WITHDRAWN("WITHDRAWN");

    private final String tokenStatus;

    TokenStatus(String tokenStatus) {
        this.tokenStatus = tokenStatus;
    }

    @Override
    public String toString() {
        return this.tokenStatus;
    }
}

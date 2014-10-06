package com.lantushenko.experimental.stub.exceptions;

/**
 * Thrown when objects cannot be compared for some reason
 */
public class ComparisonFailed extends GeneralRuntimeException {

    public ComparisonFailed(String pattern) {
        super(pattern);
    }

    public ComparisonFailed(String pattern, Object[] params) {
        super(pattern, params);
    }
}

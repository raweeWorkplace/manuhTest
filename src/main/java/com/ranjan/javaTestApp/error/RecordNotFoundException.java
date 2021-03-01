package com.ranjan.javaTestApp.error;

/**
 * @author Ranjan
 *
 * 
 */
public final class RecordNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public RecordNotFoundException() {
        super();
    }

    public RecordNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RecordNotFoundException(final String message) {
        super(message);
    }

    public RecordNotFoundException(final Throwable cause) {
        super(cause);
    }

}

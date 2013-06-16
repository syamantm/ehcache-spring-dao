package com.syamantakm.common;

/**
 * @author Syamantak Mukhopadhyay
 */
public class DAOException extends RuntimeException {
    public DAOException(Throwable t) {
        super(t);
    }

    public DAOException(String message, Throwable t) {
        super(message, t);
    }
}

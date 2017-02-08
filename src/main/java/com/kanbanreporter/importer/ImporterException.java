/*
 * Importer Exception.
 */
package com.kanbanreporter.importer;

/**
 * ImporterException class.
 * @author grzegorz
 */
public class ImporterException extends Exception {
    public ImporterException(String message) {
        super(message);
    }
    public ImporterException(String message, Exception cause) {
        super(message, cause);
    }
}
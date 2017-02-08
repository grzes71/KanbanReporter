/*
 * Generate report exception.
 */
package com.kanbanreporter.reporter;

/**
 * Exception occured while generating report.
 * @author grzegorz
 */
public class ReportException extends Exception {
    public ReportException(String message) {
        super(message);
    }
    public ReportException(String message, Exception cause) {
        super(message, cause);
    }
}
/*
 * TKanban reporter.
 */
package com.kanbanreporter.reporter;

import com.kanbanreporter.model.Kanban;

/**
 * Kanban Reporter interface.
 * @author grzegorz
 */
public interface Reporter {
    /**
     * Generate Kanban report.
     * @param kanban Kanban instance
     * @throws com.kanbanreporter.reporter.ReportException
     */
    void generate(Kanban kanban) throws ReportException;
}

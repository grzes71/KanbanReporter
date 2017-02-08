/**
 * Kanban properties.
 */
package com.kanbanreporter.model;

/**
 * Kanban properties class.
 * @author grzegorz
 */
public class KanbanProperties {
    private final String description;

    /**
     * Kanban properties class constructor
     * @param description kanban textual description
     */
    public KanbanProperties(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    
}

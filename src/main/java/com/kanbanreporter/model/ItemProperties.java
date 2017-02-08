/**
 * Item properties.
 * @author grzegorz
 */
package com.kanbanreporter.model;

/**
 * Item Properties class.
 * @author grzegorz
 */
public class ItemProperties {
    private final String description;
    private final String type;
    private final String priority;
    private final String planned_completion_date;
    private final int actual_effort;
    private final int planned_effort;
    private final int remaining_effort;
    private final String state;

    /**
     * ItemProperties class constructor.
     * @param builder 
     */
    public ItemProperties(ItemPropertiesBuilder builder) {
        this.description = builder.getDescription();
        this.type = builder.getType();
        this.priority = builder.getPriority();
        this.planned_completion_date = builder.getPlanned_completion_date();
        this.actual_effort = builder.getActual_effort();
        this.planned_effort = builder.getPlanned_effort();
        this.remaining_effort = builder.getRemaining_effort();
        this.state = builder.getState();
    }

    /**
     * Getter for Item description.
     * @return Item description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for Item type.
     * @return Item type
     */
    public String getType() {
        return type;
    }

    /**
     * Getter for Item priority.
     * @return Item priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Getter for Item planned completion date.
     * @return Item planned completion date
     */
    public String getPlanned_completion_date() {
        return planned_completion_date;
    }

    public int getActual_effort() {
        return actual_effort;
    }

    public int getPlanned_effort() {
        return planned_effort;
    }

    public int getRemaining_effort() {
        return remaining_effort;
    }

    public String getState() {
        return state;
    }
}

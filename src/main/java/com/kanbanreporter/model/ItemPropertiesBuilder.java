/*
 * ItemPropertiesBuilder
 */
package com.kanbanreporter.model;

/**
 * ItemPropertiesBuilder class.
 * @author grzegorz
 */
public class ItemPropertiesBuilder {
    private String description;
    private String type;
    private String priority;
    private String planned_completion_date;
    private int actual_effort;
    private int planned_effort;
    private int remaining_effort;
    private String state;
    
    /**
     * ItemPropertiesBuilder default constructor.
     */
    public ItemPropertiesBuilder() {
    }

    /**
     * Setter for ItemProperties description.
     * @param description ItemProperties description to be set
     * @return ItemPropertiesBuilder instance
     */
    public ItemPropertiesBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Setter for ItemProperties type.
     * @param type ItemProperties type to be set
     * @return ItemPropertiesBuilder instance
     */
    public ItemPropertiesBuilder setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Setter for ItemProperties priority
     * @param priority ItemProperties priority to be set
     * @return ItemPropertiesBuilder instance 
     */
    public ItemPropertiesBuilder setPriority(String priority) {
        this.priority = priority;
        return this;
    }

    /**
     * Setter for ItemProperties planned completion date. 
     * @param planned_completion_date ItemProperties planned completion date to be set
     * @return ItemPropertiesBuilder instance 
     */
    public ItemPropertiesBuilder setPlanned_completion_date(String planned_completion_date) {
        this.planned_completion_date = planned_completion_date;
        return this;
    }

    /**
     * ItemProperties factory method.
     * @return ItemProperties instance.
     */
    public ItemProperties createItemProperties() {
        return new ItemProperties(this);
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @return the planned_completion_date
     */
    public String getPlanned_completion_date() {
        return planned_completion_date;
    }
    
    public int getActual_effort() {
        return actual_effort;
    }
    
    public ItemPropertiesBuilder setActual_effort(int actual_effort) {
        this.actual_effort = actual_effort;
        return this;
    }
    
    public int getPlanned_effort() {
        return planned_effort;
    }
    
    public ItemPropertiesBuilder setPlanned_effort(int planned_effort) {
        this.planned_effort = planned_effort;
        return this;
    }
    
    public int getRemaining_effort() {
        return remaining_effort;
    }
    
    public ItemPropertiesBuilder setRemaining_effort(int remaining_effort) {
        this.remaining_effort = remaining_effort;
        return this;
    }

    public String getState() {
        return state;
    }

    public  ItemPropertiesBuilder setState(String state) {
        this.state = state;
        return this;
    }

}

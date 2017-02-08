/*
 * Item model.
 */
package com.kanbanreporter.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Item model class.
 * 
 * @author grzegorz
 */
public class Item implements Comparable<Item> {
    
    private final Column column;
    private final String name;
    private final ItemProperties properties;
    private final Set<User> users = new HashSet<>();

    /**
     * Add user to user set.
     * @param user user to be added
     */
    public void addUser(User user) {
        users.add(user);
    }
    
    /**
     * Getter for column.
     * @return column
     */
    public Column getColumn() {
        return column;
    }

    /**
     * Getter for name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for properties.
     * @return properties
     */
    public ItemProperties getProperties() {
        return properties;
    }

    /**
     * Getter for users.
     * @return set of users
     */
    public Set<User> getUsers() {
        return users;
    }
   
    /**
     * Item constructor.
     * @param column column this item belongs to.
     * @param name name of item.
     * @param properties item properties.
     */
    public Item(Column column, String name, ItemProperties properties) {
        this.column = column;
        this.name = name;
        this.properties = properties;
    }

    /**
     * Get hash code for Item.
     * @return Item hashcode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.column);
        hash = 13 * hash + Objects.hashCode(this.name);
        return hash;
    }

    /**
     * Equals method implementation.
     * @param obj Item instance to compare
     * @return compare value
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.column, other.column)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    /**
     * Convert Item to String.
     * @return string representation
     */
    @Override
    public String toString() {
        return "Item{" + "column=" + column + ", name=" + name + '}';
    }
    
    /**
     * Compare items by priority
     * @param item item to be compared with
     * @return compare value
     */
    @Override
    public int compareTo(Item item) {
        return properties.getPriority().compareTo(item.getProperties().getPriority());
    }
    
    public int getPercentComplete() {
        int percent_complete = 0;
        if(properties.getActual_effort()!=0 & properties.getPlanned_effort()!=0) {
            percent_complete = (int)(100.0*(float)properties.getActual_effort()/properties.getPlanned_effort());
        }
        return percent_complete;
    }
}

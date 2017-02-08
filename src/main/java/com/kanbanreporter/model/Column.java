/**
 * Kanban Column model.
 */
package com.kanbanreporter.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Kanban column model class.
 * @author grzegorz
 */
public class Column implements Comparable<Column> {

    private final String name;
    private final Kanban kanban;
    private final ColumnProperties properties;
    private final Set<Item> items = new HashSet<>();

    /**
     * Column class constructor.
     * @param name name of column
     * @param kanban kanban instance this column belongs to
     * @param properties properties instance for the column
     */
    public Column(String name, Kanban kanban, ColumnProperties properties) {
        this.name = name;
        this.kanban = kanban;
        this.properties = properties;
    }

    public void addItem(Item item) {
        items.add(item);
    }
    /**
     * Getter for column name.
     * @return column name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for kanban instance (parent)
     * @return 
     */
    public Kanban getKanban() {
        return kanban;
    }

    /**
     * Getter for column properties instance.
     * @return properties instance
     */
    public ColumnProperties getProperties() {
        return properties;
    }

    /**
     * Getter for column items.
     * @return items set
     */
    public Set<Item> getItems() {
        return items;
    }
    
    /**
     * Get Item list sorted.
     * @return sorted set
     */
    public List getSortedItems() {
        List<Item> list = new ArrayList<>(getItems());
        Collections.sort(list);
        return list;
    }
    
    /**
     * Get User set for all User instances in this Column.
     * @return users set
     */
    public Set<User> getUsers() {
        Set<User> users = new HashSet<>();
        items.stream().forEach((item) -> {
            item.getUsers().stream().forEach((user) -> {
                users.add(user);
            });
        });
        return users;
    }

    /**
     * Get all Item instances for specified user in this column, sorted.
     * @param user
     * @return List of Item instances
     */
    public List<Item> getUserItems(User user) {
        return getItems().stream().filter(item -> item.getUsers().contains(user)).sorted().collect(Collectors.toList());
    }
    /**
     * Compare columns by property order.
     * @param column column to be compared with
     * @return compare value
     */
    @Override
    public int compareTo(Column column) {
        return properties.getOrder().compareTo(column.getProperties().getOrder());
    }
    
    @Override
    public String toString() {
        return "Column{" + "name=" + name + '}';
    }
}

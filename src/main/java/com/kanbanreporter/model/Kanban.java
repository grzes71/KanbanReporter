/**
 * Kanban model.
 */
package com.kanbanreporter.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Kanban model class.
 *
 * @author grzegorz
 */
public class Kanban {

    private final String name;
    private final Set<Column> columns = new HashSet<>();
    private final KanbanProperties properties;

    /**
     * Kanban class constructor.
     *
     * @param name name of kanban
     * @param properties KanbanProperties instance
     */
    public Kanban(String name, KanbanProperties properties) {
        this.properties = properties;
        this.name = name;
    }

    /**
     * Add column to Kanban.
     *
     * @param column Column instance to be added
     */
    public void addColumn(Column column) {
        columns.add(column);
    }

    /**
     * Getter for Kanban name.
     *
     * @return kanban name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for Kanban columns
     *
     * @return set of Kanban columns
     */
    public Set<Column> getColumns() {
        return columns;
    }

    /**
     * Getter for Kanban properties.
     *
     * @return KanbanProperties instance
     */
    public KanbanProperties getProperties() {
        return properties;
    }

    /**
     * Getter for all Kanban items.
     *
     * @return set of Item instances
     */
    public Set<Item> getItems() {
        Set<Item> items = new HashSet<>();
        columns.stream().forEach(column -> {
            column.getItems().stream().forEach(item -> {
                items.add(item);
            });
        });
        return items;
    }

    /**
     * Getter for all Kanban users.
     *
     * @return set of User instances
     */
    public Set<User> getUsers() {
        Set<User> users = new HashSet<>();
        columns.stream().forEach((Column column) -> {
            column.getUsers().stream().forEach((User user) -> {
                users.add(user);
            });
        });
        return users;
    }

    /**
     * Getter for Kanban users, sorted.
     *
     * @return List of User instances.
     */
    public List<User> getSortedUsers() {
        List<User> list = new ArrayList<>(getUsers());
        Collections.sort(list);
        return list;
    }

    /**
     * Get all Kanban items, sorted.
     *
     * @return List of Item instances
     */
    public List<Item> getSortedItems() {
        List<Item> list = new ArrayList<>(getItems());
        Collections.sort(list);
        return list;
    }

    /**
     * Get all Kanban columns, sorted.
     *
     * @return List of Column instances
     */
    public List<Column> getSortedColumns() {
        List<Column> list = new ArrayList<>(getColumns());
        Collections.sort(list);
        return list;
    }

    @Override
    public String toString() {
        return "Kanban{" + "name=" + name + '}';
    }

}

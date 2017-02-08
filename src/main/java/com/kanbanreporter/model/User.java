/*
 * Kanban user.
 */
package com.kanbanreporter.model;

import java.util.Objects;

/**
 * Kanban user class.
 * @author grzegorz
 */
public class User implements Comparable<User> {
    
    private final String name;

    /**
     * User class constructor.
     * @param name user name
     */
    public User(String name) {
        this.name = name;
    }
    
    /**
     * Comparator method implementation
     * @param user user to be compared with
     * @return compare value
     */
    @Override
    public int compareTo(User user) {
        return getName().compareTo(user.getName());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name + '}';
    }

    /**
     * Getter for user name.
     * @return user name
     */
    public String getName() {
        return name;
    }
    
}

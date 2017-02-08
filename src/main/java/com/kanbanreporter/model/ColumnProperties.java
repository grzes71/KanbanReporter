/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanbanreporter.model;

/**
 * ColumnProperties class.
 * @author grzegorz
 */
public class ColumnProperties {
    private final String order;

    /**
     * ColumnProperties class constructor.
     * @param order order in the board for this column.
     */
    public ColumnProperties(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "ColumnProperties{" + "order=" + order + '}';
    }

    /**
     * Getter for order.
     * @return Column order
     */
    public String getOrder() {
        return order;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanbanreporter.importer;

import com.kanbanreporter.model.Kanban;

/**
 *
 * @author grzegorz
 */
public interface Importer {

    Kanban createKanban() throws ImporterException;
    
}

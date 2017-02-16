/*
 * Csv import
 */
package com.kanbanreporter.importer;

import com.kanbanreporter.KanbanReporter;
import com.kanbanreporter.model.Column;
import com.kanbanreporter.model.ColumnProperties;
import com.kanbanreporter.model.Item;
import com.kanbanreporter.model.ItemProperties;
import com.kanbanreporter.model.ItemPropertiesBuilder;
import com.kanbanreporter.model.Kanban;
import com.kanbanreporter.model.KanbanProperties;
import com.kanbanreporter.model.User;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.ini4j.Ini;

/**
 * CsvImporter class.
 *
 * @author grzegorz
 */
public class CsvImporter implements Importer {

    private static final  Logger LOGGER = Logger.getLogger(KanbanReporter.class.getName());

    private static final String REGEX_SPLIT_WHITESPACES = "\\s+";
    private static final String OPT_COLUMNS = "columns";
    private static final String OPT_DESCRIPTION = "description";
    private static final String OPT_ORDER = "order";
    private static final String OPT_FILE = "file";
    private static final String OPT_NAME = "name";
    private static final String SEC_KANBAN_NAME = "kanban";

    private final String path;

    /**
     * CsvImporter class constructor.
     *
     * @param path path to config ini file
     */
    public CsvImporter(String path) {
        this.path = path;
    }

    /**
     * Initialize Kanban with data from specified csv files.
     *
     * @return Kanban instance
     * @throws ImporterException
     */
    @Override
    public Kanban createKanban() throws ImporterException {
        String name, description;
        Ini ini;
        Kanban kanban;
        try {
            LOGGER.log(Level.INFO, "Reading config ini file: {0}", path);

            ini = new Ini(new FileReader(path));
            name = ini.get(SEC_KANBAN_NAME, OPT_NAME);
            description = ini.get(SEC_KANBAN_NAME, OPT_DESCRIPTION);
            kanban = new Kanban(name, new KanbanProperties(description));
            String columns = ini.get(SEC_KANBAN_NAME, OPT_COLUMNS);
            String[] columns_all = columns.split(REGEX_SPLIT_WHITESPACES);
            for (String column_suffix : columns_all) {
                createColumn(column_suffix, ini, kanban);

            }
        } catch (IOException | NumberFormatException e) {
            throw new ImporterException("Could not import kanban data from ini file: " + path, e);
        }

        return kanban;
    }

    private void createColumn(String column_suffix, Ini ini, Kanban kanban) throws NumberFormatException, IOException {
        LOGGER.log(Level.INFO, "Generating column: {0}", column_suffix);
        
        String column_section = "column-" + column_suffix;
        
        String column_name = ini.get(column_section, OPT_NAME);
        String column_file = ini.get(column_section, OPT_FILE);
        String column_order = ini.get(column_section, OPT_ORDER);
        
        ColumnProperties column_properties = new ColumnProperties(column_order);
        Column column = new Column(column_name, kanban, column_properties);
        kanban.addColumn(column);
        
        createItems(column_file, column);
    }

    /*
     * Import items from column_file to specific column.
     */
    private void createItems(String column_file, Column column) throws NumberFormatException, IOException {
        LOGGER.log(Level.INFO, "Reading column csv file: {0}", column_file);

        Reader in = new FileReader(column_file);
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withDelimiter('~').
                withSkipHeaderRecord(false).parse(in);
        for (CSVRecord record : records) {
            String item_id = record.get(0);
            String item_priority = record.get(1);
            String item_type = record.get(2);
            String item_state = record.get(3);
            String item_planned_completion_date = record.get(4);
            String item_users = record.get(5);
            int item_planned_effort = Integer.parseInt(record.get(6));
            int item_actual_effort = Integer.parseInt(record.get(7));
            int item_remaining_effort = Integer.parseInt(record.get(8));
            String item_description = record.get(9);

            ItemProperties item_properties = new ItemPropertiesBuilder().
                    setDescription(item_description).
                    setActual_effort(item_actual_effort).
                    setPlanned_completion_date(item_planned_completion_date).
                    setPlanned_effort(item_planned_effort).
                    setPriority(item_priority).
                    setRemaining_effort(item_remaining_effort).
                    setType(item_type).
                    setState(item_state).
                    createItemProperties();
            Item item = new Item(column, item_id, item_properties);

            LOGGER.log(Level.FINEST, "Created {0} added to {1}", new Object[]{item, column});

            column.addItem(item);

            String[] user_names = item_users.split(REGEX_SPLIT_WHITESPACES);
            for (String user_name : user_names) {
                item.addUser(new User(user_name));
            }

        }
    }
}

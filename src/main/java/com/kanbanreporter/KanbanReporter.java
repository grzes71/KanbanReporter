package com.kanbanreporter;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.kanbanreporter.importer.CsvImporter;
import com.kanbanreporter.importer.Importer;
import com.kanbanreporter.importer.ImporterException;
import com.kanbanreporter.model.Kanban;
import com.kanbanreporter.reporter.ReportException;
import com.kanbanreporter.reporter.Reporter;
import com.kanbanreporter.reporter.TemplateReporter;

/**
 *
 * @author grzegorz
 */
public class KanbanReporter {

	private static final Logger LOGGER = Logger.getLogger(KanbanReporter.class.getName());

	private KanbanReporter() {
		super();
	}

	public static void main(String[] args) {
		LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.ALL);
		LOGGER.info("Start KanbanReporter");

		if (args.length == 2) {
			try {
				generateKanban(args[0], args[1]);
			} catch (ImporterException | ReportException e) {
				LOGGER.log(Level.SEVERE, "An error has occured", e);
				System.exit(1);
			}
		} else {
			LOGGER.severe("Provide path to ini config and output file");
		}
	}

	private static void generateKanban(String csv_path, String output_path) throws ImporterException, ReportException {
		Importer csv_importer = new CsvImporter(csv_path);
		Reporter reporter = new TemplateReporter("template.html", output_path);

		Kanban kanban = csv_importer.createKanban();
		reporter.generate(kanban);

	}
}

/*
 * Template Reporter.
 */
package com.kanbanreporter.reporter;

import com.kanbanreporter.model.Kanban;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

/**
 * Generate report using velocity template.
 * @author grzegorz
 */
public class TemplateReporter implements Reporter {

    private static final Logger LOGGER = Logger.getLogger(TemplateReporter.class.getName());

    private static final String FILE_ENCODING = "UTF-8";
    private static final String DATE_FORMAT = "dd-MM-yyyy";
    private static final String TEMPLATE_HTML = "template.html";

    private final String outputFile;

    /**
     * TemplateReporter constructor.
     * @param templateName name of the template
     * @param outputFile output file path
     */
    public TemplateReporter(String templateName, String outputFile) {
        this.outputFile = outputFile;
    }

    /**
     * Generate Kanban report.
     * @param kanban Kanban instance
     * @throws ReportException 
     */
    @Override
    public void generate(Kanban kanban) throws ReportException {

        LOGGER.log(Level.INFO, "Generating report for {0}", kanban);

        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();

        VelocityContext context = new VelocityContext();

        context.put("kanban", kanban);
        context.put("kanban_size", kanban.getColumns().size());
        context.put("now", new SimpleDateFormat(DATE_FORMAT).format(new Date()));

        Template template = ve.getTemplate(TEMPLATE_HTML);
        StringWriter string_writer = new StringWriter();
        template.merge(context, string_writer);

        try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), FILE_ENCODING))) {
            out.write(string_writer.toString());
            out.flush();
            LOGGER.info("Report has been generated");
        } catch (IOException e) {
            throw new ReportException("IO Exception has occured while generating report", e);
        }
    }
}

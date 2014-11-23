package com.lantushenko.experimental.stub.starter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.lantushenko.experimental.stub.repositories.SchemaVersionRepository;

public class DbStarterImpl {

    private static final String SCHEMA_CHANGES_FILE_PATH = "schema_changes.xml";
    private static final String VERSION_ATT = "version";

    private Logger logger = Logger.getLogger(DbStarterImpl.class);

    private DataSourceTransactionManager transactionManager;

    @Autowired
    private SchemaVersionRepository schemaVersionRepository;

    @PostConstruct
    public void start() {
        try {
            Thread.sleep(5000);
            TransactionStatus ts = transactionManager.getTransaction(new DefaultTransactionDefinition(
                    TransactionDefinition.PROPAGATION_REQUIRES_NEW));
            Connection connection = transactionManager.getDataSource().getConnection();
            ensureVersionTableExists(connection);
            doUpdates(connection);

            transactionManager.commit(ts);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void ensureVersionTableExists(Connection connection) throws SQLException {
        connection.createStatement().execute("CREATE TABLE IF NOT EXISTS `version` ("
                + " `id` INT NOT NULL AUTO_INCREMENT, "
                + " `version` INT NOT NULL DEFAULT 0, "
                + " PRIMARY KEY (`id`))");
        schemaVersionRepository.insertVersion();
    }

    private void doUpdates(Connection connection) throws SQLException, SAXException, IOException,
            ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new ClassPathResource(SCHEMA_CHANGES_FILE_PATH).getInputStream());

        int currentVersion = schemaVersionRepository.getVersion();
        int newVersion = 0;
        boolean updated = false;
        NodeList updates = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < updates.getLength(); i++) {
            Node node = updates.item(i);
            if (node instanceof Element) {
                newVersion = Integer.parseInt(((Element) node).getAttribute(VERSION_ATT));
                if (newVersion < currentVersion) {
                    continue;
                }
                String sql = ((Element) node).getTextContent();
                logger.info(String.format("Applying sql for version %d", newVersion));

                connection.createStatement().execute(sql);
                updated = true;
            }
        }
        if (updated) {
            schemaVersionRepository.updateVersion(newVersion);
        }
    }

    public void setTransactionManager(DataSourceTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

}

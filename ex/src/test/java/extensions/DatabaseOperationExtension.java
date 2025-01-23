package extensions;

import extensions.jdbc.ConnectionManager;
import extensions.jdbc.TablesManager;
import org.junit.jupiter.api.extension.*;

import java.sql.Connection;
import java.sql.Savepoint;

public class DatabaseOperationExtension implements
        BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {

    private Connection connection;
    private Savepoint savepoint;

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        ConnectionManager.closeConnection();
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        connection.rollback(savepoint);
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        connection = ConnectionManager.openConnection();
        TablesManager.dropTable(connection);
        TablesManager.createTable(connection);
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        connection.setAutoCommit(false);
        savepoint = connection.setSavepoint("savepoint");
    }
}

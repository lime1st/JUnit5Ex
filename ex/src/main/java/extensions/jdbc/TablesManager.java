package extensions.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 데이터베이스 테이블을 관리하기 위한 클래스
 */
public class TablesManager {

    public static void createTable(Connection connection) {
        String sql = "CREATE TABLE IF NOT EXISTS PASSENGERS (ID VARCHAR(50), " +
                "NAME VARCHAR(50));";

        executeStatement(connection, sql);
    }

    public static void dropTable(Connection connection) {
        String sql = "DROP TABLE IF EXISTS PASSENGERS;";

        executeStatement(connection, sql);
    }

    private static void executeStatement(Connection connection, String sql) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
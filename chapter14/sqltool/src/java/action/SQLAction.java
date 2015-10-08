package action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.SQLResponse;

public class SQLAction {

    public SQLResponse process(String jdbcUrl, String sql) {
        if (sql.trim().toUpperCase().startsWith("SELECT")) {
            return doSelectCommand(jdbcUrl, sql);
        } else {
            return doUpdateCommand(jdbcUrl, sql);
        }
    }
    
    private SQLResponse doSelectCommand(String jdbcUrl, String sql) {
        SQLResponse response = new SQLResponse();
        try (Connection connection = DriverManager.getConnection(jdbcUrl);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] columns = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                columns[i] = metaData.getColumnName(i + 1);
            }
            response.setColumns(columns);
            List<String[]> rows = new ArrayList<>();
            while (resultSet.next()) {
                String[] row = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = resultSet.getString(i + 1);
                }
                rows.add(row);
            }
            response.setRows(rows);
            response.setStatus("OK");
        } catch (SQLException e) {
            response.setError(e.getMessage());
            response.setStatus("ERROR");
        }
        return response;
    }
    
    private SQLResponse doUpdateCommand(String jdbcUrl, String sql) {
        SQLResponse response = new SQLResponse();
        try (Connection connection
                = DriverManager.getConnection(jdbcUrl);
                Statement statement = connection.createStatement()) {
            int rowsUpdated = statement.executeUpdate(sql);
            response.setRowsAffected(rowsUpdated);
            response.setStatus("OK");
        } catch (SQLException e) {
            response.setError(e.getMessage());
            response.setStatus("ERROR");
        }
        return response;
    }
}

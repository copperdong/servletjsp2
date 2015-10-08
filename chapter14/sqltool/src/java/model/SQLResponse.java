package model;

import java.util.List;

public class SQLResponse {
    private String status; // "OK" or "ERROR"
    private String error;
    private int rowsAffected;
    private List<String[]> rows;
    private String columns[];

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getRowsAffected() {
        return rowsAffected;
    }

    public void setRowsAffected(int rowsAffected) {
        this.rowsAffected = rowsAffected;
    }

    public List<String[]> getRows() {
        return rows;
    }

    public void setRows(List<String[]> rows) {
        this.rows = rows;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }
}

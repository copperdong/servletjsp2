package servlet;

import action.SQLAction;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.SQLResponse;

public class SQLToolServlet extends HttpServlet {
    private static String DB_URL = null;
    @Override
    public void init() {
        // a web app needs to load the JDBC driver manually.
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        String dbDir = getServletContext().getRealPath("/WEB-INF");
        DB_URL = "jdbc:derby:" + dbDir + "db/mydb;create=true"; 
    }
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request
                .getRequestDispatcher("/jsp/sqlTool.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        SQLAction sqlAction = new SQLAction();
        String sql = request.getParameter("sql");
        SQLResponse sqlResponse = sqlAction.process(DB_URL, sql);
        request.setAttribute("sqlResponse", sqlResponse);
        RequestDispatcher rd = request
                .getRequestDispatcher("/jsp/sqlTool.jsp");
        rd.forward(request, response);
    }
}
package filedownload1;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/download" })
public class FileDownloadServlet extends HttpServlet {

    private static final long serialVersionUID = 7583L;

    public void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {
        HttpSession session = request.getSession();
        if (session == null || 
                session.getAttribute("loggedIn") == null) {
            RequestDispatcher dispatcher = 
                    request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
            // must return after dispatcher.forward(). Otherwise,  
            // the code below will be executed
            return; 
        }
        String dataDirectory = request.
                getServletContext().getRealPath("/WEB-INF/data");
        File file = new File(dataDirectory, "secret.pdf");
        if (file.exists()) {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", 
                    "attachment; filename=secret.pdf");
            byte[] buffer = new byte[1024];
            // try-with-resources autocloses resources
            try (FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream()) {
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (IOException ex) {
                System.out.println (ex.toString());
            }
        }
    }
}
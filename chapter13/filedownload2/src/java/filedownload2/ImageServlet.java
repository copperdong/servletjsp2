package filedownload2;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/getImage" })
public class ImageServlet extends HttpServlet {

    private static final long serialVersionUID = -99L;

    public void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {
        String referrer = request.getHeader("referer");
        if (referrer != null) {
            String imageId = request.getParameter("id");
            String imageDirectory = request.getServletContext().
                    getRealPath("/WEB-INF/image");
            File file = new File(imageDirectory, 
                    imageId + ".jpg");
            if (file.exists()) {
                response.setContentType("image/jpg");
                byte[] buffer = new byte[1024];
                // try-with-resources automatically closes the resources
                try (FileInputStream fis = new FileInputStream(file);
                    BufferedInputStream bis = 
                            new BufferedInputStream(fis);
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
}
package servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import listener.MyWriteListener;

@WebServlet(name = "WriteListenerTestServlet", urlPatterns = {
    "/writeListener"}, asyncSupported = true)
public class WriteListenerTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/jpg");
        String imageDirectory = request.getServletContext().
                getRealPath("/WEB-INF/image");
        File file = new File(imageDirectory, "1.jpg");
        
        AsyncContext asyncContext = request.startAsync();
        asyncContext.addListener(new AsyncListener() {
            public void onComplete(AsyncEvent event) throws IOException {
                System.out.println("onComplete");
            }
            public void onError(AsyncEvent event) {
                System.out.println(event.getThrowable());
            }
            public void onStartAsync(AsyncEvent event) {
                System.out.println("onStartAsync");
            }
            public void onTimeout(AsyncEvent event) {
                System.out.println("onTimeout");
            }
        });

        ServletOutputStream servletOutputStream = 
                response.getOutputStream();
        MyWriteListener listener = new MyWriteListener(file, 
                servletOutputStream, asyncContext);
        servletOutputStream.setWriteListener(listener);
    }
}
package servlet;

import java.io.IOException;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import listener.MyReadListener;

@WebServlet(urlPatterns = {"/readListener"}, asyncSupported = true)
public class ReadListenerTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(
                "/jsp/readListener1.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        AsyncContext asyncContext = request.startAsync();
        asyncContext.addListener(new AsyncListener() {
            public void onComplete(AsyncEvent event) throws IOException {
                System.out.println("onComplete");
                String error = (String) request.getAttribute("error");
                if (error != null) {
                    request.setAttribute("message", "Error occurred:" 
                            + error);
                    request.removeAttribute("error");
                } else {
                    String result = (String) 
                            request.getAttribute("result");
                    request.setAttribute("message", 
                            "Successfully received " + 
                            result.length() + " bytes.");
                    request.removeAttribute("result");
                }
                RequestDispatcher rd = request.getRequestDispatcher(
                    "/jsp/readListener2.jsp");
                try {
                    rd.forward(request, response);
                } catch (ServletException ex) {
                }

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

        ServletInputStream servletInputStream = request.getInputStream();
        MyReadListener listener = new MyReadListener(servletInputStream,
            asyncContext);
        servletInputStream.setReadListener(listener);
    }
}
package listener;

import java.io.IOException;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.websocket.Session;

@WebListener
public class ExpiryTrackerHttpSessionListener 
        implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        System.out.println("http session created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("http session destroyed");
        HttpSession httpSession = event.getSession();
        Session websocketSession = (Session)
                httpSession.getAttribute("WEBSOCKET_SESSION");
        if (websocketSession != null) {
            try {
                websocketSession.getBasicRemote().sendText("expired");
            } catch (IOException ex) {
            }
        }
    }
}

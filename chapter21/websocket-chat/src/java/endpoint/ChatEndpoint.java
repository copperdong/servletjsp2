package endpoint;

import java.io.IOException;
import java.util.Set;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class ChatEndpoint {
    
    @OnMessage
    public void sendMessageToAll(String msg, Session session) {
        Set<Session> openSessions = session.getOpenSessions();
        for (Session ses : openSessions) {
            try {
                ses.getBasicRemote().sendText(msg);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
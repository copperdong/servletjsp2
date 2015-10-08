package endpoint;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat2")
public class Chat2Endpoint {
    
    @OnOpen
    public void open(Session session) {
        List<String> userNames = session.getRequestParameterMap()
                .get("userName");
        if (userNames != null) {
            String userName = userNames.get(0);
            session.getUserProperties().put("userName", userName);
        }
        List<String> rooms = session.getRequestParameterMap()
                .get("room");
        if (rooms != null) {
            String room = rooms.get(0);
            session.getUserProperties().put("room", room);
        }
    }
    @OnMessage
    public void sendMessageToAll(String msg, Session session) {
        String userName = (String) session.getUserProperties()
                .get("userName");
        String room = (String) session.getUserProperties()
                .get("room");
        Set<Session> openSessions = session.getOpenSessions();
        for (Session ses : openSessions) {
            if (room.equals((String) 
                    ses.getUserProperties().get("room"))) {
                try {
                    ses.getBasicRemote().sendText(userName + ": " + msg);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
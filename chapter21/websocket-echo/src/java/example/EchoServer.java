package example;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class EchoServer {
    @OnMessage
    public String echo(String message) {
        System.out.println("echo:" + message);
        return "Echoing " + message;
    }
}

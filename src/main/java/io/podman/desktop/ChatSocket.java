package io.podman.desktop;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.SendHandler;
import jakarta.websocket.SendResult;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import org.jboss.logging.Logger;

@ServerEndpoint("/summarizer")
@ApplicationScoped
public class ChatSocket {

    @Inject
    SummarizerService summarizerService;

    private static final Logger LOG = Logger.getLogger(ChatSocket.class);

    Session session = null;
    Queue<String> response = new LinkedList<String>();
    boolean send = true;

    @OnOpen
    public void onOpen(Session session) {
        this.session=session;
    }

    @OnClose
    public void onClose(Session session) {
        try {
            this.session.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.session = null;
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        this.session = null;
        LOG.error("onError", throwable);
        broadcast("Error: " + throwable);
    }

    @OnMessage
    public void onMessage(String textToSummarize) {
        summarizerService.getSummarization(textToSummarize, token -> broadcast(token));
    }

    private void broadcast(String message) {
        session.getAsyncRemote().sendText(message, new SendHandler() {
            @Override
            public void onResult(SendResult result) {
                // TODO Auto-generated method stub
            } 
        });  
    }

}
